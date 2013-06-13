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

package com.illumina.basespace.file;

import java.io.InputStream;
import java.net.URI;

public interface FileRequestHandler
{
    public void download(com.illumina.basespace.entity.File file,java.io.File destination, DownloadListener listener);
    public void download(final com.illumina.basespace.entity.File file, long fileStart, long len, java.io.File target,long targetStart);
    public InputStream getInputStream(com.illumina.basespace.entity.File file);
    public InputStream getInputStream(com.illumina.basespace.entity.File file,long start,long end);
    public URI getURI(com.illumina.basespace.entity.File file);
}
