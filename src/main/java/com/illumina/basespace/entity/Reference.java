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
package com.illumina.basespace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A reference point between one or more BaseSpace entities
 * @author bking
 *
 */
public class Reference extends ReferenceCompact
{
    @JsonProperty("Href")
    private String href;
    public String getHref()
    {
        return href;
    }
    public void setHref(String href)
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
    @Override
    public String toString()
    {
        return "Reference [href=" + href + ", content=" + content + ", toString()=" + super.toString() + "]";
    }
 
    

    
    
    
    
}
