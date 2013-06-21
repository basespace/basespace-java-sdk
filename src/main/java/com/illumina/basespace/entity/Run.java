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

import java.net.URI;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.DateDeserializer;



/**
 * A BaseSpace instrument run
 * @author bking
 *
 */
public class Run extends RunCompact
{
    @JsonProperty("HrefFiles")
    private URI hrefFiles;
    public URI getHrefFiles()
    {
        return hrefFiles;
    }
    public void setHrefFiles(URI hrefFiles)
    {
        this.hrefFiles = hrefFiles;
    }

    @JsonProperty("HrefSamples")
    private URI hrefSamples;
    public URI getHrefSamples()
    {
        return hrefSamples;
    }
    public void setHrefSamples(URI hrefSamples)
    {
        this.hrefSamples = hrefSamples;
    }
    
    @JsonProperty("UserUploadedBy")
    private UserCompact uploadedBy;
    public UserCompact getUploadedBy()
    {
        return uploadedBy;
    }
    public void setUploadedBy(UserCompact uploadedBy)
    {
        this.uploadedBy = uploadedBy;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateUploadCompleted")
    private Date dateUploadCompleted;
    public Date getDateUploadCompleted()
    {
        return dateUploadCompleted;
    }
    public void setDateUploadCompleted(Date dateUploadCompleted)
    {
        this.dateUploadCompleted = dateUploadCompleted;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateUploadStarted")
    private Date dateUploadStarted;
    public Date getDateUploadStarted()
    {
        return dateUploadStarted;
    }
    public void setDateUploadStarted(Date dateUploadStarted)
    {
        this.dateUploadStarted = dateUploadStarted;
    }
    
    @JsonProperty("HrefBaseSpaceUI")
    private URI hrefBaseSpaceUI;
    public URI getHrefBaseSpaceUI()
    {
        return hrefBaseSpaceUI;
    }
    public void setHrefBaseSpaceUI(URI hrefBaseSpaceUI)
    {
        this.hrefBaseSpaceUI = hrefBaseSpaceUI;
    }
    
    
}
