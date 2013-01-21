package com.illumina.basespace.auth;

import com.illumina.basespace.infrastructure.BaseSpaceException;

/**
 * Unauthorized Operation
 * @author bking
 *
 */
public class UnauthorizedException extends BaseSpaceException
{
    @Override
    public String getMessage()
    {
        return "The attempted API operation was unauthorized by BaseSpace. Please verify BaseSpace configuration information.";
    }
}
