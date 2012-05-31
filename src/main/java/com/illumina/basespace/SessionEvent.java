package com.illumina.basespace;

import java.util.EventObject;

public class SessionEvent extends EventObject
{
    private String sessionId;
    private Throwable throwable;
    
    
    public SessionEvent(Object source,String sessionId)
    {
        super(source);
        this.sessionId = sessionId;
    }
    
    public SessionEvent(Object source,String sessionId,Throwable throwable)
    {
        this(source,sessionId);
        this.throwable = throwable;
    }
    
    /**
     * Get the Throwable that occurred during the operation, if any
     * @return Throwable that occurred, or null
     */
    public Throwable getThrowable()
    {
        return throwable;
    }
    
    /**
     * Get the session id associated with this event
     * @return the session id
     */
    public String getSessionId()
    {
        return sessionId;
    }
}
