package com.illumina.basespace;

import java.util.EventObject;

/**
 * 
 * @author bking
 *
 */
class AuthTokenEvent extends EventObject
{
    private AuthToken token;
    private String sessionId;
    
    public AuthTokenEvent(Object source,String sessionId,AuthToken token)
    {
        super(source);
        this.sessionId = sessionId;
        this.token = token;
    }
    public AuthToken getToken()
    {
        return token;
    }
    public String getSessionId()
    {
        return sessionId;
    }
   

}
