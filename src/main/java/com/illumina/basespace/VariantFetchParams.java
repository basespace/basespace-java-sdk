package com.illumina.basespace;

import javax.ws.rs.core.MultivaluedMap;

public class VariantFetchParams extends FetchParams
{
    public VariantFetchParams()
    {
 
    }
    
    
    
    public VariantFetchParams(int startPosition, int endPosition, ReturnFormat format)
    {
        super();
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.format = format;
    }



    public VariantFetchParams(int limit)
    {
        super(limit);
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

    private ReturnFormat format = ReturnFormat.json;
    public ReturnFormat getFormat()
    {
        return format;
    }
    public void setFormat(ReturnFormat format)
    {
        this.format = format;
    }

    @Override
    public MultivaluedMap<String, String> toMap()
    {
        MultivaluedMap<String,String>rtn = super.toMap();
        rtn.add("StartPos",String.valueOf(getStartPosition()));
        rtn.add("EndPos",String.valueOf(getEndPosition()));
        rtn.add("Format",getFormat().name());
        return rtn;
    }
    
    public static enum ReturnFormat
    {
        json,
        vcf
    }
    
}
