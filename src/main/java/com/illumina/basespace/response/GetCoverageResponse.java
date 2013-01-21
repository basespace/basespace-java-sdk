package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.CoverageRecord;

public class GetCoverageResponse extends ApiResponse<CoverageRecord, CoverageRecord>
{
    @JsonProperty("Response")
    private CoverageRecord item;

    @Override
    public CoverageRecord get()
    {
        return item;
    }

}
