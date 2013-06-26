/**
* Copyright 2013 Illumina
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
    /**
     * The starting position in the chromosome to search for the variant, can be any number within the range of the chromosome. No default value, must be specified and must be an integer.
     * @param startPosition
     */
    public void setStartPosition(int startPosition)
    {
        this.startPosition = startPosition;
    }
  
    private int endPosition;
    public int getEndPosition()
    {
        return endPosition;
    }
    /**
     * The ending position in the chromosome to search for the variant, can be any number within the range of the chromosome. No default value, must be specified and must be an integer.
     * @param endPosition
     */
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
