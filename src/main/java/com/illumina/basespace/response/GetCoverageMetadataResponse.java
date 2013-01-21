package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.CoverageMetaData;

public class GetCoverageMetadataResponse extends ApiResponse<CoverageMetaData, CoverageMetaData>
{
    @JsonProperty("Response")
    private CoverageMetaData item;

    @Override
    public CoverageMetaData get()
    {
        return item;
    }

}
