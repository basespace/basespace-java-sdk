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

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author bking
 *
 */
public class AuthenticationPipeline implements AuthCodeListener
{
    private HttpServer httpServer;
    private BaseSpaceConfiguration config;
    private String sessionId;
    private List<AuthTokenListener>authTokenListeners =  Collections.synchronizedList(new ArrayList<AuthTokenListener>());
    private String localServer;
    private AuthorizationStatus status = AuthorizationStatus.WaitingForPermission;
    
    public AuthenticationPipeline(String sessionId,BaseSpaceConfiguration config,AuthTokenListener authTokenListener)
    {
        this.sessionId = sessionId;
        this.config = config;
        this.localServer = "http://localhost:" + String.valueOf(config.getAuthCodeListenerPort());
        addAuthTokenListener(authTokenListener);
        startServer();
        requestAuthorization();
    }
    
    AuthorizationStatus getStatus()
    {
        return status;
    }
    
    private void startServer()
    {
        httpServer = new HttpServer(getConfig().getAuthCodeListenerPort(),
                getConfig().getAuthCodeListenerTimeout());
        httpServer.addAuthCodeListener(this);
        new Thread(httpServer).start();
    }
    
    private void requestAuthorization()
    {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("client_id",getConfig().getClientId());
        params.add("redirect_uri",localServer);
        params.add("response_type","code" );
        params.add("scope",getConfig().getAuthorizationScope());
        WebResource resource = client.resource(UriBuilder.fromUri(getConfig().getAuthorizationUri()).build());
        resource = resource.queryParams(params);
        BrowserLaunch.openURL(resource.getURI().toString());
    }
    
    protected void requestAuthToken(String authCode)
    {
        try
        {
            Form form = new Form();
            form.add("code",authCode);
            form.add("redirect_uri",localServer);
            form.add("grant_type","authorization_code");
            form.add("client_id", config.getClientId());
            form.add("client_secret", config.getClientSecret());
            
            Client client = Client.create(new DefaultClientConfig());
            WebResource resource = client.resource(UriBuilder.fromUri(config.getApiRootUri()).path(config.getVersion()).build());
            String response = resource.path(config.getAuthTokenUriFragment())
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_FORM_URLENCODED,
                    MediaType.APPLICATION_JSON)
                    .post(String.class,form);
            
            ObjectMapper mapper = new ObjectMapper();
            AuthToken token = mapper.readValue(response, AuthToken.class);
            fireAuthTokenEvent(new AuthTokenEvent(this,sessionId,token));
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t.getMessage());
        }
    }

    void addAuthTokenListener(AuthTokenListener listener)
    {
        if (!authTokenListeners.contains(listener))authTokenListeners.add(listener);
    }
    void removeAuthTokenListener(AuthTokenListener listener)
    {
        if (authTokenListeners.contains(listener))authTokenListeners.remove(listener);
    }
    void removeAllAuthTokenListeners()
    {
        while(authTokenListeners.size() > 0)
        {
            authTokenListeners.remove(0);
        }
    }
    
    @Override
    public void authCodeReceived(AuthCodeEvent evt)
    {
        requestAuthToken(evt.getAuthCode());
    }
    
    @Override
    public void timedOut(EventObject obj)
    {
        status = AuthorizationStatus.TimedOut;
        fireErrorOccured(new AuthTokenEvent(this,this.sessionId,
                new Throwable("Server timed-out waiting for authorization. Please try again.")));

    }
    
    protected void fireAuthTokenEvent(AuthTokenEvent evt)
    {
        status = AuthorizationStatus.TokenReceived;
        for(AuthTokenListener listener:authTokenListeners)
        {
            listener.authTokenReceived(evt);
        }
    }
    protected void fireErrorOccured(AuthTokenEvent evt)
    {
        for(AuthTokenListener listener:authTokenListeners)
        {
            listener.errorOccured(evt);
        }
    }
    
    public BaseSpaceConfiguration getConfig()
    {
        return config;
    }
  
}