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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains metadata for a BaseSpace fetch operation
 * @author bking
 *
 */
@JsonIgnoreProperties({"Items"}) 
public class ItemListMetaData implements Serializable
{
    @JsonProperty("DisplayedCount")
    private int displayedCount;
    
    @JsonProperty("TotalCount")
    private int totalCount;
    
    @JsonProperty("Offset")
    private int offset;
    
    @JsonProperty("Limit")
    private int limit;
    
    @JsonProperty("SortDir")
    private String sortDir;
    
    @JsonProperty("SortBy")
    private String sortBy;
    
    public int getDisplayedCount()
    {
        return displayedCount;
    }

    public void setDisplayedCount(int displayedCount)
    {
        this.displayedCount = displayedCount;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public String getSortDir()
    {
        return sortDir;
    }

    public void setSortDir(String sortDir)
    {
        this.sortDir = sortDir;
    }

    public String getSortBy()
    {
        return sortBy;
    }

    public void setSortBy(String sortBy)
    {
        this.sortBy = sortBy;
    }
}
