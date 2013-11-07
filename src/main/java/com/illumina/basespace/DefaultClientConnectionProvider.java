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

package com.illumina.basespace;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illumina.basespace.auth.ResourceForbiddenException;
import com.illumina.basespace.auth.UnauthorizedException;
import com.illumina.basespace.entity.ApiResource;
import com.illumina.basespace.entity.Project;
import com.illumina.basespace.infrastructure.BaseSpaceException;
import com.illumina.basespace.infrastructure.BaseSpaceURLConnectionFactory;
import com.illumina.basespace.infrastructure.ClientConnectionProvider;
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.Jsonable;
import com.illumina.basespace.infrastructure.ResourceNotFoundException;
import com.illumina.basespace.param.Mappable;
import com.illumina.basespace.response.ApiResponse;
import com.illumina.basespace.util.TypeHelper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author bking
 *
 */
class DefaultClientConnectionProvider implements ClientConnectionProvider
{
    private Logger logger = Logger.getLogger(DefaultClientConnectionProvider.class.getPackage().getName());
    private ApiConfiguration configuration;
    private String accessToken;
    private ConversionContext context;
    
    public DefaultClientConnectionProvider(final ApiConfiguration configuration,String accessToken)
    {
        this.configuration = configuration;
        this.accessToken = accessToken;
        context = new ConversionContext()
        {

            @Override
            public ApiConfiguration getApiConfiguration()
            {
                return configuration;
            }

            @Override
            public ObjectMapper getMapper()
            {
                return TypeHelper.INSTANCE.getObjectMapper();
            }
        };
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
                    if (response.getStatus() >= 400)
                    {
                        String message =response.getClientResponseStatus().toString();
                        if (response.getType().toString().indexOf(MediaType.APPLICATION_JSON) > -1)
                        {
                            try
                            {
                                ApiResponse<?,?> status = ( ApiResponse<?,?>) TypeHelper.INSTANCE.getObjectMapper().readValue(response.getEntity(String.class) ,ApiResponse.class);
                                message = status.getResponseStatus().getMessage();
                            }
                            catch (Throwable e){}
                        }
                        switch (response.getClientResponseStatus())
                        {
                            //Wrap more commonly encountered response status codes in a higher level exception
                            case NOT_FOUND:
                                throw new ResourceNotFoundException(request.getURI());
                            case FORBIDDEN:
                                throw new ResourceForbiddenException(request.getMethod(),message,request.getURI());
                            case UNAUTHORIZED:
                                throw new UnauthorizedException();
                            default:
                                throw new BaseSpaceException(request.getURI(),message,response.getStatus());
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
    
    @Override
    public <T extends ApiResponse<?, ?>> T getResponse(Class<? extends ApiResponse<?, ?>> clazz, String path,
            Mappable params,Map<String,String>headers)
    {
        return getResponse(clazz,configuration.getApiRootUri(),path,params,headers);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiResponse<?, ?>> T getResponse(Class<? extends ApiResponse<?, ?>> clazz, 
            String root,String path, Mappable params,Map<String,String>headers)
    {
        try
        {
            return (T) TypeHelper.INSTANCE.getObjectMapper().readValue(getStringResponse(root,path,params,headers),clazz);
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
        return getStringResponse(configuration.getApiRootUri(),path,params,headers);
    }
    
    @Override
    public String getStringResponse(String root,String path,Mappable params,Map<String,String>headers)
    {
        try
        {
            int idx = path.indexOf(configuration.getVersion());
            if (idx > -1) path = path.substring(idx + configuration.getVersion().length());
            MultivaluedMap<String, String> queryParams = params == null ? new MultivaluedMapImpl() : params.toMap();
            WebResource resource = getClient().resource(
                     root)
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
    @Override
    public <T extends ApiResponse<?, ?>> T postForm(Class<? extends ApiResponse<?, ?>> clazz, String path,
           Map<String,String>headers,Form formData)
    {
        return postForm(clazz,configuration.getApiRootUri(),path,headers,formData);
    }
    
    @Override
    public <T extends ApiResponse<?, ?>> T postForm(Class<? extends ApiResponse<?, ?>> clazz, String root,String path,
           Map<String,String>headers,Form formData)
    {
        return postInternal(clazz,root,path,headers,formData,MediaType.APPLICATION_FORM_URLENCODED);
    }
    
    @Override
    public <T extends ApiResponse<?, ?>> T postJson(Class<? extends ApiResponse<?, ?>> clazz, String path,
            Map<String, String> headers, Jsonable json)
    {
        return postJson(clazz,configuration.getApiRootUri(),path,headers,json);
    }
    
    @Override
    public <T extends ApiResponse<?, ?>> T postJson(Class<? extends ApiResponse<?, ?>> clazz, String root,String path,
            Map<String, String> headers, Jsonable json)
    {
        return postInternal(clazz,root,path,headers,json.toJson(context),MediaType.APPLICATION_JSON);
    }
    
    @Override
    public void delete(String root, String path, Map<String, String> headers)
    {
        deleteInternal(root,path,headers);
    }
    
    @Override
    public void delete(String path, Map<String, String> headers)
    {
        delete(configuration.getApiRootUri(),path,headers);
    }
    
    protected <T extends ApiResponse<?, ?>> T postInternal(Class<? extends ApiResponse<?, ?>> clazz, String path,
            Map<String, String> headers, Object data,String mediaType)
    {
        return postInternal(clazz,configuration.getApiRootUri(),path,headers,data,mediaType);
    }
    
    protected void deleteInternal(String root,String path,Map<String, String> headers)
    {
        try
        {
            WebResource webResource = getClient().resource(
                    root)
                    .path(configuration.getVersion())
                    .path(path);
            WebResource.Builder builder = webResource.getRequestBuilder();
            if (headers != null)
            {
                for(String key:headers.keySet()){builder = builder.header(key, headers.get(key));}
            }               
            String response = builder.delete(String.class);
            logger.fine(response);
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }
    
    @SuppressWarnings("unchecked")
    protected <T extends ApiResponse<?, ?>> T postInternal(Class<? extends ApiResponse<?, ?>> clazz, String root,String path,
            Map<String, String> headers, Object data,String mediaType)
    {
        try
        {
            WebResource webResource = getClient().resource(
                    root)
                    .path(configuration.getVersion())
                    .path(path);
            WebResource.Builder builder = webResource.getRequestBuilder();
            builder = builder.header("Content-Type", mediaType);
            if (headers != null)
            {
                for(String key:headers.keySet()){builder = builder.header(key, headers.get(key));}
            }               
            logger.fine(data.toString());
            String response = builder
                    .accept(mediaType)
                    .post(String.class,data);
            logger.fine(response);
            return (T) TypeHelper.INSTANCE.getObjectMapper().readValue(response,clazz);
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiResponse<?, ?>> T putFile(Class<? extends ApiResponse<?, ?>> clazz, String path,
            Map<String, String> headers, InputStream file)
    {
        try
        {
            WebResource resource = getClient()
                    .resource(UriBuilder.fromUri(getConfiguration().getApiRootUri())
                            .path(getConfiguration().getVersion())
                            .path(path).build());
            
            WebResource.Builder builder = resource.getRequestBuilder();
            if (headers != null)
            {
                for(String key:headers.keySet()){builder = builder.header(key, headers.get(key));}
            }         
            builder = builder
                    .type(MediaType.APPLICATION_OCTET_STREAM)
                    .accept(MediaType.APPLICATION_JSON)
                    .entity(file);
            
            String response = builder.put(String.class);
            logger.fine(response);
            return (T) TypeHelper.INSTANCE.getObjectMapper().readValue(response,clazz);
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
    public ObjectMapper getMapper()
    {
        return TypeHelper.INSTANCE.getObjectMapper();
    }
  
 
 
    
    

}
