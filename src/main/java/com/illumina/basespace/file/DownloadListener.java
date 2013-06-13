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

/**
 * Listener interface related to file download events
 * @author bking
 *
 */
public interface DownloadListener
{
    /**
     * Reports progress during the download of a file
     * @param evt the download event
     */
    public void progress(DownloadEvent evt);
    
    /**
     * The download has been completed
     * @param evt the download event
     */
    public void complete(DownloadEvent evt);
    
    /**
     * The download has been canceled
     * @param evt the download event
     */
    public void canceled(DownloadEvent evt);
}
