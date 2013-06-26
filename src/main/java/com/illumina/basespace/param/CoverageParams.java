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

public class CoverageParams extends PositionalQueryParams
{
    public CoverageParams()
    {
    }
    /**
     * Create new coverage parameters
     * @param startPosition The starting position in the chromosome to search for the variant, can be any number within the range of the chromosome. No default value, must be specified and must be an integer.
     * @param endPosition The ending position in the chromosome to search for the variant, can be any number within the range of the chromosome. No default value, must be specified and must be an integer
     * @param zoomLevel granularity per bin
     */
    public CoverageParams(int startPosition, int endPosition,int zoomLevel)
    {
        super(startPosition,endPosition);
        this.setZoomLevel(zoomLevel);
    }
    
    private int zoomLevel;
    public int getZoomLevel()
    {
        return zoomLevel;
    }
    public void setZoomLevel(int zoomLevel)
    {
        this.zoomLevel = zoomLevel;
    }

    @Override
    public MultivaluedMap<String, String> toMap()
    {
        MultivaluedMap<String,String>rtn = super.toMap();
        rtn.add("zoomLevel",String.valueOf(getZoomLevel()));
        return rtn;
    }
}