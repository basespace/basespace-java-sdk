package com.illumina.basespace;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author bking
 *
 */
class AuthToken extends BaseSpaceEntity
{
    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken()
    {
        return accessToken;
    }
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    @JsonProperty("expires_in")
    private String expiresIn;

    public String getExpiresIn()
    {
        return expiresIn;
    }
    public void setExpiresIn(String expiresIn)
    {
        this.expiresIn = expiresIn;
    }
    @Override
    public String toString()
    {
        return "AuthToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
    }

    

    
    
    

    
    
    
}
