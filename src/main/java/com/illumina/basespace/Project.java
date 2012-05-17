package com.illumina.basespace;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * A BaseSpace project
 * @author bking
 *
 */
@UriPath("/projects")
public class Project extends BaseSpaceEntity
{

    @JsonProperty("UserOwnedBy")
    private User userOwnedBy;
    public User getUserOwnedBy()
    {
        return userOwnedBy;
    }
    protected void setUserOwnedBy(User userOwnedBy)
    {
        this.userOwnedBy = userOwnedBy;
    }

    @Override
    public String toString()
    {
        return "Project [toString()=" + super.toString() + "]";
    }
    
}
