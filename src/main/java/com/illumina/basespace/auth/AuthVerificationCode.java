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
public class AuthVerificationCode
{
    @JsonProperty("verification_uri")
    private String verificationUri;
    public String getVerificationUri()
    {
        return verificationUri;
    }
    public void setVerificationUri(String verificationUri)
    {
        this.verificationUri = verificationUri;
    }
    
    @JsonProperty("verification_with_code_uri")
    private String verificationWithCodeUri;
    public String getVerificationWithCodeUri()
    {
        return verificationWithCodeUri;
    }
    public void setVerificationWithCodeUri(String verificationWithCodeUri)
    {
        this.verificationWithCodeUri = verificationWithCodeUri;
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
    
    @JsonProperty("user_code")
    private String userCode;
    public String getUserCode()
    {
        return userCode;
    }
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }
    
    @JsonProperty("device_code")
    private String deviceCode;
    public String getDeviceCode()
    {
        return deviceCode;
    }
    public void setDeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }
    
    @JsonProperty("interval")
    private int interval;
    public int getInterval()
    {
        return interval;
    }
    public void setInterval(int interval)
    {
        this.interval = interval;
    }
    @Override
    public String toString()
    {
        return "AuthVerificationCode [verificationUri=" + verificationUri + ", verificationWithCodeUri="
                + verificationWithCodeUri + ", expiresIn=" + expiresIn + ", userCode=" + userCode + ", deviceCode="
                + deviceCode + ", interval=" + interval + "]";
    }
    
    
    
}
