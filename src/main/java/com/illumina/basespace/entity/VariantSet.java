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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.variantset.Contig;
import com.illumina.basespace.entity.variantset.Filter;
import com.illumina.basespace.entity.variantset.Format;
import com.illumina.basespace.entity.variantset.Info;
import com.illumina.basespace.entity.variantset.VariantSetMetadata;

public class VariantSet implements Serializable
{

    @JsonProperty("Metadata")
    private VariantSetMetadata metadata;
    public VariantSetMetadata getMetadata()
    {
        return metadata;
    }
    
    @JsonProperty("Legends")
    private Legends legends;
    public Legends getLegends()
    {
        return legends;
    }

    
    @JsonProperty("Samples")
    private com.illumina.basespace.entity.variantset.Sample[]samples;
    public com.illumina.basespace.entity.variantset.Sample[] getSamples()
    {
        return samples;
    }



    public class Legends
    {
        @JsonProperty("contig")
        private Contig[] contig;
        public Contig[] getContig()
        {
            return contig;
        }
        public void setContig(Contig[] contig)
        {
            this.contig = contig;
        }
        
        @JsonProperty("INFO")
        private Info[]info;
        public Info[] getInfo()
        {
            return info;
        }
        
        @JsonProperty("FORMAT")
        private Format[]format;
        public Format[] getFormat()
        {
            return format;
        }
        
        @JsonProperty("FILTER")
        private Filter[]filter;
        public Filter[] getFilter()
        {
            return filter;
        }
     
    }

  
    
}
