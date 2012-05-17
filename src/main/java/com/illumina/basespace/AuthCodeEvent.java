package com.illumina.basespace;

import java.util.EventObject;

/**
 * 
 * @author bking
 *
 */
class AuthCodeEvent extends EventObject
{
    private String authCode;

    public AuthCodeEvent(Object source,String authCode)
    {
        super(source);
        this.authCode = authCode;
    }

    public String getAuthCode()
    {
        return authCode;
    }
    
    

}
