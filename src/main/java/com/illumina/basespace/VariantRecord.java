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
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VariantRecord implements Serializable
{
    @JsonProperty("CHROM")
    private String chromosome;
    public String getChromosome()
    {
        return chromosome;
    }
    public void setChromosome(String chromosome)
    {
        this.chromosome = chromosome;
    }

    @JsonProperty("POS")
    private int position;
    public int getPosition()
    {
        return position;
    }
    public void setPosition(int position)
    {
        this.position = position;
    }

    @JsonProperty("ID")
    private ArrayList<?> ids;
    public ArrayList<?> getIds()
    {
        return ids;
    }
    public void setId(ArrayList<?> id)
    {
        this.ids = id;
    }

    @JsonProperty("REF")
    private String referenceBase;
    public String getReferenceBase()
    {
        return referenceBase;
    }
    public void setReferenceBase(String referenceBase)
    {
        this.referenceBase = referenceBase;
    }
    
    @JsonProperty("ALT")
    private String alternate;
    public String getAlternate()
    {
        return alternate;
    }
    public void setAlternate(String alternate)
    {
        this.alternate = alternate;
    }

    @JsonProperty("QUAL")
    private int qualityScore;
    public int getQualityScore()
    {
        return qualityScore;
    }
    public void setQualityScore(int qualityScore)
    {
        this.qualityScore = qualityScore;
    }

    @JsonProperty("FILTER")
    private String filter;
    public String getFilter()
    {
        return filter;
    }
    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    @JsonProperty("INFO")
    private AdditionalInfo additionalInfo;
    public AdditionalInfo getAdditionalInfo()
    {
        return additionalInfo;
    }
    public void setAdditionalInfo(AdditionalInfo additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }
    
    @JsonProperty("SampleFormat")
    private SampleFormat sampleFormat;
    public SampleFormat getSampleFormat()
    {
        return sampleFormat;
    }
    public void setSampleFormat(SampleFormat sampleFormat)
    {
        this.sampleFormat = sampleFormat;
    }

    public static class AdditionalInfo implements Serializable
    {
        @JsonProperty("VARTYPE_DEL")
        private ArrayList<?> varTypeDels;
        public ArrayList<?> getVarTypeDels()
        {
            return varTypeDels;
        }
        public void setVarTypeDels(ArrayList<?> varTypeDels)
        {
            this.varTypeDels = varTypeDels;
        }

        @JsonProperty("VARTYPE_SNV")
        private ArrayList<?> varTypeSnvs;
        public ArrayList<?> getVarTypeSnvs()
        {
            return varTypeSnvs;
        }
        public void setVarTypeSnvs(ArrayList<?> varTypeSnvs)
        {
            this.varTypeSnvs = varTypeSnvs;
        }

        @JsonProperty("CIGAR")
        private ArrayList<String> cigars;
        public ArrayList<String> getCigars()
        {
            return cigars;
        }
        public void setCigars(ArrayList<String> cigars)
        {
            this.cigars = cigars;
        }

        @JsonProperty("RU")
        private ArrayList<?> rus;
        public ArrayList<?> getRus()
        {
            return rus;
        }
        public void setRus(ArrayList<?> rus)
        {
            this.rus = rus;
        }

        @JsonProperty("REFREP")
        private ArrayList<?> refReps;
        public ArrayList<?> getRefReps()
        {
            return refReps;
        }
        public void setRefReps(ArrayList<?> refReps)
        {
            this.refReps = refReps;
        }

        @JsonProperty("IDREP")
        private ArrayList<?> idReps;
        public ArrayList<?> getIdReps()
        {
            return idReps;
        }
        public void setIdReps(ArrayList<?> idReps)
        {
            this.idReps = idReps;
        }
        @Override
        public String toString()
        {
            return "AdditionalInfo [varTypeDels=" + varTypeDels + ", varTypeSnvs=" + varTypeSnvs + ", cigars=" + cigars
                    + ", rus=" + rus + ", refReps=" + refReps + ", idReps=" + idReps + "]";
        }
        
        
    }

    public static class SampleFormat implements Serializable
    {
        @JsonProperty("SAMPLE")
        private GenotypeInfo genotypeInfo;
        public GenotypeInfo getGenotypeInfo()
        {
            return genotypeInfo;
        }
        public void setGenotypeInfo(GenotypeInfo genotypeInfo)
        {
            this.genotypeInfo = genotypeInfo;
        }
        @Override
        public String toString()
        {
            return "SampleFormat [genotypeInfo=" + genotypeInfo + "]";
        }
        
    }

    public static class GenotypeInfo implements Serializable
    {
        @JsonProperty("GT")
        private String genotype;
        public String getGenotype()
        {
            return genotype;
        }
        public void setGenotype(String genotype)
        {
            this.genotype = genotype;
        }

        @JsonProperty("GQ")
        private String genotypeQuality;
        public String getGenotypeQuality()
        {
            return genotypeQuality;
        }
        public void setGenotypeQuality(String genotypeQuality)
        {
            this.genotypeQuality = genotypeQuality;
        }

        @JsonProperty("DPI")
        private String dpi;
        public String getDpi()
        {
            return dpi;
        }
        public void setDpi(String dpi)
        {
            this.dpi = dpi;
        }

        @JsonProperty("DPU")
        private String dpu;
        public String getDpu()
        {
            return dpu;
        }
        public void setDpu(String dpu)
        {
            this.dpu = dpu;
        }

        @JsonProperty("DPF")
        private String dpf;
        public String getDpf()
        {
            return dpf;
        }
        public void setDpf(String dpf)
        {
            this.dpf = dpf;
        }

        @JsonProperty("AU")
        private String au;
        public String getAu()
        {
            return au;
        }
        public void setAu(String au)
        {
            this.au = au;
        }
        @Override
        public String toString()
        {
            return "GenotypeInfo [genotype=" + genotype + ", genotypeQuality=" + genotypeQuality + ", dpi=" + dpi
                    + ", dpu=" + dpu + ", dpf=" + dpf + ", au=" + au + "]";
        }
    }

    @Override
    public String toString()
    {
        return "VariantRecord [chromosome=" + chromosome + ", position=" + position + ", ids=" + ids
                + ", referenceBase=" + referenceBase + ", alternate=" + alternate + ", qualityScore=" + qualityScore
                + ", filter=" + filter + ", additionalInfo=" + additionalInfo + ", sampleFormat=" + sampleFormat + "]";
    }
    
    
}