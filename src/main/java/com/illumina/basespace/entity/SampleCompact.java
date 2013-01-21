package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleCompact extends UserOwnedResource
{
    @JsonProperty("SampleId")
    protected String sampleId;
    public String getSampleId()
    {
        return sampleId;
    }
    public void setSampleId(String sampleId)
    {
        this.sampleId = sampleId;
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
        return "SampleCompact [sampleId=" + sampleId + ", status=" + status + ", statusSummary=" + statusSummary
                + ", toString()=" + super.toString() + "]";
    }
    
    
}
