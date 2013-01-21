package com.illumina.basespace.infrastructure;

public class NotImplementedException extends BaseSpaceException
{
    @Override
    public String getMessage()
    {
        return "Operation is not implemented for this object type";
    }
    
}
