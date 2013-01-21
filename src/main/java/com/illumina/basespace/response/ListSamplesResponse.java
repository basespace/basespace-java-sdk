package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Sample;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.param.QueryParams;

public class ListSamplesResponse extends ApiResponse<SampleCompact, Sample>
{
    @Override
    public SampleCompact[] items()
    {
        return itemResponse.items;
    }

    @JsonProperty("Response")
    private SampleItems itemResponse;

    @Override
    public QueryParams getQueryMetadata()
    {
        return itemResponse;
    }

    public class SampleItems extends QueryParams
    {
        @JsonProperty("Items")
        private SampleCompact[] items;
    }

}
