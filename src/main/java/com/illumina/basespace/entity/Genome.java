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

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genome extends GenomeCompact
{
    @JsonProperty("DisplayName")
    private String displayName;
    public String getDisplayName()
    {
        return displayName;
    }
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }
    
    @JsonProperty("Source")
    private String source;
    public String getSource()
    {
        return source;
    }
    public void setSource(String source)
    {
        this.source = source;
    }
    
    @JsonProperty("Build")
    private String build;
    public String getBuild()
    {
        return build;
    }
    public void setBuild(String build)
    {
        this.build = build;
    }
    
    
    @Override
    public String getName()
    {
        return getDisplayName();
    }
    
    
}
