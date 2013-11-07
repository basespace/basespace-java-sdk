package com.illumina.basespace.property;

import com.illumina.basespace.util.TypeHelper;

public class StringMultiValueProperty extends MultiValueProperty<String>
{
    public StringMultiValueProperty()
    {
        
    }

    @Override
    public String getType()
    {
        return TypeHelper.PROPERTY_STRING_ARRAY;
    }


    

}
