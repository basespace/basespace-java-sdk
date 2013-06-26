/**
* Copyright 2013 Illumina
* 
 * Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*    http://www.apache.org/licenses/LICENSE-2.0
* 
 *  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/


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
