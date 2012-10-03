package com.illumina.basespace;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An Application Result
 * @author bking
 */
@UriPath("/appresults")
public class AppResult extends BaseSpaceEntity
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
    
    
    
}
