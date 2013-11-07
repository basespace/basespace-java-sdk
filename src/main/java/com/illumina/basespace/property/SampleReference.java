package com.illumina.basespace.property;

import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.util.TypeHelper;

public class SampleReference extends ReferenceProperty<SampleCompact>
{
    public SampleReference()
    {
        super();
    }

    public SampleReference(String name, String description, SampleCompact content)
    {
        super(name, description, content);

    }

 
    public SampleReference(String name, String description, SampleCompact[] items)
    {
        super(name, description,items);
    }
    
    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_SAMPLE:TypeHelper.PROPERTY_SAMPLE_ARRAY;
    }

}
