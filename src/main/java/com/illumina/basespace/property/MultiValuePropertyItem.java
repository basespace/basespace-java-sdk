package com.illumina.basespace.property;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultiValuePropertyItem<T>
{
    public MultiValuePropertyItem()
    {
        
    }
    
    @JsonProperty("Id")
    private String id;
    
    @JsonProperty("Content")
    private T content;

    public String getId()
    {
        return id;
    }
    public T getContent()
    {
        return content;
    }
}
