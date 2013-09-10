package com.illumina.basespace.entity;

import java.io.Serializable;
import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties implements Serializable
{
    @JsonProperty("DisplayedCount")
    private int displayedCount;
    public int getDisplayedCount()
    {
        return displayedCount;
    }
    public void setDisplayedCount(int displayedCount)
    {
        this.displayedCount = displayedCount;
    }

    @JsonProperty("TotalCount")
    private int totalCount;
    public int getTotalCount()
    {
        return totalCount;
    }
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    @JsonProperty("Href")
    private URI href;
    public URI getHref()
    {
        return href;
    }
    public void setHref(URI href)
    {
        this.href = href;
    }

    @JsonProperty("Items")
    private ApiResource[]items;
    public ApiResource[] getItems()
    {
        return items;
    }
    public void setItems(ApiResource[] items)
    {
        this.items = items;
    }
    
    
    
}
