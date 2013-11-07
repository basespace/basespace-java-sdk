package com.illumina.basespace.property;

import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.util.TypeHelper;

public class ProjectReference extends ReferenceProperty<ProjectCompact>
{
    public ProjectReference()
    {
        super();
    }

    public ProjectReference(String name, String description, ProjectCompact content)
    {
        super(name, description, content);

    }

    public ProjectReference(String name, String description)
    {
        super(name, description);
    }

    public ProjectReference(String name, String description, ProjectCompact[] items)
    {
        super(name, description,items);
    } 

    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_PROJECT:TypeHelper.PROPERTY_PROJECT_ARRAY;
    }

}
