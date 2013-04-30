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

/**
 * An application session
 * @author bking
 *
 */
public class AppSession extends BaseSpaceEntity
{
    @JsonProperty("Id")
    private String appSessionId;
    public String getAppSessionId()
    {
        return appSessionId;
    }
    public void setAppSessionId(String appSessionId)
    {
        this.appSessionId = appSessionId;
    }

    
    @JsonProperty("Application")
    private Application application;
    public Application getApplication()
    {
        return application;
    }
    public void setApplication(Application application)
    {
        this.application = application;
    }
    
    @JsonProperty("UserCreatedBy")
    private User userCreatedBy;
    public User getUserCreatedBy()
    {
        return userCreatedBy;
    }

    public void setUserCreatedBy(User userCreatedBy)
    {
        this.userCreatedBy = userCreatedBy;
    }
    
}
