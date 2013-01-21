package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileCompact;

public class GetFileResponse extends ApiResponse<FileCompact, File>
{

    @JsonProperty("Response")
    private File item;

    @Override
    public File get()
    {
        return item;
    }

}
