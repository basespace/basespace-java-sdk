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

import java.util.EventObject;

import com.illumina.basespace.entity.FileCompact;

/**
 * Event related to a file download from BaseSpace
 * @author bking
 *
 */
public class DownloadEvent extends EventObject
{
    private long currentBytes;
    private long totalBytes;
    private boolean canceled;
    
    public DownloadEvent(FileCompact file,long currentBytes,long totalBytes)
    {
        super(file);
        this.currentBytes = currentBytes;
        this.totalBytes = totalBytes;
    }

    /**
     * Has the download been canceled by a listener
     * @return true if the download has been canceled
     */
    public boolean isCanceled()
    {
        return canceled;
    }

    /**
     * Flags an in-progress download for cancellation. 
     */
    public void cancel()
    {
        this.canceled = true;
    }

    /**
     * Get the number of bytes that have been downloaded
     * @return the number of downloaded bytes
     */
    public long getCurrentBytes()
    {
        return currentBytes;
    }

    /**
     * Get the total size of the file in bytes
     * @return the total size of the file in bytes
     */
    public long getTotalBytes()
    {
        return totalBytes;
    }


    
}
