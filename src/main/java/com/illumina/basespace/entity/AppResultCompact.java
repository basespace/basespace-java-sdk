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

/**
 * 
 * @author bking
 *
 */
public class AppResultCompact extends OwnedResource
{

    public AppResultCompact()
    {
        super();
    }

    public AppResultCompact(String id)
    {
        super(id);
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
    
    @JsonProperty("StatusSummary")
    private String statusSummary;
    public String getStatusSummary()
    {
        return statusSummary;
    }
    public void setStatusSummary(String statusSummary)
    {
        this.statusSummary = statusSummary;
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

    @Override
    public String toString()
    {
        return "AppResultCompact [status=" + status + ", statusSummary=" + statusSummary + ", toString()="
                + super.toString() + "]";
    }    
    
    
    
}
