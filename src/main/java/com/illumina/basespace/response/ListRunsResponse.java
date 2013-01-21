package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Run;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.param.QueryParams;

public class ListRunsResponse extends ApiResponse<RunCompact, Run>
{
    @Override
    public RunCompact[] items()
    {
        return itemResponse.items;
    }

    @JsonProperty("Response")
    private RunItems itemResponse;

    @Override
    public QueryParams getQueryMetadata()
    {
        return itemResponse;
    }

    public class RunItems extends QueryParams
    {
        @JsonProperty("Items")
        private RunCompact[] items;
    }
}
