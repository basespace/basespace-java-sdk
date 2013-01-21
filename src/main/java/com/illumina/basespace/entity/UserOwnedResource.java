package com.illumina.basespace.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.infrastructure.NotImplementedException;
import com.illumina.basespace.util.DateDeserializer;
import com.sun.jersey.api.representation.Form;

public abstract class UserOwnedResource extends ApiResource
{
    
    
    public UserOwnedResource()
    {
        super();
    }
    public UserOwnedResource(String id)
    {
        super(id);
    }

    @JsonProperty("UserOwnedBy")
    private UserCompact userOwnedBy;
    public UserCompact getUserOwnedBy()
    {
        return userOwnedBy;
    }
    protected void setUserOwnedBy(UserCompact userOwnedBy)
    {
        this.userOwnedBy = userOwnedBy;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateCreated")
    private Date dateCreated;

    /**
     * Get the date this entity was created
     * @return the create date
     */
    public Date getDateCreated()
    {
        return dateCreated;
    }
    protected void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
    @Override
    public String toString()
    {
        return "UserOwnedResource [userOwnedBy=" + userOwnedBy + ", dateCreated=" + dateCreated + ", toString()="
                + super.toString() + "]";
    }
    
    
    public Form toForm()
    {
        throw new NotImplementedException();
    }
    public String toJson()
    {
        throw new NotImplementedException();
    }
    
    
}
