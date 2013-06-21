/**
* Copyright 2013 Illumina
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

package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.infrastructure.NotImplementedException;
import com.illumina.basespace.param.QueryParams;

/**
 * 
 * @author bking
 *
 * @param <C>
 * @param <F>
 */
public class ApiResponse<C, F>
{
    public F get()
    {
        throw new NotImplementedException();
    }

    public C[] items()
    {
        throw new NotImplementedException();
    }

    public QueryParams getMetaData()
    {
        throw new NotImplementedException();
    }

    @JsonProperty("ResponseStatus")
    private ResponseStatus responseStatus;

    public ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus message)
    {
        this.responseStatus = message;
    }

    @JsonProperty("Notifications")
    private String[] notifications;

    public String[] getNotifications()
    {
        return notifications;
    }

    public void setNotifications(String[] notifications)
    {
        this.notifications = notifications;
    }

}
