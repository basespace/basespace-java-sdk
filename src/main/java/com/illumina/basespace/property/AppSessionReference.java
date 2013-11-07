package com.illumina.basespace.property;

import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.util.TypeHelper;

public class AppSessionReference extends ReferenceProperty<AppSessionCompact>
{
    public AppSessionReference()
    {
        super();
    }

    public AppSessionReference(String name, String description, AppSessionCompact content)
    {
        super(name, description, content);

    }

    public AppSessionReference(String name, String description, AppSessionCompact[] items)
    {
        super(name, description,items);
    } 
    

    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_APPSESSION:TypeHelper.PROPERTY_APPSESSION_ARRAY;
    }

}
