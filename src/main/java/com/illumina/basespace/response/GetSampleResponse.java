package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Sample;
import com.illumina.basespace.entity.SampleCompact;

public class GetSampleResponse extends ApiResponse<SampleCompact, Sample>
{
    @JsonProperty("Response")
    private Sample item;

    @Override
    public Sample get()
    {
        return item;
    }

}
