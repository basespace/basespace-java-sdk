package com.illumina.basespace.property;

import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.util.TypeHelper;

public class AppResultReference  extends ReferenceProperty<AppResultCompact>
{
    public AppResultReference()
    {
        super();
    }

    public AppResultReference(String name, String description, AppResultCompact content)
    {
        super(name, description, content);

    }

    public AppResultReference(String name, String description)
    {
        super(name, description);
    }

    public AppResultReference(String name, String description, AppResultCompact[] items)
    {
        super(name, description,items);
    }

    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_APPRESULT:TypeHelper.PROPERTY_APPRESULT_ARRAY;
    }
    
    
}
