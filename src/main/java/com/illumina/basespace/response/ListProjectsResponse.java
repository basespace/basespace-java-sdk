package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Project;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.param.QueryParams;

public class ListProjectsResponse extends ApiResponse<ProjectCompact, Project>
{
    @Override
    public ProjectCompact[] items()
    {
        return itemResponse.items;
    }

    @Override
    public QueryParams getQueryMetadata()
    {
        return itemResponse;
    }

    @JsonProperty("Response")
    private ProjectItems itemResponse;

    public class ProjectItems extends QueryParams
    {
        @JsonProperty("Items")
        private ProjectCompact[] items;

    }
}
