package com.illumina.basespace.entity;

import java.net.URI;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.infrastructure.ConversionContext;

/**
 * An Application Result
 * @author bking
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
    private AppSession appSession;
    public AppSession getAppSession()
    {
        return appSession;
    }
    public void setAppSession(AppSession appSession)
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
    
    @Override
    public String toJson(ConversionContext context)
    {
        try
        {
            CreateUpdateAppResult var = new CreateUpdateAppResult();
            var.setName(this.getName());
            var.setDescription(this.getDescription());
            var.setHrefAppSession(context.getApiConfiguration().getVersion() + "/appsessions/" + getAppSession().getAppSessionId());
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
    
    
    
}
