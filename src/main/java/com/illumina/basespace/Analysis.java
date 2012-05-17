package com.illumina.basespace;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A BaseSpace Analysis
 * @author bking
 *
 */
@UriPath("/analyses")
public class Analysis extends BaseSpaceEntity
{
    @JsonProperty("Status")
    private String status;
    
    
    public String getStatus()
    {
        return status;
    }
    protected void setStatus(String status)
    {
        this.status = status;
    }

    @JsonProperty("UserOwnerBy")
    private User userOwnedBy;
    public User getUserOwnedBy()
    {
        return userOwnedBy;
    }
    protected void setUserOwnedBy(User userOwnedBy)
    {
        this.userOwnedBy = userOwnedBy;
    }

    @Override
    public String toString()
    {
        return "Analysis [toString()=" + super.toString() + "]";
    }
    
}
