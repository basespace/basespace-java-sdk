package com.illumina.basespace.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.param.QueryParams;

public abstract class MultiValueProperty<T> extends QueryParams
{
    public MultiValueProperty()
    {
        
    }  
    
    @JsonProperty("Type")
    private String type;
    public abstract String getType();
    
  
    @JsonProperty("Items")
    private MultiValuePropertyItem<T>[]items;
    public MultiValuePropertyItem<T>[] getItems()
    {
        return items;
    }
    public void setItems(MultiValuePropertyItem<T>[] items)
    {
        this.items = items;
    }
  
}
