package com.illumina.basespace;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An Application Result
 * @author bking
 */
@UriPath("/appresults")
public class AppResult extends BaseSpaceEntity
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
    
    @JsonProperty("UserOwnedBy")
    private User userOwnedBy;
    public User getUserOwnedBy()
    {
        return userOwnedBy;
    }
    protected void setUserOwnedBy(User userOwnedBy)
    {
        this.userOwnedBy = userOwnedBy;
    }
    
    @JsonProperty("StatusSummary")
    private String statusSummary;
    public String getStatusSummary()
    {
        return statusSummary;
    }
    public void setStatusSummary(String statusSummary)
    {
        this.statusSummary = statusSummary;
    }
}
