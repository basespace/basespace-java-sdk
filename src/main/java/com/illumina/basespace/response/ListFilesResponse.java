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
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.param.QueryParams;

public class ListFilesResponse extends ApiResponse<FileCompact, File>
{
    @JsonProperty("Response")
    private FileItems itemResponse;

    @Override
    public FileCompact[] items()
    {
        return itemResponse.items;
    }

    @Override
    public QueryParams getMetaData()
    {
        return itemResponse;
    }

    public class FileItems extends QueryParams
    {
        @JsonProperty("Items")
        private FileCompact[] items;
    }

}
