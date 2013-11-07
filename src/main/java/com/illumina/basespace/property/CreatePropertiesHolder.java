package com.illumina.basespace.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.ApiResource;
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.Jsonable;

public class CreatePropertiesHolder implements Jsonable
{
    @SuppressWarnings("rawtypes")
    public CreatePropertiesHolder(Property<?>[] properties)
    {
        super();
        for(int i = 0; i < properties.length; i++)
        {
            if (ReferenceProperty.class.isAssignableFrom(properties[i].getClass()))
            {
                final ReferenceProperty<?> original = (ReferenceProperty<?>) properties[i];
                ReferenceProperty<?> substitution = new ReferenceProperty(original.getName(),
                        original.getDescription())
                {

                    @JsonProperty("Items")
                    public String[] getItemPaths()
                    {
                        return original.getItemPaths();
                    }
                    
                    @JsonProperty("Content")
                    public String getContent()
                    {
                        return getItemPaths()[0];
                    }

                    public ApiResource[] getItems()
                    {
                        return null;
                    }

                    @Override
                    public String getType()
                    {
                        return original.getType();
                    }
                };
                properties[i] = substitution;
            }
        }
        this.properties = properties;
    }


    @JsonProperty("Properties")
    private Property<?>[]properties;
    public Property<?>[] getProperties()
    {
        return properties;
    }
    public void setProperties(Property<?>[] properties)
    {
        this.properties = properties;
    }
    
    
    @Override
    public String toJson(ConversionContext context)
    {
        try
        {
            return context.getMapper().writeValueAsString(this);
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    
}
