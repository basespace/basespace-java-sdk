package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.param.QueryParams;

public class ListFilesResponse extends ApiResponse<FileCompact, File>
{
    @JsonProperty("Response")
    private FileItems itemResponse;

    @Override
    public FileCompact[] items()
    {
        return itemResponse.items;
    }

    @Override
    public QueryParams getQueryMetadata()
    {
        return itemResponse;
    }

    public class FileItems extends QueryParams
    {
        @JsonProperty("Items")
        private FileCompact[] items;
    }

}
