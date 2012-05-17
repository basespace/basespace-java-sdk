package com.illumina.basespace;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

/**
 * 
 * @author bking
 *
 */
public final class BaseSpaceSessionManager implements AuthTokenListener
{
    private static BaseSpaceSessionManager singletonObject;
    private List<BaseSpaceSessionListener>sessionListeners;
    private Map<String,AuthenticationPipeline>configMap = Collections.synchronizedMap(new HashMap<String,AuthenticationPipeline>());
    
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

    public void addSessionListener(BaseSpaceSessionListener listener)
    {
        if (sessionListeners == null)
            sessionListeners =  Collections.synchronizedList(new ArrayList<BaseSpaceSessionListener>());
        
        if (!sessionListeners.contains(listener))
            sessionListeners.add(listener);
    }
    public void removeSessionListener(BaseSpaceSessionListener listener)
    {
        if (sessionListeners == null)return;
        if (sessionListeners.contains(listener))
            sessionListeners.remove(listener);
    }
    protected void fireSessionEvent(BaseSpaceSessionEvent evt)
    {
        for(BaseSpaceSessionListener listener:sessionListeners)
        {
            listener.sessionEstablished(evt);
        }
    }
    
      
    public void requestSession(String id,BaseSpaceConfiguration configuration)
    {
        BaseSpaceUtilities.assertNotNull(configuration, "configuration");
        configMap.put(id, new AuthenticationPipeline(id,configuration,this));
    }
    
    
    @Override
    public void authTokenReceived(AuthTokenEvent evt)
    {
        AuthenticationPipeline pipeline = configMap.get(evt.getSessionId());
        URI rootApi = UriBuilder.fromUri( pipeline.getConfig().getApiRootUri()).path(pipeline.getConfig().getVersion()).build();
        BaseSpaceSession session = new DefaultBaseSpaceSession(rootApi,evt.getToken());
        BaseSpaceSessionEvent event = new BaseSpaceSessionEvent(this,evt.getSessionId(),session);
        fireSessionEvent(event);
    }
}
