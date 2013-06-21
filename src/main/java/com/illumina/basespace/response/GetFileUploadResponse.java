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

package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.response.GetFileUploadResponse.FileUpload;

public class GetFileUploadResponse extends ApiResponse<FileUpload,FileUpload>
{

    @JsonProperty("Response")
    private FileUpload item;

    @Override
    public FileUpload get()
    {
        return item;
    }

    public class FileUpload
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
        
        @JsonProperty("ETag")
        private String tag;
        public String getTag()
        {
            return tag;
        }
        public void setTag(String tag)
        {
            this.tag = tag;
        }
        
        @JsonProperty("Size")
        private long size;
        public long getSize()
        {
            return size;
        }
        public void setSize(long size)
        {
            this.size = size;
        }
        @Override
        public String toString()
        {
            return "FileUpload [number=" + number + ", tag=" + tag + ", size=" + size + "]";
        }
        
        
    }
}
