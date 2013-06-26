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

package com.illumina.basespace.entity.variantset;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Format
{
    @JsonProperty("Description")
    private String description;

    public String getDescription()
    {
        return description;
    }
    
    @JsonProperty("ID")
    private String ID;
    
    @JsonProperty("Number")
    private String Number;
    
    @JsonProperty("Type")
    private String Type;

    public String getID()
    {
        return ID;
    }

    public String getNumber()
    {
        return Number;
    }

    public String getType()
    {
        return Type;
    }
    
    
}
