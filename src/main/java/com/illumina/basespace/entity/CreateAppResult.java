package com.illumina.basespace.entity;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.Jsonable;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CreateAppResult implements Jsonable
{
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("HrefAppSession")
    private URI hrefAppSession;
    
 
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


    public void setHrefAppSession(URI hrefAppSession)
    {
        this.hrefAppSession = hrefAppSession;
    }

    public void setReferences(ReferenceCompact[] references)
    {
        this.references = references;
    }

    @Override
    public String toJson(ConversionContext context)
    {
        try
        {
            return context.getMapper().writeValueAsString(this);
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
}
