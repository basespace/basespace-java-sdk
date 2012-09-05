/**
* Copyright 2012 Illumina
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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

/**
 * 
 * @author bking
 *
 */
public final class BaseSpaceSessionManager
{
    private static Logger logger = Logger.getLogger(BaseSpaceSessionManager.class.getPackage().getName());
    private static BaseSpaceSessionManager singletonObject;
    private static final String ACCESS_DENIED = "access_denied";
    //private static final String AUTHORIZATION_PENDING = "authorization_pending";
    
    private BaseSpaceSessionManager()
    {
     

    }
    public static synchronized BaseSpaceSessionManager instance()
    {
        if (singletonObject == null)
            singletonObject = new BaseSpaceSessionManager();
        return singletonObject;
    }

    public Object clone() throws CloneNotSupportedException 
    {
        throw new CloneNotSupportedException();
    }

      
    public BaseSpaceSession requestSession(BaseSpaceConfiguration configuration)throws AccessDeniedException
    {
        BaseSpaceUtilities.assertNotNull(configuration, "configuration");
        return new DefaultBaseSpaceSession(configuration,requestAccessToken(configuration));

    }

    
    protected String requestAccessToken(BaseSpaceConfiguration configuration)
            throws AccessDeniedException
    {
        try
        {
            Form form = new Form();
            form.add("client_id", configuration.getClientId());
            form.add("scope",  configuration.getAuthorizationScope());
            form.add("response_type", "device_code");

            Client client = Client.create(new DefaultClientConfig());
            WebResource resource = client.resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(configuration.getAuthorizationUriFragment())
                    .build());
            logger.info(resource.toString());
        
            ClientResponse response = resource.accept(
                    MediaType.APPLICATION_XHTML_XML,
                    MediaType.APPLICATION_FORM_URLENCODED,
                    MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class,form);
            String responseAsJSONString = response.getEntity(String.class);
            logger.info(responseAsJSONString);
            
            ObjectMapper mapper = new ObjectMapper();
            AuthVerificationCode authCode = mapper.readValue(responseAsJSONString, AuthVerificationCode.class);
            logger.info(authCode.toString());
            
            //TODO: This is a temporary fix
            //String uri = authCode.getVerificationWithCodeUri().replace("https://oauth/", "https://cloud-endor.illumina.com/oauth/");
            String uri = authCode.getVerificationWithCodeUri();
            
            BrowserLaunch.openURL(uri);
            
            //Poll for approval
            form = new Form();
            form.add("client_id", configuration.getClientId());
            form.add("client_secret",configuration.getClientSecret());
            form.add("code",authCode.getDeviceCode());
            form.add("grant_type","device");
           
            resource = client.resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(configuration.getAccessTokenUriFragment())
                    .build());
            
            String accessToken = null;
            while(accessToken == null)
            {
                long interval = authCode.getInterval() * 1000;
                Thread.sleep(interval);
                response = resource.accept(
                        MediaType.APPLICATION_XHTML_XML,
                        MediaType.APPLICATION_FORM_URLENCODED,
                        MediaType.APPLICATION_JSON)
                        .post(ClientResponse.class,form);
               
                switch(response.getClientResponseStatus())
                {
                    case BAD_REQUEST:
                        AccessToken token = mapper.readValue(response.getEntity(String.class), AccessToken.class);
                        if (token.getError().equalsIgnoreCase(ACCESS_DENIED))
                        {
                            throw new AccessDeniedException();
                        }
                        break;
                    case OK:
                        token = mapper.readValue(response.getEntity(String.class), AccessToken.class);
                        accessToken = token.getAccessToken();
                }
            } 
            return accessToken;
            
        }
        catch(AccessDeniedException denied)
        {
            throw denied;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }
    
}
