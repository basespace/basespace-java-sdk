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

import java.net.URI;

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
    
    @JsonProperty("HrefSamples")
    private URI hrefSamples;
    public URI getSamplesHref()
    {
        return hrefSamples;
    }
    protected void setSamplesHref(URI hrefSamples)
    {
        this.hrefSamples = hrefSamples;
    }
    
    @JsonProperty("HrefAnalyses")
    private URI hrefAnalyses;
    public URI getAnalysesHref()
    {
        return hrefAnalyses;
    }
    protected void setAnalysesHref(URI hrefAnalyses)
    {
        this.hrefAnalyses = hrefAnalyses;
    }

    @Override
    public String toString()
    {
        return "Project [toString()=" + super.toString() + "]";
    }
    
}
