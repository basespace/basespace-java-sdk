package com.illumina.basespace.property;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.param.QueryParams;

public abstract class Property<T> extends QueryParams
{
    public Property()
    {
        
    }
    
    @JsonProperty("Href")
    private URI href;
    /**
     * Location of the resource in the API
     * @return
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
     * Name of the selected resource
     * @return
     */
    public String getName()
    {
        return name;
    }
    protected void setName(String name)
    {
        this.name = name;
    }

    public Property(String name,String description)
    {
        setName(name);
        this.description = description;
    }
    public Property(String name,String description,T content)
    {
        setName(name);
        this.description = description;
        this.content = content;
    }
    public Property(String name,String description,T[] items)
    {
        setName(name);
        this.description = description;
        this.items = items;
    }
   
    
    @JsonProperty("Type")
    private String type;
    public abstract String getType();

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
    
    @JsonProperty("ItemsDisplayedCount")
    private int itemsDisplayedCount;
    public int getItemsDisplayedCount()
    {
        return itemsDisplayedCount;
    }
    public void setItemsDisplayedCount(int itemsDisplayedCount)
    {
        this.itemsDisplayedCount = itemsDisplayedCount;
    }
    
    @JsonProperty("ItemsTotalCount")
    private int itemsTotalCount;
    public int getItemsTotalCount()
    {
        return itemsTotalCount;
    }
    public void setItemsTotalCount(int itemsTotalCount)
    {
        this.itemsTotalCount = itemsTotalCount;
    }
    
    @JsonProperty("HrefItems")
    private URI hrefItems;
    public URI getHrefItems()
    {
        return hrefItems;
    }
    public void setHrefItems(URI hrefItems)
    {
        this.hrefItems = hrefItems;
    }

    @JsonProperty("Content")
    private T content;
    public T getContent()
    {
        return content;
    }
    public void setContent(T content)
    {
        this.content = content;
    }
    
    @JsonProperty("Items")
    private T[]items;
    public T[] getItems()
    {
        return items;
    }
    public void setItems(T[] items)
    {
        this.items = items;
    }


    
}
