package com.illumina.basespace;

import java.net.URI;

/**
 * Indicates an attempt was made to retrieve a server resource without adequate permissions
 * in the context of the current session user permissions
 * @author bking
 *
 */
public class ForbiddenResourceException extends RuntimeException
{
    private URI uri;
    public ForbiddenResourceException(URI uri)
    {
        this.uri = uri;
    }
    
    public String getMessage()
    {
        return "Resource at " + uri.toString() + " is forbidden. Please check permissions.";
    }
}
