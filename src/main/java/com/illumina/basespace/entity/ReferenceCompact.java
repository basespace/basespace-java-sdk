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

public class ReferenceCompact
{
   
    @JsonProperty("Rel")
    private String relation;
    public String getRelation()
    {
        return relation;
    }
    public void setRelation(String relation)
    {
        this.relation = relation;
    }
    

    
    @JsonProperty("Type")
    private String type;
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    

    @JsonProperty("HrefContent")
    private URI hrefContent;
    public URI getHrefContent()
    {
        return hrefContent;
    }
    public void setHrefContent(URI hrefContent)
    {
        this.hrefContent = hrefContent;
    }

    @JsonProperty("Href")
    private URI href;
    public URI getHref()
    {
        return href;
    }
    public void setHref(URI href)
    {
        this.href = href;
    }
    
    @JsonProperty("Content")
    private Content content;
    public Content getContent()
    {
        return content;
    }
    public void setContent(Content content)
    {
        this.content = content;
    }
    
}
