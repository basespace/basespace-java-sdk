package com.illumina.basespace;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VariantFile extends File
{
    @JsonProperty("HrefVariants")
    private String hrefVariants;
    public String getHrefVariants()
    {
        return hrefVariants;
    }
    protected void setHrefVariants(String hrefVariants)
    {
        this.hrefVariants = hrefVariants;
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
        return "VariantFile [hrefVariants=" + hrefVariants + ", hrefContent=" + hrefContent + ", toString()="
                + super.toString() + "]";
    }

    
    
    
}
