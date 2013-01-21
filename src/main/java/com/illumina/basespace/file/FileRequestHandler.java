package com.illumina.basespace.file;

import java.io.InputStream;
import java.net.URI;

public interface FileRequestHandler
{
    public void download(com.illumina.basespace.entity.File file,java.io.File localFolder);
    public void download(final com.illumina.basespace.entity.File file, long fileStart, long len, java.io.File target,long targetStart);
    public InputStream getInputStream(com.illumina.basespace.entity.File file);
    public InputStream getInputStream(com.illumina.basespace.entity.File file,long start,long end);
    public URI getURI(com.illumina.basespace.entity.File file);
}
