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
    protected void fireErrorEvent(BaseSpaceSessionEvent evt)
    {
        for(BaseSpaceSessionListener listener:sessionListeners)
        {
            listener.errorOccurred(evt);
         }
    }
      
    public void requestSession(String id,BaseSpaceConfiguration configuration)
    {
        BaseSpaceUtilities.assertNotNull(configuration, "configuration");
        configMap.put(id, new AuthenticationPipeline(id,configuration,this));
    }
    public AuthorizationStatus getRequestStatus(String id)
    {
        AuthenticationPipeline pipeline = configMap.get(id);
        if (pipeline == null)return AuthorizationStatus.InvalidSessionId;
        return pipeline.getStatus();
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
    @Override
    public void errorOccured(AuthTokenEvent evt)
    {
        BaseSpaceSessionEvent event = new BaseSpaceSessionEvent(this,evt.getSessionId(),evt.getThrowable());
        fireErrorEvent(event);
    }
}
