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
import com.illumina.basespace.infrastructure.CreatableResource;
import com.illumina.basespace.util.ValidationHelper;
import com.sun.jersey.api.representation.Form;

public class AppSessionCompact extends OwnedResource implements CreatableResource 
{
    @JsonProperty("UserCreatedBy")
    private User userCreatedBy;
    public User getUserCreatedBy()
    {
        return userCreatedBy;
    }
    
    
    @JsonProperty("Status")
    private String status;
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
    public ApplicationCompact getApplication()
    {
        return application;
    }
    public void setApplication(ApplicationCompact application)
    {
        this.application = application;
    }
    

    public Form toForm()
    {
        ValidationHelper.assertNotNull(getStatus(), "Status");
        ValidationHelper.assertNotNull(getStatusSummary(), "Status Summary");
        Form rtn = new Form();
        rtn.add("status",getStatus());
        rtn.add("statussummary",getStatusSummary());
        return rtn;
    }

    public String toJson(ConversionContext context)
    {
        return null;
    }
}
