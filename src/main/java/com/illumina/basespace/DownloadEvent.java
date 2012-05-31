package com.illumina.basespace;

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
    private File file;
    
    public DownloadEvent(Object source,File file,long currentBytes,long totalBytes)
    {
        super(source);
        this.currentBytes = currentBytes;
        this.totalBytes = totalBytes;
        this.file = file;
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
    public File getTheFile()
    {
        return file;
    }
    
    

    
}
