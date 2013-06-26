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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.Jsonable;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CreateAppResult implements Jsonable
{
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("HrefAppSession")
    private URI hrefAppSession;
    
 
    @JsonProperty("References")
    private ReferenceCompact[]references;
    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public void setHrefAppSession(URI hrefAppSession)
    {
        this.hrefAppSession = hrefAppSession;
    }

    public void setReferences(ReferenceCompact[] references)
    {
        this.references = references;
    }

    @Override
    public String toJson(ConversionContext context)
    {
        try
        {
            return context.getMapper().writeValueAsString(this);
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
}
