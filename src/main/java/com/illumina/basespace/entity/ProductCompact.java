package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author bking
 *
 */
public class ProductCompact extends ApiResource
{
    @JsonProperty("Quantity")
    private float quantity;
    public float getQuantity()
    {
        return quantity;
    }
    public void setQuantity(float quantity)
    {
        this.quantity = quantity;
    }
    
    
    @JsonProperty("Tags")
    private String[] tags;
    public String[] getTags()
    {
        return tags;
    }
    public void setTags(String[] tags)
    {
        this.tags = tags;
    }
}
