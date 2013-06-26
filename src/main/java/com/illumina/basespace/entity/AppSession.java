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
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The instance of an application running on a set of Samples, Runs, Files, Appresults, or Projects. This resource gives information about the status of an application, which will be working on one of the above resource types. The id of a particular app session may be found within an AppResult or Sample, or will be assigned upon triggering an application. If an application has access to a given Sample or AppResult, it also has access to the associated AppSession which provides additional metadata. The app will see a reference to an AppSession for each Sample and AppResult in the API.
 * @author bking
 *
 */
public class AppSession extends AppSessionCompact
{
    @JsonProperty("OriginatingUri")
    private URI originatingUri;
    public URI getOriginatingUri()
    {
        return originatingUri;
    }
    public void setOriginatingUri(URI originatingUri)
    {
        this.originatingUri = originatingUri;
    }
    
    
    @JsonProperty("References")
    private ReferenceCompact[]references;
    /**
     * The resources that are referenced by the appsession 
     * @return
     */
    public ReferenceCompact[] getReferences()
    {
        return references;
    }
    public void setReferences(ReferenceCompact[] references)
    {
        this.references = references;
    }
    
    @JsonProperty("AuthorizationCode")
    private String authorizationCode;
    public String getAuthorizationCode()
    {
        return authorizationCode;
    }
    public void setAuthorizationCode(String authorizationCode)
    {
        this.authorizationCode = authorizationCode;
    }
    @Override
    public String toString()
    {
        return "AppSession [originatingUri=" + originatingUri + ", references=" + Arrays.toString(references)
                + ", authorizationCode=" + authorizationCode + ", toString()=" + super.toString() + "]";
    }
    
    

}
