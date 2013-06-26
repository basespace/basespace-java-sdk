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

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.BigDecimalDeserializer;
import com.illumina.basespace.util.DateDeserializer;

public class Product extends ProductCompact
{
    @JsonProperty("PurchaseId")
    private String purchaseId;
    public String getPurchaseId()
    {
        return purchaseId;
    }
    public void setPurchaseId(String purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DatePurchased")
    private Date datePurchased;
    public Date getDatePurchased()
    {
        return datePurchased;
    }
    public void setDatePurchased(Date datePurchased)
    {
        this.datePurchased = datePurchased;
    }

    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateExpiration")
    private Date expirationDate;
    public Date getExpirationDate()
    {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }


    @JsonProperty("Price")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal price;
    public BigDecimal getPrice()
    {
        return price;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    
    @JsonProperty("PersistenceStatus")
    private String persistenceStatus;
    public String getPersistenceStatus()
    {
        return persistenceStatus;
    }
    public void setPersistenceStatus(String persistenceStatus)
    {
        this.persistenceStatus = persistenceStatus;
    }
    

    @JsonProperty("ProductIds")
    private String productIds;
    public String getProductIds()
    {
        return productIds;
    }
    public void setProductIds(String productIds)
    {
        this.productIds = productIds;
    }
    
    
    
    
}
