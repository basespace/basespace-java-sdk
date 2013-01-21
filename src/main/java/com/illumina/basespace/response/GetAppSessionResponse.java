package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSession;

public class GetAppSessionResponse extends ApiResponse<AppResultCompact, AppSession>
{
    @JsonProperty("Response")
    private AppSession item;

    @Override
    public AppSession get()
    {
        return item;
    }

}
