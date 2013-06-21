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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A BaseSpace data file.
 * @author bking
 *
 */
public class File extends FileCompact
{
    @JsonProperty("OriginalScope")
    private String originalScope;
    public String getOriginalScope()
    {
        return originalScope;
    }
    public void setOriginalScope(String originalScope)
    {
        this.originalScope = originalScope;
    }
    
    @JsonProperty("HrefVariants")
    private URI hrefVariants;
    public URI getHrefVariants()
    {
        return hrefVariants;
    }
    protected void setHrefVariants(URI hrefVariants)
    {
        this.hrefVariants = hrefVariants;
    }
    
    @JsonProperty("HrefCoverage")
    private URI hrefCoverage;
    public URI getHrefCoverage()
    {
        return hrefCoverage;
    }
    public void setHrefCoverage(URI hrefCoverage)
    {
        this.hrefCoverage = hrefCoverage;
    }

    @JsonProperty("HrefContent")
    private URI hrefContent;
    public URI getHrefContent()
    {
        return hrefContent;
    }
    protected void setHrefContent(URI hrefContent)
    {
        this.hrefContent = hrefContent;
    }
    
    @JsonProperty("HrefParts")
    private URI hrefParts;
    public URI getHrefParts()
    {
        return hrefParts;
    }
    public void setHrefParts(URI hrefParts)
    {
        this.hrefParts = hrefParts;
    }

    @JsonProperty("UploadStatus")
    private String uploadStatus;
    public String getUploadStatus()
    {
        return uploadStatus;
    }
    public void setUploadStatus(String uploadStatus)
    {
        this.uploadStatus = uploadStatus;
    }
    @Override
    public String toString()
    {
        return "File [originalScope=" + originalScope + ", hrefVariants=" + hrefVariants + ", hrefCoverage="
                + hrefCoverage + ", hrefContent=" + hrefContent + ", uploadStatus=" + uploadStatus + ", toString()="
                + super.toString() + "]";
    }

    
    
    
    
    
    
    
}
