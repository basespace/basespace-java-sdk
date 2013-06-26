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

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author bking
 *
 */
public class QueryParams implements Mappable
{
    private static final int ALL = -999;
    public static final String ASCENDING = "Asc";
    public static final String DESCENDING = "Desc";
    
    public QueryParams()
    {
        
    }
    
    public QueryParams(int limit)
    {
        super();
        this.limit = limit;
    }



    public QueryParams(int offset, int limit)
    {
        super();
        this.offset = offset;
        this.limit = limit;
    }

    @JsonProperty("DisplayedCount")
    private int displayedCount;
    public int getDisplayedCount()
    {
        return displayedCount;
    }

    @JsonProperty("TotalCount")
    private int totalCount;
    public int getTotalCount()
    {
        return totalCount;
    }

    @JsonProperty("Offset")
    private int offset;
    public int getOffset()
    {
        return offset;
    }
    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    @JsonProperty("Limit")
    private int limit = ALL;
    public int getLimit()
    {
        return limit;
    }
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    @JsonProperty("SortDir")
    private String sortDir = ASCENDING;
    public String getSortDir()
    {
        return sortDir;
    }
    public void setSortDir(String sortDir)
    {
        this.sortDir = sortDir;
    }

    @JsonProperty("SortBy")
    private String sortBy;
    public String getSortBy()
    {
        return sortBy;
    }
    public void setSortBy(String sortBy)
    {
        this.sortBy = sortBy;
    }   
    
    @JsonIgnore
    private Map<String,String>additionalParams = new HashMap<String,String>();
    public void addParam(String key,String value)
    {
        additionalParams.put(key, value);
    }
    
    @JsonIgnore
    public void addParam(String key,String[] values,String delimiter)
    {
        if (values == null || values.length == 0)return;
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for(String val:values)
        {
            if (!first)sb.append(",");
            sb.append(val);
            first = false;
        }
        additionalParams.put(key, sb.toString());
    }
    
    /**
     * Convert these parameters to a map
     * @return the parameters as a map
     */
    public MultivaluedMap<String, String> toMap()
    {
        MultivaluedMap<String, String>rtn = new MultivaluedMapImpl();
        if (getLimit() != ALL) rtn.add("Limit",String.valueOf(getLimit()));
        if (getSortBy() != null) rtn.add("SortBy", getSortBy());
        rtn.add("SortDir",getSortDir());
        rtn.add("Offset",String.valueOf(getOffset()));
        for(String key:additionalParams.keySet())
        {
            rtn.add(key, additionalParams.get(key));
        }
        return rtn;
    }

    @Override
    public String toString()
    {
        return "QueryParams [displayedCount=" + displayedCount + ", totalCount=" + totalCount + ", offset=" + offset
                + ", limit=" + limit + ", sortDir=" + sortDir + ", sortBy=" + sortBy + "]";
    }

    
}
