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

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicResponse
{
    @JsonProperty("ResponseStatus")
    private ResponseStatus responseStatus;
    public ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }
    public void setResponseStatus(ResponseStatus responseStatus)
    {
        this.responseStatus = responseStatus;
    }
    
    @JsonProperty("Notifications")
    private String[]notifications;
    public String[] getNotifications()
    {
        return notifications;
    }
    public void setNotifications(String[] notifications)
    {
        this.notifications = notifications;
    }
    
    
}
