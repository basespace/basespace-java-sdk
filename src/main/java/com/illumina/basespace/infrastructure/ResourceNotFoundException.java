package com.illumina.basespace.infrastructure;

import java.net.URI;

public class ResourceNotFoundException extends BaseSpaceException
{
    public ResourceNotFoundException(URI uri)
    {
        super(null,null,uri,404);
    }
    
    @Override
    public String getMessage()
    {
        return "Resource at " + getUri().toString() + " not found";
    }
}
