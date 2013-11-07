package com.illumina.basespace.property;

import java.util.ArrayList;
import java.util.List;

import com.illumina.basespace.entity.ApiResource;
import com.illumina.basespace.util.TypeHelper;

public abstract class ReferenceProperty<T extends ApiResource> extends Property<T>
{
    public ReferenceProperty(String name,String description)
    {
        super(name,description);
    }
    
    public ReferenceProperty()
    {
        super();
    }

    public ReferenceProperty(String name, String description, T content)
    {
        super(name, description, content);
    }

    public ReferenceProperty(String name, String description, T[] items)
    {
        super(name, description, items);
    }


    public String[]getItemPaths()
    {
        List<String>paths = new ArrayList<String>();
        if (getContent() != null)
        {
            paths.add(TypeHelper.INSTANCE.getResourcePath(getContent().getClass(), true) + "/" + getContent().getId());
        }
        else
        {
            for(T val:getItems())
            {
                paths.add(TypeHelper.INSTANCE.getResourcePath(val.getClass(), true) + "/" + val.getId());
            }
        }
        return paths.toArray(new String[paths.size()]);
    }
 

}
