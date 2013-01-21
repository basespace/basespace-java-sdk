package com.illumina.basespace.infrastructure;

import java.io.IOException;
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
import com.illumina.basespace.auth.ForbiddenResourceException;
import com.illumina.basespace.auth.UnauthorizedException;
import com.illumina.basespace.entity.UserOwnedResource;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.response.ApiResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class DefaultClientConnectionProvider implements ClientConnectionProvider
{
    private final Logger logger = Logger.getLogger(DefaultClientConnectionProvider.class.getPackage().getName());
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
                return true;
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
                logger.info(request.getMethod() + " to " + request.getURI().toString());
                if (accessToken != null)
                {
                    logger.finer("Auth token " + accessToken);
                    request.getHeaders().add("x-access-token", accessToken);
                }
                ClientResponse response = null;
                try
                {
                    response = getNext().handle(request);
                    switch (response.getClientResponseStatus())
                    {
                        case FORBIDDEN:
                            throw new ForbiddenResourceException(request.getMethod(),request.getURI());
                        case UNAUTHORIZED:
                            throw new UnauthorizedException();
                    }
                }
                catch (ClientHandlerException t)
                {
                    throw new ConnectionException(request.getURI().toString(), t);
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
            QueryParams params)
    {
        try
        {
            return (T) mapper.readValue(getJSONResponse(path,params),clazz);
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
    public String getJSONResponse(String path, QueryParams params)
    {
        try
        {
            MultivaluedMap<String, String> queryParams = params == null ? new MultivaluedMapImpl() : params.toMap();
            String response = getClient().resource(
                    configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(path)
                    .queryParams(queryParams)
                    .accept(MediaType.APPLICATION_JSON).get(String.class);
            logger.info(response);
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
            UserOwnedResource resource)
    {
        try
        {
            if (resource == null)throw new IllegalArgumentException("Null resource to create/update");
            String response = getClient().resource(
                    configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(path)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(String.class,resource.toForm());
            logger.info(response);
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
    
    

}
