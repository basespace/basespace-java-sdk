package com.illumina.basespace.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.BigDecimalDeserializer;
import com.illumina.basespace.util.DateDeserializer;


public class Refund 
{
    @JsonProperty("Status")
    private String status;
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    @JsonProperty("RefundStatus")
    private String refundStatus;
    public String getRefundStatus()
    {
        return refundStatus;
    }
    public void setRefundStatus(String refundStatus)
    {
        this.refundStatus = refundStatus;
    }
    
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateCreated")
    private Date dateCreated;
    public Date getDateCreated()
    {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateUpdated")
    private Date dateUpdated;
    public Date getDateUpdated()
    {
        return dateUpdated;
    }
    public void setDateUpdated(Date dateUpdated)
    {
        this.dateUpdated = dateUpdated;
    }
    
    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }
    
    
    @JsonProperty("Amount")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal amount;
    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
    
    @JsonProperty("AmountOfTax")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal amountOfTax;
    public BigDecimal getAmountOfTax()
    {
        return amountOfTax;
    }
    public void setAmountOfTax(BigDecimal amountOfTax)
    {
        this.amountOfTax = amountOfTax;
    }
    
    
    @JsonProperty("AmountTotal")
    @JsonDeserialize(using=BigDecimalDeserializer.class)
    private BigDecimal amountOfTotal;
    public BigDecimal getAmountOfTotal()
    {
        return amountOfTotal;
    }
    public void setAmountOfTotal(BigDecimal amountOfTotal)
    {
        this.amountOfTotal = amountOfTotal;
    }
    
    @JsonProperty("Products")
    private Product[]products;
    public Product[] getProducts()
    {
        return products;
    }
    public void setProducts(Product[] products)
    {
        this.products = products;
    }
    
    @JsonProperty("PurchaseType")
    private String purchaseType;
    public String getPurchaseType()
    {
        return purchaseType;
    }
    public void setPurchaseType(String purchaseType)
    {
        this.purchaseType = purchaseType;
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
    public void setUser(UserCompact user)
    {
        this.user = user;
    }
    
    @JsonProperty("Application")
    private Application application;
    public Application getApplication()
    {
        return application;
    }
    public void setApplication(Application application)
    {
        this.application = application;
    }
    

    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateRefunded")
    private Date dateRefunded;
    public Date getDateRefunded()
    {
        return dateRefunded;
    }
    public void setDateRefunded(Date dateRefunded)
    {
        this.dateRefunded = dateRefunded;
    }
    
    @JsonProperty("UserRefundedBy")
    private UserCompact userRefundedBy;
    public UserCompact getUserRefundedBy()
    {
        return userRefundedBy;
    }
    public void setUserRefundedBy(UserCompact userRefundedBy)
    {
        this.userRefundedBy = userRefundedBy;
    }

    @JsonProperty("RefundComment")
    private String refundComment;
    public String getRefundComment()
    {
        return refundComment;
    }
    public void setRefundComment(String refundComment)
    {
        this.refundComment = refundComment;
    }
    
    private String refundSecret;
    public String getRefundSecret()
    {
        return refundSecret;
    }
    public void setRefundSecret(String refundSecret)
    {
        this.refundSecret = refundSecret;
    }
}
