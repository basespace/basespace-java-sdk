package com.illumina.basespace;

import java.net.URI;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A BaseSpace user
 * @author bking
 *
 */
@UriPath("/users")
public class User extends BaseSpaceEntity
{
    @JsonProperty("Email")
    private String email;

    /**
     * Get the email associated with this user
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }
    protected void setEmail(String email)
    {
        this.email = email;
    }

    @JsonProperty("GravatarUrl")
    private URI gravatarUrl;
    
    /**
     * Get the gravatar URI associated with this user
     * @return the gravatar URI
     */
    public URI getGravatarUrl()
    {
        return gravatarUrl;
    }
    protected void setGravatarUrl(URI gravatarUrl)
    {
        this.gravatarUrl = gravatarUrl;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateLastActive")
    private Date dateLastActive;
    
    /**
     * Get the date this user was last active
     * @return the last active date
     */
    public Date getDateLastActive()
    {
        return dateLastActive;
    }
    protected void setDateLastActive(Date dateLastActive)
    {
        this.dateLastActive = dateLastActive;
    }
    
    @JsonProperty("HrefRuns")
    private URI hrefRuns;
    /**
     * Get the runs URI for this user
     * @return the runs URI
     */
    public URI getHrefRuns()
    {
        return hrefRuns;
    }
    protected void setHrefRuns(URI hrefRuns)
    {
        this.hrefRuns = hrefRuns;
    }

    @JsonProperty("HrefProjects")
    private URI hrefProjects;
    /**
     * Get the projects URI for this user
     * @return the projects URI
     */
    public URI getHrefProjects()
    {
        return hrefProjects;
    }
    protected void setHrefProjects(URI hrefProjects)
    {
        this.hrefProjects = hrefProjects;
    }
    @Override
    public String toString()
    {
        return "User [email=" + email + ", dateLastActive=" + dateLastActive + ", hrefRuns=" + hrefRuns
                + ", hrefProjects=" + hrefProjects + "]";
    }

    
  
 
    
}
