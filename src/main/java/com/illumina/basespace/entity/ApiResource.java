package com.illumina.basespace.entity;

import java.io.Serializable;
import java.net.URI;

import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.infrastructure.NotImplementedException;

public abstract class ApiResource implements Serializable
{
    @JsonProperty("Id")
    private String Id;
    
    
    
    public ApiResource(String id)
    {
        if (id == null)throw new IllegalArgumentException("Null id used for constructor");
        Id = id;
    }
    
    public ApiResource()
    {
        
    }
    
    
    /**
     * Get the unique id associated with this entity
     * @return the id
     */
    public String getId()
    {
        return Id;
    }
    protected void setId(String id)
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
    @Override
    public String toString()
    {
        return "ApiResource [Id=" + Id + ", href=" + href + ", name=" + name + "]";
    }
 

    
    
    
    
}
