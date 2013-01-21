package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppResultCompact extends UserOwnedResource
{

    public AppResultCompact()
    {
        super();
    }

    public AppResultCompact(String id)
    {
        super(id);
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
    public String toString()
    {
        return "AppResultCompact [status=" + status + ", statusSummary=" + statusSummary + ", toString()="
                + super.toString() + "]";
    }    
    
    
    
}
