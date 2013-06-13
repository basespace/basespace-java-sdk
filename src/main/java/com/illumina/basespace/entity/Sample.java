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


/**
 * A BaseSpace sample
 * @author bking
 *
 */
public class Sample extends SampleCompact
{
    @JsonProperty("HrefGenome")
    private URI hrefGenome;
    public URI getHrefGenome()
    {
        return hrefGenome;
    }
    public void setHrefGenome(URI hrefGenome)
    {
        this.hrefGenome = hrefGenome;
    }
    
    @JsonProperty("ExperimentName")
    private String experimentName;
    public String getExperimentName()
    {
        return experimentName;
    }
    public void setExperimentName(String experimentName)
    {
        this.experimentName = experimentName;
    }
    
    @JsonProperty("SampleNumber")
    private int sampleNumber;
    public int getSampleNumber()
    {
        return sampleNumber;
    }
    public void setSampleNumber(int sampleNumber)
    {
        this.sampleNumber = sampleNumber;
    }
    
    @JsonProperty("HrefFiles")
    private URI hrefFiles;
    public URI getHrefFiles()
    {
        return hrefFiles;
    }
    public void setHrefFiles(URI hrefFiles)
    {
        this.hrefFiles = hrefFiles;
    }
    
    @JsonProperty("AppSession")
    private AppSessionCompact appSession;
    public AppSessionCompact getAppSession()
    {
        return appSession;
    }
    public void setAppSession(AppSessionCompact appSession)
    {
        this.appSession = appSession;
    }
    
    @JsonProperty("IsPairedEnd")
    private boolean isPairedEnd;
    public boolean isPairedEnd()
    {
        return isPairedEnd;
    }
    public void setPairedEnd(boolean isPairedEnd)
    {
        this.isPairedEnd = isPairedEnd;
    }
    
    @JsonProperty("Read1")
    private int read1;
    public int getRead1()
    {
        return read1;
    }
    public void setRead1(int read1)
    {
        this.read1 = read1;
    }
    
    @JsonProperty("Read2")
    private int read2;
    public int getRead2()
    {
        return read2;
    }
    public void setRead2(int read2)
    {
        this.read2 = read2;
    }
    
    @JsonProperty("NumReadsRaw")
    private int numberOfReadsRaw;
    public int getNumberOfReadsRaw()
    {
        return numberOfReadsRaw;
    }
    public void setNumberOfReadsRaw(int numberOfReadsRaw)
    {
        this.numberOfReadsRaw = numberOfReadsRaw;
    }
    
    @JsonProperty("NumReadsPF")
    private int numberOfReadsPF;
    public int getNumberOfReadsPF()
    {
        return numberOfReadsPF;
    }
    public void setNumberOfReadsPF(int numberOfReadsPF)
    {
        this.numberOfReadsPF = numberOfReadsPF;
    }

    @JsonProperty("References")
    private Reference[] references;
    public Reference[] getReferences()
    {
        return references;
    }
    public void setReferences(Reference[] references)
    {
        this.references = references;
    }

    
    
    
    
}
