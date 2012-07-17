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

import java.net.URI;
import java.util.EventObject;

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
    private URI uri;
    
    
    public DownloadEvent(Object source,URI uri,long currentBytes,long totalBytes)
    {
        super(source);
        this.currentBytes = currentBytes;
        this.totalBytes = totalBytes;
        this.uri = uri;
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
     * Allows listeners to cancel the download
     * @param canceled if true, will attempt to cancel a download in progress
     */
    public void setCanceled(boolean canceled)
    {
        this.canceled = canceled;
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

    /**
     * Get the BaseSpace file that is being downloaded
     * @return the BaseSpace file
     */
    public URI getURI()
    {
        return uri;
    }
    
    

    
}
