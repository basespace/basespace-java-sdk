package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Project;
import com.illumina.basespace.entity.ProjectCompact;

public class GetProjectResponse extends ApiResponse<ProjectCompact, Project>
{
    @JsonProperty("Response")
    private Project item;

    @Override
    public Project get()
    {
        return item;
    }

}
