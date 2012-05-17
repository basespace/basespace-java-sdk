package com.illumina.basespace;

import java.util.EventObject;

/**
 * 
 * @author bking
 *
 */
public class BaseSpaceSessionEvent extends EventObject
{
    private BaseSpaceSession session;
    private String sessionId;
    
    public BaseSpaceSessionEvent(Object src,String sessionId,BaseSpaceSession session)
    {
        super(src);
        this.session = session;
        this.sessionId = sessionId;
    }

    public BaseSpaceSession getSession()
    {
        return session;
    }

    public String getSessionId()
    {
        return sessionId;
    }
    
}
