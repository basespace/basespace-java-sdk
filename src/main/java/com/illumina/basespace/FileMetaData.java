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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class FileMetaData
{
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("Expires")
    private Date expires;
    public Date getExpires()
    {
        return expires;
    }
    public void setExpires(Date expires)
    {
        this.expires = expires;
    }
    
    @JsonProperty("HrefContent")
    private String hrefContent;
    public String getHrefContent()
    {
        return hrefContent;
    }
    public void setHrefContent(String hrefContent)
    {
        this.hrefContent = hrefContent;
    }
    
    @JsonProperty("SupportsRange")
    private boolean supportsRange;
    public boolean isSupportsRange()
    {
        return supportsRange;
    }
    public void setSupportsRange(boolean supportsRange)
    {
        this.supportsRange = supportsRange;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expires == null) ? 0 : expires.hashCode());
        result = prime * result + ((hrefContent == null) ? 0 : hrefContent.hashCode());
        result = prime * result + (supportsRange ? 1231 : 1237);
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FileMetaData other = (FileMetaData) obj;
        if (expires == null)
        {
            if (other.expires != null) return false;
        }
        else if (!expires.equals(other.expires)) return false;
        if (hrefContent == null)
        {
            if (other.hrefContent != null) return false;
        }
        else if (!hrefContent.equals(other.hrefContent)) return false;
        if (supportsRange != other.supportsRange) return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "FileContentInfo [expires=" + expires + ", hrefContent=" + hrefContent + ", supportsRange="
                + supportsRange + "]";
    }
    
    
    
    
    
}
