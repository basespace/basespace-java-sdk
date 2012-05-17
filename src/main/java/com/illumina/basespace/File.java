package com.illumina.basespace;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A BaseSpace data file.
 * @author bking
 *
 */
@UriPath("/files")
public class File extends BaseSpaceEntity
{
    @JsonProperty("ContentType")
    private String contentType;
    public String getContentType()
    {
        return contentType;
    }
    protected void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    @JsonProperty("Size")
    private Long size;
    
    public Long getSize()
    {
        return size;
    }
    protected void setSize(Long size)
    {
        this.size = size;
    }
    
    @JsonProperty("Path")
    private String path;

    public String getPath()
    {
        return path;
    }
    protected void setPath(String path)
    {
        this.path = path;
    }
    @Override
    public String toString()
    {
        return "File [size=" + size + ", path=" + path + ", toString()=" + super.toString() + "]";
    }
    
    
    
}
