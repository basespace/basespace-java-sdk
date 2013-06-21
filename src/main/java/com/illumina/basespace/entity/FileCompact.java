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

public class FileCompact extends ApiResource
{
    public FileCompact()
    {
        super();
    }

    public FileCompact(String id)
    {
        super(id);
    }
    
    @JsonProperty("ContentType")
    private String contentType;
    public String getContentType()
    {
        return contentType;
    }
    protected void setContentType(String contentType)
    {
        this.contentType = contentType;
    }
    

    @JsonProperty("Size")
    private Long size;
    
    public Long getSize()
    {
        return size;
    }
    protected void setSize(Long size)
    {
        this.size = size;
    }
    
    @JsonProperty("Path")
    private String path;

    public String getPath()
    {
        return path;
    }
    protected void setPath(String path)
    {
        this.path = path;
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
    public String toString()
    {
        return "FileCompact [contentType=" + contentType + ", size=" + size + ", path=" + path + ", toString()="
                + super.toString() + "]";
    }
    
    
}
