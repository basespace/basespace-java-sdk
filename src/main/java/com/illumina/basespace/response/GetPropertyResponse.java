package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.property.MultiValueProperty;
import com.illumina.basespace.property.MultiValuePropertyDeserializer;

public class GetPropertyResponse extends ApiResponse<MultiValueProperty, MultiValueProperty>
{
    @JsonProperty("Response")
    @JsonDeserialize(using=MultiValuePropertyDeserializer.class)
    private MultiValueProperty<?> item;

    @Override
    public MultiValueProperty<?> get()
    {
        return item;
    }
    
  
  

}
