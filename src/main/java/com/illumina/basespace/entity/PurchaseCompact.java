package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.Jsonable;

public class PurchaseCompact extends ApiResource implements Jsonable
{

    @JsonProperty("Products")
    private ProductCompact[]products;
    public ProductCompact[] getProducts()
    {
        return products;
    }
    public void setProducts(ProductCompact[] products)
    {
        this.products = products;
    }

    @JsonProperty("AppSessionId")
    private String appSessionId;
    public String getAppSessionId()
    {
        return appSessionId;
    }
    public void setAppSessionId(String appsessionId)
    {
        this.appSessionId = appsessionId;
    }
    
    @Override
    public String toJson(ConversionContext context)
    {
        try
        {
            return context.getMapper().writeValueAsString(this);
        }
        catch (Throwable t)
        {
            throw new RuntimeException("Error converting to JSON",t);
        }

    }
    

    

}
