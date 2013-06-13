/**
* Copyright 2013 Illumina
* 
 * Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*    http://www.apache.org/licenses/LICENSE-2.0
* 
 *  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

package com.illumina.basespace.infrastructure;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.illumina.basespace.ApiConfiguration;
import com.illumina.basespace.auth.ResourceForbiddenException;
import com.illumina.basespace.auth.UnauthorizedException;
import com.illumina.basespace.param.Mappable;
import com.illumina.basespace.response.ApiResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class DefaultClientConnectionProvider implements ClientConnectionProvider
{
    private Logger logger = Logger.getLogger(DefaultClientConnectionProvider.class.getPackage().getName());
    private ApiConfiguration configuration;
    private String accessToken;
    private final ObjectMapper mapper = new ObjectMapper();
    
    public DefaultClientConnectionProvider(ApiConfiguration configuration,String accessToken)
    {
        this.configuration = configuration;
        this.accessToken = accessToken;
        this.mapper.addHandler(new DeserializationProblemHandler()
        {
            @Override
                public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp,
                                 JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException,
                                                                           JsonProcessingException
            {
                logger.warning("Ignoring unknown property '" + propertyName + "' when attempting to deserialize JSON to "
                       + beanOrClass.getClass().getName());
                return false;
            }
        });
    }
    
    private Client client;
    public Client getClient()
    {
        if (client != null) return client;
        return client = createClient();
    }
    private Client createClient()
    {
        ClientConfig config = new DefaultClientConfig();
        URLConnectionClientHandler urlConnectionClientHandler = 
                new URLConnectionClientHandler(new BaseSpaceURLConnectionFactory(this.configuration));

        Client client = new Client(urlConnectionClientHandler, config);
        client.setReadTimeout(configuration.getReadTimeout());
        client.setConnectTimeout(configuration.getConnectionTimeout());
        client.addFilter(new ClientFilter()
        {
            @Override
            public ClientResponse handle(ClientRequest request) throws ClientHandlerException
            {
                logger.fine(request.getMethod() + " to " + request.getURI().toString());
                if (accessToken != null)
                {
                    request.getHeaders().add("x-access-token", accessToken);
                }
                ClientResponse response = null;
                try
                {
                    response = getNext().handle(request);
                    if (response.getStatus() > 400)
                    {
                        switch (response.getClientResponseStatus())
                        {
                            //Wrap more commonly encountered response status codes in a higher level exception
                            case NOT_FOUND:
                                throw new ResourceNotFoundException(request.getURI());
                            case FORBIDDEN:
                                throw new ResourceForbiddenException(request.getMethod(),request.getURI());
                            case UNAUTHORIZED:
                                throw new UnauthorizedException();
                            default:
                                throw new BaseSpaceException(request.getURI(),response.getStatus());
                        }   
                    }
               
                }
                catch (ClientHandlerException t)
                {
                    throw new BaseSpaceException(t.getMessage(), t,request.getURI());
                }
                return response;
            }
        });
        return client;
    }
    @Override
    public ApiConfiguration getConfiguration()
    {
        return configuration;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiResponse<?, ?>> T getResponse(Class<? extends ApiResponse<?, ?>> clazz, String path,
            Mappable params,Map<String,String>headers)
    {
        try
        {
            return (T) mapper.readValue(getStringResponse(path,params,headers),clazz);
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (RuntimeException t)
        {
            throw t;
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    
    @Override
    public String getStringResponse(String path, Mappable params,Map<String,String>headers)
    {
        try
        {
            int idx = path.indexOf(configuration.getVersion());
            if (idx > -1) path = path.substring(idx + configuration.getVersion().length());
            MultivaluedMap<String, String> queryParams = params == null ? new MultivaluedMapImpl() : params.toMap();
            WebResource resource = getClient().resource(
                    configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(path)
                    .queryParams(queryParams);
            WebResource.Builder builder = resource.getRequestBuilder();
            if (headers != null)
            {
                for(String key:headers.keySet())
                {
                    builder = resource.header(key, headers.get(key));
                }
            }
            String response = builder.accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XHTML_XML).get(String.class);
            logger.fine(response);
            return response;
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiResponse<?, ?>> T postResource(Class<? extends ApiResponse<?, ?>> clazz, String path,
            CreatableResource resource,Map<String,String>headers,Mappable params)
    {
        try
        {
            ConversionContext ctx = new ConversionContext()
            {
                @Override
                public ApiConfiguration getApiConfiguration()
                {
                    return configuration;
                }
                @Override
                public ObjectMapper getMapper()
                {
                    return mapper;
                }
            };
            if (resource == null)throw new IllegalArgumentException("Null resource to create/update");
            WebResource webResource = getClient().resource(
                    configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(path);
            WebResource.Builder builder = webResource.getRequestBuilder();
            if (headers != null)
            {
                for(String key:headers.keySet()){builder = builder.header(key, headers.get(key));}
            }                
            String response = webResource
                    .accept(MediaType.APPLICATION_JSON)
                    .post(String.class,resource.toForm() != null?resource.toForm():resource.toJson(ctx));
            logger.fine(response);
            return (T) mapper.readValue(response,clazz);
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    @Override
    public ObjectMapper getMapper()
    {
        return mapper;
    }
 
    
    

}
