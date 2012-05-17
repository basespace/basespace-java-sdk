package com.illumina.basespace;

import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * A set of parameters which limit the scope of a retrieved list
 * @author bking
 *
 */
public class FetchParams
{
    private static final int ALL = -999;
    
    
    private String sortBy;
    private SortDirection sortDirection = SortDirection.Ascending;
    private int limit = ALL;
    
    /**
     * Create fetch params
     * @param limit the maximum number of entities to retrieve
     */
    public FetchParams(int limit)
    {
        super();
        this.limit = limit;
    }
    /**
     * Create fetch params
     * @param sortBy the sort by property name of the target entity
     * @param sortDirection direction to sort
     * @param limit the maximum number of entities to retrieve
     */
    public FetchParams(String sortBy, SortDirection sortDirection, int limit)
    {
        super();
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
        this.limit = limit;
    }
    /**
     * Get the sort by property name
     * @return the sort by name
     */
    public String getSortBy()
    {
        return sortBy;
    }
    /**
     * Set the sort by property name
     * @param sortBy the sort by name
     */
    public void setSortBy(String sortBy)
    {
        this.sortBy = sortBy;
    }
    /**
     * Get the sort direction
     * @return the sort direction
     */
    public SortDirection getSortDirection()
    {
        return sortDirection;
    }
    /**
     * Set the sort direction
     * @param sortDirection the sort direction
     */
    public void setSortDirection(SortDirection sortDirection)
    {
        this.sortDirection = sortDirection;
    }
    /**
     * Get the maximum number of entities to retrieve
     * @return the limit
     */
    public int getLimit()
    {
        return limit;
    }
    /**
     * Set the maximum number of entities to retrieve
     * @param limit the limit
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
    
    private int offSet;
    public int getOffSet()
    {
        return offSet;
    }
    public void setOffSet(int offSet)
    {
        this.offSet = offSet;
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
        rtn.add("SortDir",getSortDirection()==SortDirection.Ascending?"Asc":"Desc");
        if (getOffSet()>0) rtn.add("Offset",String.valueOf(getOffSet()));
        return rtn;
    }
}
