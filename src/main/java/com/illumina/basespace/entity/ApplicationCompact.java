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

package com.illumina.basespace.entity;

import java.net.URI;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.DateDeserializer;

/**
 * 
 * @author bking
 *
 */
public class ApplicationCompact extends ApiResource
{
    
    @JsonProperty("HrefLogo")
    private URI hrefLogo;
    public URI getHrefLogo()
    {
        return hrefLogo;
    }
    public void setHrefLogo(URI hrefLogo)
    {
        this.hrefLogo = hrefLogo;
    }

    @JsonProperty("HomepageUri")
    private URI homePageUri;
    public URI getHomePageUri()
    {
        return homePageUri;
    }
    public void setHomePageUri(URI homePageUri)
    {
        this.homePageUri = homePageUri;
    }

    @JsonProperty("ShortDescription")
    private String shortDescription;
    public String getShortDescription()
    {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateCreated")
    private Date dateCreated;

    /**
     * Get the date this entity was created
     * @return the create date
     */
    public Date getDateCreated()
    {
        return dateCreated;
    }
    protected void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
    
    @JsonProperty("CompanyName")
    private String companyName;
    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    
}
