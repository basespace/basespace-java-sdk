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
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.CreatableResource;
import com.sun.jersey.api.representation.Form;

/**
 * An Application Result
 * @author bking
 */
public class AppResult extends AppResultCompact implements CreatableResource
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
    

    public String toJson(ConversionContext context)
    {
        try
        {
            CreateUpdateAppResult var = new CreateUpdateAppResult();
            var.setName(this.getName());
            var.setDescription(this.getDescription());
            var.setHrefAppSession(context.getApiConfiguration().getVersion() + "/appsessions/" + getAppSession().getId());
            var.setStatus(this.getStatus());
            if (this.getReferences() != null && this.getReferences().length > 0)
            {
                ReferenceCompact[]crlist = new ReferenceCompact[this.getReferences().length];
                int i = 0;
                for(Reference r:getReferences())
                {
                    ReferenceCompact cr = new ReferenceCompact();
                    cr.setType(r.getType());
                    cr.setHrefContent(r.getHrefContent());
                    cr.setRelation(r.getRelation());
                    crlist[i] = cr;
                    i++;
                }
                var.setReferences(crlist);
            }
            return context.getMapper().writeValueAsString(var);
        }
        catch(Throwable t)
        {
            throw new RuntimeException("Error converting to Json string: " + t.getMessage());
        }
    }
    
    
    private class CreateUpdateAppResult
    {
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Description")
        private String description;
        @JsonProperty("status")
        private String status;
        @JsonProperty("HrefAppSession")
        private String hrefAppSession;
        
     
        @JsonProperty("References")
        private ReferenceCompact[]references;
        public void setName(String name)
        {
            this.name = name;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public void setStatus(String status)
        {
            this.status = status;
        }

        public void setHrefAppSession(String hrefAppSession)
        {
            this.hrefAppSession = hrefAppSession;
        }

        public void setReferences(ReferenceCompact[] references)
        {
            this.references = references;
        }

        
        
        
    }


    public Form toForm()
    {
        return null;
    }
    
    
    
}
