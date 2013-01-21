package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.VariantRecord;
import com.illumina.basespace.param.QueryParams;

public class ListVariantsResponse extends ApiResponse<VariantRecord, VariantRecord>
{
    @Override
    public VariantRecord[] items()
    {
        return itemResponse.items;
    }

    @JsonProperty("Response")
    private VariantItems itemResponse;

    @Override
    public QueryParams getQueryMetadata()
    {
        return itemResponse;
    }

    public class VariantItems extends QueryParams
    {
        @JsonProperty("Items")
        private VariantRecord[] items;
    }

}
