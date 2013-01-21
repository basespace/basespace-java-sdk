package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.util.ValidationHelper;
import com.sun.jersey.api.representation.Form;

public class AppSessionCompact extends UserOwnedResource
{
    @JsonProperty("UserCreatedBy")
    private User userCreatedBy;
    public User getUserCreatedBy()
    {
        return userCreatedBy;
    }
    
    
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
    
    @Override
    public Form toForm()
    {
        ValidationHelper.assertNotNull(getStatus(), "Status");
        ValidationHelper.assertNotNull(getStatusSummary(), "Status Summary");
        Form rtn = new Form();
        rtn.add("status",getStatus());
        rtn.add("statussummary",getStatusSummary());
        return rtn;
    }
}
