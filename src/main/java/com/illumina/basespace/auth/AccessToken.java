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

package com.illumina.basespace.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author bking
 *
 */
public class AccessToken
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
    private int expiresIn;
    public int getExpiresIn()
    {
        return expiresIn;
    }
    public void setExpiresIn(int expiresIn)
    {
        this.expiresIn = expiresIn;
    }

    @JsonProperty("error")
    private String error;
    public String getError()
    {
        return error;
    }
    public void setError(String error)
    {
        this.error = error;
    }
    
    @JsonProperty("error_description")
    private String errorDescription;
    public String getErrorDescription()
    {
        return errorDescription;
    }
    public void setErrorDescription(String errorDescription)
    {
        this.errorDescription = errorDescription;
    }
    @Override
    public String toString()
    {
        return "AccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", error=" + error
                + ", errorDescription=" + errorDescription + "]";
    }
    
    
    

    
    
    

    
    
    
}
