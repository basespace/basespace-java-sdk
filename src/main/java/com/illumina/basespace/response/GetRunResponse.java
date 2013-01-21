package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Run;
import com.illumina.basespace.entity.RunCompact;

public class GetRunResponse extends ApiResponse<RunCompact, Run>
{
    @JsonProperty("Response")
    private Run item;

    @Override
    public Run get()
    {
        return item;
    }

}
