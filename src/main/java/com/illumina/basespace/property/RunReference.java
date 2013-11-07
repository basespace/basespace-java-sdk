package com.illumina.basespace.property;

import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.util.TypeHelper;

public class RunReference extends ReferenceProperty<RunCompact>
{
    public RunReference()
    {
        super();
    }

    public RunReference(String name, String description, RunCompact content)
    {
        super(name, description, content);

    }

    public RunReference(String name, String description, RunCompact[] items)
    {
        super(name, description,items);
    }
    

    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_RUN:TypeHelper.PROPERTY_RUN_ARRAY;
    }

}
