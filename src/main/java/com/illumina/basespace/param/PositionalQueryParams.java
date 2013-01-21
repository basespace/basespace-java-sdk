package com.illumina.basespace.param;

import javax.ws.rs.core.MultivaluedMap;

public class PositionalQueryParams extends QueryParams
{
    public PositionalQueryParams()
    {
        
    }
    
    public PositionalQueryParams(int startPosition, int endPosition)
    {
        super();
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
    
    public PositionalQueryParams(int startPosition, int endPosition,int offset,int limit)
    {
        super(offset,limit);
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    private int startPosition;
    public int getStartPosition()
    {
        return startPosition;
    }
    public void setStartPosition(int startPosition)
    {
        this.startPosition = startPosition;
    }
  
    private int endPosition;
    public int getEndPosition()
    {
        return endPosition;
    }
    public void setEndPosition(int endPosition)
    {
        this.endPosition = endPosition;
    }
    
    
    @Override
    public MultivaluedMap<String, String> toMap()
    {
        MultivaluedMap<String,String>rtn = super.toMap();
        rtn.add("StartPos",String.valueOf(getStartPosition()));
        rtn.add("EndPos",String.valueOf(getEndPosition()));
        return rtn;
    }
    
}
