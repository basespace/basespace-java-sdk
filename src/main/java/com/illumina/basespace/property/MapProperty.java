package com.illumina.basespace.property;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.illumina.basespace.util.TypeHelper;


public class MapProperty extends Property<Map<String,String[]>>
{
    
    public MapProperty()
    {
        
    }
    
    public MapProperty(String name, String description, Map<String, String[]> content)
    {
        super(name, description, content);
    }

    public MapProperty(String name, String description, Map<String, String[]>[] items)
    {
        super(name, description, items);
    }

    public MapProperty(String name, String description)
    {
        super(name, description);
    }
    
    @JsonProperty("ContentMap")
    @JsonSerialize(using=MapPropertySerializer.class)
    private Map<String,String[]> content;
    public Map<String,String[]> getContent()
    {
        return content;
    }
    public void setContent(Map<String,String[]> content)
    {
        this.content = content;
    }

      @Override
    public String getType()
    {
          return this.getContent() != null?TypeHelper.PROPERTY_MAP :TypeHelper.PROPERTY_MAP_ARRAY;
    }

    
    
 
}
