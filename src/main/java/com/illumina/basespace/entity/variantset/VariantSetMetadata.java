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

public class VariantSetMetadata
{

    @JsonProperty("IndelTheta")
    private String IndelTheta;
    public String getIndelTheta()
    {
        return IndelTheta;
    }

    @JsonProperty("fileformat")
    private String fileFormat;
    public String getFileFormat()
    {
        return fileFormat;
    }
    
    @JsonProperty("fileDate")
    private String fileDate;
    public String getFileDate()
    {
        return fileDate;
    }

    @JsonProperty("source")
    private String source;
    public String getSource()
    {
        return source;
    }
    
    @JsonProperty("source_version")
    private String sourceVersion;
    public String getSourceVersion()
    {
        return source;
    }
    
    @JsonProperty("startTime")
    private String startTime;
    public String getStartTime()
    {
        return startTime;
    }
    
    @JsonProperty("cmdline")
    private String cmdLine;
    public String getCmdLine()
    {
        return cmdLine;
    }
    
    @JsonProperty("reference")
    private String reference;
    public String getReference()
    {
        return reference;
    }
    
    @JsonProperty("content")
    private String content;
    public String getContent()
    {
        return content;
    }
    
    @JsonProperty("SnvTheta")
    private String SnvTheta;
    public String getSnvTheta()
    {
        return SnvTheta;
    }
    
    @JsonProperty("MaxDepth_chr1")
    private String MaxDepth_chr1;
    
    @JsonProperty("MaxDepth_chr2")
    private String MaxDepth_chr2;
    
    @JsonProperty("MaxDepth_chr3")
    private String MaxDepth_chr3;
    
    @JsonProperty("MaxDepth_chr4")
    private String MaxDepth_chr4;
    
    @JsonProperty("MaxDepth_chr5")
    private String MaxDepth_chr5;
    
    @JsonProperty("MaxDepth_chr6")
    private String MaxDepth_chr6;
    
    @JsonProperty("MaxDepth_chr7")
    private String MaxDepth_chr7;
    
    @JsonProperty("MaxDepth_chr8")
    private String MaxDepth_chr8;
    
    @JsonProperty("MaxDepth_chr9")
    private String MaxDepth_chr9;
    
    @JsonProperty("MaxDepth_chr10")
    private String MaxDepth_chr10;
    
    @JsonProperty("MaxDepth_chr11")
    private String MaxDepth_chr11;
    
    @JsonProperty("MaxDepth_chr12")
    private String MaxDepth_chr12;
    
    @JsonProperty("MaxDepth_chr13")
    private String MaxDepth_chr13;
    
    @JsonProperty("MaxDepth_chr14")
    private String MaxDepth_chr14;
    
    @JsonProperty("MaxDepth_chr15")
    private String MaxDepth_chr15;
    
    @JsonProperty("MaxDepth_chr16")
    private String MaxDepth_chr16;

    @JsonProperty("MaxDepth_chr17")
    private String MaxDepth_chr17;
    
    @JsonProperty("MaxDepth_chr18")
    private String MaxDepth_chr18;
    
    @JsonProperty("MaxDepth_chr19")
    private String MaxDepth_chr19;   
    
    @JsonProperty("MaxDepth_chr20")
    private String MaxDepth_chr20;      
    
    @JsonProperty("MaxDepth_chr21")
    private String MaxDepth_chr21; 
   
    @JsonProperty("MaxDepth_chr22")
    private String MaxDepth_chr22;
    public String getMaxDepth_chr1()
    {
        return MaxDepth_chr1;
    }

    public String getMaxDepth_chr2()
    {
        return MaxDepth_chr2;
    }

    public String getMaxDepth_chr3()
    {
        return MaxDepth_chr3;
    }

    public String getMaxDepth_chr4()
    {
        return MaxDepth_chr4;
    }

    public String getMaxDepth_chr5()
    {
        return MaxDepth_chr5;
    }

    public String getMaxDepth_chr6()
    {
        return MaxDepth_chr6;
    }

    public String getMaxDepth_chr7()
    {
        return MaxDepth_chr7;
    }

    public String getMaxDepth_chr8()
    {
        return MaxDepth_chr8;
    }

    public String getMaxDepth_chr9()
    {
        return MaxDepth_chr9;
    }

    public String getMaxDepth_chr10()
    {
        return MaxDepth_chr10;
    }

    public String getMaxDepth_chr11()
    {
        return MaxDepth_chr11;
    }

    public String getMaxDepth_chr12()
    {
        return MaxDepth_chr12;
    }

    public String getMaxDepth_chr13()
    {
        return MaxDepth_chr13;
    }

    public String getMaxDepth_chr14()
    {
        return MaxDepth_chr14;
    }

    public String getMaxDepth_chr15()
    {
        return MaxDepth_chr15;
    }

    public String getMaxDepth_chr16()
    {
        return MaxDepth_chr16;
    }

    public String getMaxDepth_chr17()
    {
        return MaxDepth_chr17;
    }

    public String getMaxDepth_chr18()
    {
        return MaxDepth_chr18;
    }

    public String getMaxDepth_chr19()
    {
        return MaxDepth_chr19;
    }

    public String getMaxDepth_chr20()
    {
        return MaxDepth_chr20;
    }

    public String getMaxDepth_chr21()
    {
        return MaxDepth_chr21;
    }

    public String getMaxDepth_chr22()
    {
        return MaxDepth_chr22;
    }
    
    @JsonProperty("MaxDepth_chrM")
    private String MaxDepth_chrM;
    
    @JsonProperty("MaxDepth_chrX")
    private String MaxDepth_chrX;
    
    @JsonProperty("MaxDepth_chrY")
    private String MaxDepth_chrY;
    public String getMaxDepth_chrM()
    {
        return MaxDepth_chrM;
    }

    public String getMaxDepth_chrX()
    {
        return MaxDepth_chrX;
    }

    public String getMaxDepth_chrY()
    {
        return MaxDepth_chrY;
    }

}
