package com.illumina.basespace.property;

import com.illumina.basespace.util.TypeHelper;


public class StringProperty extends Property<String>
{
 
    public StringProperty()
    {
        
    }

    public StringProperty(String name, String description, String content)
    {
        super(name, description, content);
    }

    public StringProperty(String name, String description, String[] items)
    {
        super(name, description, items);
    }

    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_STRING:TypeHelper.PROPERTY_STRING_ARRAY;
    }

}
