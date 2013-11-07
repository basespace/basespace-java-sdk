package com.illumina.basespace.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.util.TypeHelper;

public class ProjectReferenceProperty extends ReferenceProperty<ProjectCompact>
{
    public ProjectReferenceProperty(String name, String description, ProjectCompact[] items)
    {
        super(name, description);
        this.items = items;
    }

    @Override
    public ProjectCompact[] getItems()
    {
        return items;
    }
    
    @JsonProperty("Items")
    private ProjectCompact[] items;

    @Override
    public ProjectCompact getContent()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType()
    {
        return TypeHelper.PROPERTY_PROJECT;
    }

}
