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

public class VariantRecord implements Serializable
{
    @JsonProperty("CHROM")
    private String CHROM;
    public String getCHROM()
    {
        return CHROM;
    }
    public void setCHROM(String CHROM)
    {
        this.CHROM = CHROM;
    }

    @JsonProperty("POS")
    private int POS;
    public int getPOS()
    {
        return POS;
    }
    public void setPOS(int POS)
    {
        this.POS = POS;
    }

    @JsonProperty("ID")
    private String[] ids;
    public String[] getIds()
    {
        return ids;
    }
    public void setId(String[] id)
    {
        this.ids = id;
    }

    @JsonProperty("REF")
    private String REF;
    public String getREF()
    {
        return REF;
    }
    public void setREF(String REF)
    {
        this.REF = REF;
    }
    
    @JsonProperty("ALT")
    private String ALT;
    public String getALT()
    {
        return ALT;
    }
    public void setALT(String ALT)
    {
        this.ALT = ALT;
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
    private Info info;
    public Info getInfo()
    {
        return info;
    }
    public void setInfo(Info additionalInfo)
    {
        this.info = additionalInfo;
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

    public static class Info implements Serializable
    {
        @JsonProperty("SNVSB")
        private String[]SNVSB;
        public String[] getSNVSB()
        {
            return SNVSB;
        }
        public void setSNVSB(String[] SNVSB)
        {
            this.SNVSB = SNVSB;
        }
        
        @JsonProperty("SNVHPOL")
        private String[]SNVHPOL;
        public String[] getSNVHPOL()
        {
            return SNVHPOL;
        }
        public void setSNVHPOL(String[] SNVHPOL)
        {
            this.SNVHPOL = SNVHPOL;
        }
        
        
        @JsonProperty("CIGAR")
        private String[] CIGAR;
        public String[] getCIGAR()
        {
            return CIGAR;
        }
        public void setCIGAR(String[] CIGAR)
        {
            this.CIGAR = CIGAR;
        }
        
        @JsonProperty("RU")
        private String[] RU;
        public String[] getRU()
        {
            return RU;
        }
        public void setRU(String[] RU)
        {
            this.RU = RU;
        }
        
        

        @JsonProperty("REFREP")
        private String[]REFREP;
        public String[] getREFREP()
        {
            return REFREP;
        }
        public void setREFREP(String[] REFREP)
        {
            this.REFREP = REFREP;
        }
        
        @JsonProperty("IDREP")
        private String[]IDREP;
        public String[] getIDREP()
        {
            return IDREP;
        }
        public void setIDREP(String[] IDREP)
        {
            this.IDREP = IDREP;
        }
        
    }

    public static class SampleFormat implements Serializable
    {
        @JsonProperty("SAMPLE")
        private SampleData sampleData;
        public SampleData getSample()
        {
            return sampleData;
        }
        public void setSample(SampleData sampleData)
        {
            this.sampleData = sampleData;
        }
    }

    public static class SampleData implements Serializable
    {
        @JsonProperty("GT")
        private String GT;
        public String getGT()
        {
            return GT;
        }
        public void setGT(String GT)
        {
            this.GT = GT;
        }

        @JsonProperty("GQ")
        private String GQ;
        public String getGQ()
        {
            return GQ;
        }
        public void setGQ(String GQ)
        {
            this.GQ = GQ;
        }
        
        @JsonProperty("GQX")
        private String GQX;
        public String getGQX()
        {
            return GQX;
        }
        public void setGQX(String gqx)
        {
            this.GQX = gqx;
        }

        @JsonProperty("DP")
        private String DP;
        public String getDP()
        {
            return DP;
        }
        public void setDP(String DP)
        {
            this.DP = DP;
        }

        @JsonProperty("DPF")
        private String DPF;
        public String getDPF()
        {
            return DPF;
        }
        public void setDPF(String DPF)
        {
            this.DPF = DPF;
        }

        @JsonProperty("AD")
        private String AD;
        public String getAD()
        {
            return AD;
        }
        public void setAd(String AD)
        {
            this.AD = AD;
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
        

    }
    
}