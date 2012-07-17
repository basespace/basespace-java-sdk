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

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoverageRecord  implements Serializable
{
    @JsonProperty("Chrom")
    private String chromosome;
    public String getChromosome()
    {
        return chromosome;
    }
    public void setChromosome(String chromosome)
    {
        this.chromosome = chromosome;
    }
    
    @JsonProperty("StartPos")
    private int startPosition;
    public int getStartPosition()
    {
        return startPosition;
    }
    public void setStartPosition(int startPosition)
    {
        this.startPosition = startPosition;
    }
    
    @JsonProperty("EndPos")
    private int endPosition;
    public int getEndPosition()
    {
        return endPosition;
    }
    public void setEndPosition(int endPosition)
    {
        this.endPosition = endPosition;
    }
    
    @JsonProperty("MeanCoverage")
    private float[]meanCoverage;
    public float[] getMeanCoverage()
    {
        return meanCoverage;
    }
    public void setMeanCoverage(float[] meanCoverage)
    {
        this.meanCoverage = meanCoverage;
    }
    @Override
    public String toString()
    {
        return "CoverageRecord [chromosome=" + chromosome + ", startPosition=" + startPosition + ", endPosition="
                + endPosition 
                + ",totalCoverage=" + meanCoverage.length
                + ", meanCoverage=" + Arrays.toString(meanCoverage)
                + "]";
    }
    
    
}
