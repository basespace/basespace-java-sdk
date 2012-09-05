/**
* Copyright 2012 Illumina
* 
 * Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*    http://www.apache.org/licenses/LICENSE-2.0
* 
 *  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

package com.illumina.basespace;

import javax.ws.rs.core.MultivaluedMap;

public class VariantFetchParams extends FetchParams
{
    public VariantFetchParams()
    {
 
    }
    
    public VariantFetchParams(int startPosition, int endPosition,int offset,int limit)
    {
        super();
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.setOffSet(offset);
        this.setLimit(limit);
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
