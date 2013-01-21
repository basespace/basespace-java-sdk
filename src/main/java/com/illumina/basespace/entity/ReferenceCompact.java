package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReferenceCompact
{
    @JsonProperty("Rel")
    private String relation;
    public String getRelation()
    {
        return relation;
    }
    public void setRelation(String relation)
    {
        this.relation = relation;
    }
    
    
    @JsonProperty("Type")
    private String type;
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    

    @JsonProperty("HrefContent")
    private String hrefContent;
    public String getHrefContent()
    {
        return hrefContent;
    }
    protected void setHrefContent(String hrefContent)
    {
        this.hrefContent = hrefContent;
    }
    @Override
    public String toString()
    {
        return "ReferenceCompact [relation=" + relation + ", type=" + type + ", hrefContent=" + hrefContent + "]";
    }
    
    
}
