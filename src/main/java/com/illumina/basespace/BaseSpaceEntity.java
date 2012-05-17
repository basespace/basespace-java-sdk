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

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Common base class for BaseSpace entities
 * @author bking
 *
 */
@JsonIgnoreProperties({ "ResponseStatus", "Notifications" })
abstract class BaseSpaceEntity implements Serializable
{
    protected Logger logger = Logger.getLogger(BaseSpaceEntity.class.getPackage().getName());

    @JsonProperty("Id")
    private Long Id;
    
    /**
     * Get the unique id associated with this entity
     * @return the id
     */
    public Long getId()
    {
        return Id;
    }
    protected void setId(Long id)
    {
        Id = id;
    }
    
    @JsonProperty("Href")
    private URI href;
    /**
     * Get the Href associated with this entity
     * @return the Href
     */
    public URI getHref()
    {
        return href;
    }
    protected void setHref(URI href)
    {
        this.href = href;
    }
    
    
    @JsonProperty("Name")
    private String name;
    /**
     * Get the name associated with this entity
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    protected void setName(String name)
    {
        this.name = name;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateCreated")
    private Date dateCreated;

    /**
     * Get the date this entity was created
     * @return the create date
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
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseSpaceEntity other = (BaseSpaceEntity) obj;
        if (Id == null)
        {
            if (other.Id != null) return false;
        }
        else if (!Id.equals(other.Id)) return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "BaseSpaceEntity [Id=" + Id + ", href=" + href + ", name=" + name + ", dateCreated=" + dateCreated + "]";
    }


    
    

}
