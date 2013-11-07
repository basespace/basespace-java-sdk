package com.illumina.basespace.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.param.QueryParams;

public class PropertyItems extends QueryParams
{
    @JsonProperty("Items")
    @JsonDeserialize(using=PropertyArrayDeserializer.class)
    public Property<?>[] items;
    public Property<?>[] items()
    {
        return items;
    }
    
}
