package com.illumina.basespace.property;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MapEntry
{
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
}
