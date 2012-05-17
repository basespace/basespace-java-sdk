package com.illumina.basespace;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
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
    
    public AuthenticationPipeline(String sessionId,BaseSpaceConfiguration config,AuthTokenListener authTokenListener)
    {
        this.sessionId = sessionId;
        this.config = config;
        this.localServer = "http://localhost:" + String.valueOf(config.getAuthCodeListenerPort());
        addAuthTokenListener(authTokenListener);
        startServer();
        requestAuthorization();
    }
    
    
    private void startServer()
    {
        httpServer = new HttpServer(getConfig().getAuthCodeListenerPort());
        httpServer.addAuthCodeListener(this);
        new Thread(httpServer).start();
    }
    
    private void requestAuthorization()
    {
        ClientConfig config = new DefaultClientConfig();
        addSSLProperties(config);
        Client client = Client.create(config);
        
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("client_id",getConfig().getClientId());
        params.add("redirect_uri",localServer);
        params.add("response_type","code" );
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
    
    private void addSSLProperties(ClientConfig config)
    {
        try
        {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager trustManager = new X509TrustManager()
            {
                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                {
                    
                }
    
                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                {
                }
    
                @Override
                public X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }
                
            };
            HostnameVerifier verifier = new HostnameVerifier()
            {
                @Override
                public boolean verify(String arg0, SSLSession arg1)
                {
                    return true;
                }
            };
            sslContext.init(null, new TrustManager[]{trustManager},new SecureRandom());
            config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
                    new HTTPSProperties(verifier,sslContext));
        }
        catch(Throwable t)
        {
            
            
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
    
    protected void fireAuthTokenEvent(AuthTokenEvent evt)
    {
        for(AuthTokenListener listener:authTokenListeners)
        {
            listener.authTokenReceived(evt);
        }
    }
    
    public BaseSpaceConfiguration getConfig()
    {
        return config;
    }
     
}
