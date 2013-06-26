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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.DateDeserializer;

public abstract class OwnedResource extends ApiResource
{
    public OwnedResource()
    {
        super();
    }
    public OwnedResource(String id)
    {
        super(id);
    }

    @JsonProperty("UserOwnedBy")
    private UserCompact userOwnedBy;
    /**
     * Information about the User who owns this resource
     * @return
     */
    public UserCompact getUserOwnedBy()
    {
        return userOwnedBy;
    }
    protected void setUserOwnedBy(UserCompact userOwnedBy)
    {
        this.userOwnedBy = userOwnedBy;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateCreated")
    private Date dateCreated;

    /**
     * When this resource was created
     * @return
     */
    public Date getDateCreated()
    {
        return dateCreated;
    }
    protected void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
    
    
    @Override
    public String toString()
    {
        return "UserOwnedResource [userOwnedBy=" + userOwnedBy + ", dateCreated=" + dateCreated + ", toString()="
                + super.toString() + "]";
    }
    
    

    
}
