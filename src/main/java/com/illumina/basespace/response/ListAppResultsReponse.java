package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.AppResult;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.param.QueryParams;

public class ListAppResultsReponse extends ApiResponse<AppResultCompact, AppResult>
{
    @JsonProperty("Response")
    private AppResultItems itemResponse;

    @Override
    public AppResultCompact[] items()
    {
        return itemResponse.items;
    }

    @Override
    public QueryParams getQueryMetadata()
    {
        return itemResponse;
    }

    public class AppResultItems extends QueryParams
    {
        @JsonProperty("Items")
        private AppResultCompact[] items;
    }

}
