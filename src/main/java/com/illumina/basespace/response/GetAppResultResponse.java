package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.AppResult;
import com.illumina.basespace.entity.AppResultCompact;

public class GetAppResultResponse extends ApiResponse<AppResultCompact, AppResult>
{
    @JsonProperty("Response")
    private AppResult item;

    @Override
    public AppResult get()
    {
        return item;
    }

}
