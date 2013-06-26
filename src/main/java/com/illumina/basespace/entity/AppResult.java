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
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AppResults is a general term for any result that is produced from an application in BaseSpace. For example, if an app performed a resequencing workflow, the corresponding AppResult would be a collection of aligned reads (stored as BAM files) and variants (stored as VCF files). 
 * @author bking
 *
 */
public class AppResult extends AppResultCompact
{
    
    @JsonProperty("Description")
    private String description;
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    @JsonProperty("HrefFiles")
    private URI hrefFiles;
    /**
     * The location of the files for this appresult
     * @return
     */
    public URI getHrefFiles()
    {
        return hrefFiles;
    }
    public void setHrefFiles(URI hrefFiles)
    {
        this.hrefFiles = hrefFiles;
    }
    
    @JsonProperty("HrefGenome")
    private URI hrefGenome;
    /**
     * The genome to which this appresult was referenced, this field is optional and if it is empty it will not be exposed
     * @return
     */
    public URI getHrefGenome()
    {
        return hrefGenome;
    }
    public void setHrefGenome(URI hrefGenome)
    {
        this.hrefGenome = hrefGenome;
    }
    
    @JsonProperty("AppSession")
    private AppSessionCompact appSession;
    /**
     * Information about the appsession that created this appresult
     * @return
     */
    public AppSessionCompact getAppSession()
    {
        return appSession;
    }
    public void setAppSession(AppSessionCompact appSession)
    {
        this.appSession = appSession;
    }
    
    @JsonProperty("References")
    private Reference[] references;
    /**
     * Shows how and where this AppResult is referenced
     * @return
     */
    public Reference[] getReferences()
    {
        return references;
    }
    public void setReferences(Reference[] references)
    {
        this.references = references;
    }
    @Override
    public String toString()
    {
        return "AppResult [description=" + description + ", hrefFiles=" + hrefFiles + ", hrefGenome=" + hrefGenome
                + ", appSession=" + appSession + ", references=" + Arrays.toString(references) + ", toString()="
                + super.toString() + "]";
    }
    

 
   
    
    
}
