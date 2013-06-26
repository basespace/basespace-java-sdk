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
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.BigDecimalDeserializer;
import com.illumina.basespace.util.DateDeserializer;

public class ApplicationTransaction extends ApiResource
{
    @JsonProperty("Status")
    private String status;
    public String getStatus()
    {
        return status;
    }
    
    @JsonProperty("RefundStatus")
    private String refundStatus;
    public String getRefundStatus()
    {
        return refundStatus;
    }
   
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateCreated")
    private Date dateCreated;
    public Date getDateCreated()
    {
        return dateCreated;
    }

    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateUpdated")
    private Date dateUpdated;
    public Date getDateUpdated()
    {
        return dateUpdated;
    }
    
    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }
    
    @JsonProperty("Amount")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal amount;
    public BigDecimal getAmount()
    {
        return amount;
    }
    
    @JsonProperty("AmountOfTax")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal amountOfTax;
    public BigDecimal getAmountOfTax()
    {
        return amountOfTax;
    }
    
    
    @JsonProperty("AmountTotal")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal amountOfTotal;
    public BigDecimal getAmountOfTotal()
    {
        return amountOfTotal;
    }

    @JsonProperty("Products")
    private Product[]products;
    public Product[] getProducts()
    {
        return products;
    }

    @JsonProperty("PurchaseType")
    private String purchaseType;
    public String getPurchaseType()
    {
        return purchaseType;
    }

    
    @JsonProperty("AppSession")
    private AppSessionCompact appSession;
    public AppSessionCompact getAppSession()
    {
        return appSession;
    }
    public void setAppSession(AppSessionCompact appSession)
    {
        this.appSession = appSession;
    }
    
    @JsonProperty("User")
    private UserCompact user;
    public UserCompact getUser()
    {
        return user;
    }
    
    @JsonProperty("Application")
    private Application application;
    public Application getApplication()
    {
        return application;
    }
    @Override
    public String toString()
    {
        return "Transaction [status=" + status + ", refundStatus=" + refundStatus + ", dateCreated=" + dateCreated
                + ", dateUpdated=" + dateUpdated + ", invoiceNumber=" + invoiceNumber + ", amount=" + amount
                + ", amountOfTax=" + amountOfTax + ", amountOfTotal=" + amountOfTotal + ", products="
                + Arrays.toString(products) + ", purchaseType=" + purchaseType + ", appSession=" + appSession
                + ", user=" + user + ", application=" + application + ", toString()=" + super.toString() + "]";
    }
    
    
    
}
