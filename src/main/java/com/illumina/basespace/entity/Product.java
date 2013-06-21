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
