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

import java.io.Serializable;
import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author bking
 *
 */
public abstract class ApiResource implements Serializable
{
    public ApiResource(String id)
    {
        if (id == null)throw new IllegalArgumentException("Null id used for constructor");
        this.id = id;
    }
    
    public ApiResource()
    {
        
    }
    
    @JsonProperty("Id")
    private String id;
    /**
     * Get the unique id associated with this entity
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    protected void setId(String id)
    {
        this.id = id;
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
    @Override
    public String toString()
    {
        return "ApiResource [Id=" + id + ", href=" + href + ", name=" + name + "]";
    }
    
}
