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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.DateDeserializer;

public class RunCompact extends OwnedResource
{
    @JsonProperty("Number")
    private int number;
    public int getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }

    @JsonProperty("ExperimentName")
    private String experimentName;
    public String getExperimentName()
    {
        return experimentName;
    }
    protected void setExperimentName(String experimentName)
    {
        this.experimentName = experimentName;
    }
    @Override
    public String getName()
    {
        return getExperimentName();
    }
    
    @JsonProperty("Status")
    private String status;
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    
    @JsonProperty("ReagentBarcode")
    private String reagentBarcode;
    public String getReagentBarcode()
    {
        return reagentBarcode;
    }
    public void setReagentBarcode(String reagentBarcode)
    {
        this.reagentBarcode = reagentBarcode;
    }
    
    @JsonProperty("FlowcellBarcode")
    private String flowcellBarcode;
    public String getFlowcellBarcode()
    {
        return flowcellBarcode;
    }
    public void setFlowcellBarcode(String flowcellBarcode)
    {
        this.flowcellBarcode = flowcellBarcode;
    }
    
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateModified")
    private Date dateModified;
    public Date getDateModified()
    {
        return dateModified;
    }
    public void setDateModified(Date dateModified)
    {
        this.dateModified = dateModified;
    }
    
    @JsonProperty("TotalSize")
    private long totalSize;
    public long getTotalSize()
    {
        return totalSize;
    }
    public void setTotalSize(long totalSize)
    {
        this.totalSize = totalSize;
    }
    
    
}
