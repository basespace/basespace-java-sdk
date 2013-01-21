package com.illumina.basespace.param;

import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class QueryParams
{
    private static final int ALL = -999;
    public static final String ASCENDING = "asc";
    public static final String DESCENDING = "desc";
    
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
    private int limit;
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
        return rtn;
    }

}
