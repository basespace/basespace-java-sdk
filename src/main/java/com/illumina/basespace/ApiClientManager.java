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

import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illumina.basespace.auth.AccessDeniedException;
import com.illumina.basespace.auth.AccessToken;
import com.illumina.basespace.auth.AuthVerificationCode;
import com.illumina.basespace.infrastructure.BaseSpaceException;
import com.illumina.basespace.util.BrowserLaunch;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.representation.Form;

/**
 * 
 * @author bking
 *
 */
public final class ApiClientManager
{
    private static Logger logger = Logger.getLogger(ApiClientManager.class.getPackage().getName());
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final ApiClientManager singletonObject = new ApiClientManager();

    private static Client httpClient = createHttpClient();
    
    private ApiClientManager()
    {

    }
    public static synchronized ApiClientManager instance()
    {
        return singletonObject;
    }

    public Object clone() throws CloneNotSupportedException 
    {
        throw new CloneNotSupportedException();
    }

    /**
     * Request a BaseSpace session  
     * @param configuration configuration for the session
     * @return a new BaseSpaceSession
     * @throws AccessDeniedException if an access token could not be obtained from BaseSpace
     */
    public ApiClient createClient(ApiConfiguration configuration)throws AccessDeniedException
    {
        if (configuration == null)throw new IllegalArgumentException("null configuration");
        return new DefaultApiClient(
                new DefaultClientConnectionProvider(configuration,requestAccessToken(configuration)));
    }

    protected String requestAccessToken(ApiConfiguration configuration)
            throws AccessDeniedException
    {
        try
        {
            if (configuration.getAccessToken() != null)
            {
                return configuration.getAccessToken();
            }

            AuthVerificationCode authCode = getAuthVerificationCode(configuration);

            String uri = authCode.getVerificationWithCodeUri();
            BrowserLaunch.openURL(uri);

            return startAccessTokenPolling(authCode, configuration);
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException("Error requesting access token from BaseSpace: " + t.getMessage());
        }
    }

    /**
     * Get the verification code and device code
     *
     * This API corresponds to the following step in the authentication flow:
     * https://developer.basespace.illumina.com/docs/content/documentation/authentication/obtaining-access-tokens#Gettingtheverificationcodeanddevicecode
     *
     * @param configuration configuration for the session
     * @return an auth verification code
     * @throws AccessDeniedException if an auth verification token could not be obtained from BaseSpace
     */
    public AuthVerificationCode getAuthVerificationCode(ApiConfiguration configuration) throws AccessDeniedException {
        try
        {
            Form form = new Form();
            form.add("client_id", configuration.getClientId());
            form.add("scope",  configuration.getAuthorizationScope());
            form.add("response_type", "device_code");

            Client client = getHttpClient();

            WebResource resource = client.resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(configuration.getAuthorizationUriFragment())
                    .build());
            logger.finer(resource.toString());

            ClientResponse response = resource.accept(
                    MediaType.APPLICATION_XHTML_XML,
                    MediaType.APPLICATION_FORM_URLENCODED,
                    MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class,form);
            String responseAsJSONString = response.getEntity(String.class);
            logger.finer(responseAsJSONString);

            AuthVerificationCode authCode = mapper.readValue(responseAsJSONString, AuthVerificationCode.class);
            if (authCode.getError() != null)
            {
                throw new BaseSpaceException(authCode.getErrorDescription(),null,resource.getURI());
            }

            logger.finer(authCode.toString());
            return authCode;
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException("Error requesting auth verification code from BaseSpace: " + t.getMessage());
        }
    }

    protected String startAccessTokenPolling(AuthVerificationCode authCode, ApiConfiguration configuration){
        try {
            //Poll for approval
            Form form = new Form();
            form.add("client_id", configuration.getClientId());
            form.add("client_secret",configuration.getClientSecret());
            form.add("code",authCode.getDeviceCode());
            form.add("grant_type","device");

            Client client = getHttpClient();

            WebResource resource = client.resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(configuration.getAccessTokenUriFragment())
                    .build());

            String accessToken = null;
            while(accessToken == null)
            {
                long interval = authCode.getInterval() * 1000;
                Thread.sleep(interval);
                accessToken = getAccessToken(resource, form);
            }
            return accessToken;
        }  catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException("Error requesting access token from BaseSpace: " + t.getMessage());
        }
    }

    /**
     * Perform a single polling attempt to get access token. This function should be called periodically while awaiting user authorization.
     *
     * This API corresponds to the following step in the authentication flow:
     * https://developer.basespace.illumina.com/docs/content/documentation/authentication/obtaining-access-tokens#Gettingtheaccesstokenfornonweb-basedapps
     *
     * @param deviceCode device code received from the previous step {@link this#getAuthVerificationCode}
     * @param configuration configuration for the session
     * @return an access token or null if the user has not yet approved the access request
     * @throws AccessDeniedException if an auth verification token could not be obtained from BaseSpace
     */
    public String getAccessToken(String deviceCode, ApiConfiguration configuration) throws BaseSpaceException {
        Form form = new Form();
        form.add("client_id", configuration.getClientId());
        form.add("client_secret",configuration.getClientSecret());
        form.add("code",deviceCode);
        form.add("grant_type","device");

        Client client = getHttpClient();
        WebResource resource = client.resource(UriBuilder.fromUri(configuration.getApiRootUri())
                .path(configuration.getVersion())
                .path(configuration.getAccessTokenUriFragment())
                .build());
        return getAccessToken(resource, form);
    }

    private String getAccessToken(WebResource resource, Form form) throws BaseSpaceException {
        try {
            ClientResponse response = resource.accept(
                    MediaType.APPLICATION_XHTML_XML,
                    MediaType.APPLICATION_FORM_URLENCODED,
                    MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, form);

            String responseAsJSONString = response.getEntity(String.class);
            logger.finer(responseAsJSONString);
            String accessToken = null;
            if (response.getClientResponseStatus().getStatusCode() > 400) {
                AccessToken error = mapper.readValue(responseAsJSONString, AccessToken.class);
                throw new BaseSpaceException(resource.getURI(), error.getErrorDescription(), response.getClientResponseStatus().getStatusCode());
            }
            if (response.getClientResponseStatus().getStatusCode() == 200) {

                AccessToken token = mapper.readValue(responseAsJSONString, AccessToken.class);
                accessToken = token.getAccessToken();
            }
            return accessToken;
        } catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException("Error polling for access token from BaseSpace: " + t.getMessage());
        }
    }

    private static Client createHttpClient(){
        try {
            Client client = Client.create(new DefaultClientConfig());
            client.addFilter(new ClientFilter() {
                @Override
                public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
                    logger.fine(request.getMethod() + " to " + request.getURI().toString());
                    ClientResponse response = null;
                    try {
                        response = getNext().handle(request);
                    } catch (ClientHandlerException t) {
                        throw new BaseSpaceException(t.getMessage(), t, request.getURI());
                    }
                    return response;
                }
            });
            return client;
        } catch(Throwable t){
            return null;
        }
    }

    private Client getHttpClient() {
        if (httpClient == null) {
            httpClient = createHttpClient();
            if (httpClient == null) {
                throw new RuntimeException("Error creating Web Client");
            }
        }
        return httpClient;
    }
    
}
