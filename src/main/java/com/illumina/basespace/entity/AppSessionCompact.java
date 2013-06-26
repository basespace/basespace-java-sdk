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

package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.Jsonable;

/**
 * The instance of an application running on a set of Samples, Runs, Files, Appresults, or Projects. This resource gives information about the status of an application, which will be working on one of the above resource types. The id of a particular app session may be found within an AppResult or Sample, or will be assigned upon triggering an application. If an application has access to a given Sample or AppResult, it also has access to the associated AppSession which provides additional metadata. The app will see a reference to an AppSession for each Sample and AppResult in the API.
 * @author bking
 *
 */
public class AppSessionCompact extends OwnedResource implements Jsonable 
{
    @JsonProperty("UserCreatedBy")
    private User userCreatedBy;
    public User getUserCreatedBy()
    {
        return userCreatedBy;
    }
    
    
    @JsonProperty("Status")
    private String status;
    /**
     * Status of the AppSession, the Samples or AppResults created by it will inherit this property
     * @return
     */
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    @JsonProperty("StatusSummary")
    private String statusSummary;
    /**
     *  A summary of the status of the appsession
     * @return
     */
    public String getStatusSummary()
    {
        return statusSummary;
    }
    public void setStatusSummary(String statusSummary)
    {
        this.statusSummary = statusSummary;
    }
    
    @JsonProperty("Application")
    private ApplicationCompact application;
    /**
     * The application that created this appsession, includes information about the application
     * @return
     */
    public ApplicationCompact getApplication()
    {
        return application;
    }
    public void setApplication(ApplicationCompact application)
    {
        this.application = application;
    }
    
    public String toJson(ConversionContext context)
    {
        return null;
    }
}
