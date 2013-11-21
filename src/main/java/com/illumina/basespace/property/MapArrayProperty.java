package com.illumina.basespace.property;

import com.illumina.basespace.util.TypeHelper;


public class MapArrayProperty extends Property<MapEntry[]>
{
    
    public MapArrayProperty()
    {
        
    }
    
    

    public MapArrayProperty(String name, String description)
    {
        super(name, description);
    }
    


    @Override
    public String getType()
    {
          return TypeHelper.PROPERTY_MAP_ARRAY;
    }


    
 
}
