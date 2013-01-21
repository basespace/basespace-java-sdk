package com.illumina.basespace.entity;

import java.net.URI;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    
    
    
}
