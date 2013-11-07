package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.property.Property;
import com.illumina.basespace.property.PropertyItems;

public class ListPropertiesResponse extends ApiResponse<Property<?>, Property<?>>
{

    @Override
    public Property<?>[] items()
    {
        return itemResponse.items();
    }
    
    
    @JsonProperty("Response")
    private PropertyItems itemResponse;

    @Override
    public QueryParams getMetaData()
    {
        return itemResponse;
    }
}
