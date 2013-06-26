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
 * Samples are the result of demultiplexing the output of a sequencing instrument flow cell run. A Sample in BaseSpace contains metadata about how it was produced (i.e. from the sample sheet) and a collection of Files representing all the reads in compressed FASTQ format.
 * @author bking
 *
 */
public class Sample extends SampleCompact
{
    @JsonProperty("HrefGenome")
    private URI hrefGenome;
    /**
     * The genome to which this sample was referenced, this field is optional and if it is empty it will not be exposed
     * @return
     */
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
    /**
     * The name of the run from which this sample was taken
     * @return
     */
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
    /**
     * The sample number of this sample within the project
     * @return
     */
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
    /**
     * The location of the files for this sample
     * @return
     */
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
    /**
     * Information about the appsession that created this sample
     * @return
     */
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
    /**
     * Designates whether or not the read is a paired end read
     * @return
     */
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
    /**
     * The length of the first Read, the number of bases
     * @return
     */
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
    /**
     * The length of the second Read, the number of bases
     * @return
     */
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
    /**
     * The number of Reads for this Sample
     * @return
     */
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
    /**
     * The number of Reads that have passed filters
     * @return
     */
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
    /**
     * The Reference field shows this Sample's relation to other Samples or AppResults in BaseSpace
     * @return
     */
    public Reference[] getReferences()
    {
        return references;
    }
    public void setReferences(Reference[] references)
    {
        this.references = references;
    }

    
    
    
    
}
