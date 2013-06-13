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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.illumina.basespace.ApiClient;
import com.illumina.basespace.auth.ResourceForbiddenException;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileRedirectMetaData;
import com.illumina.basespace.infrastructure.BaseSpaceException;
import com.illumina.basespace.infrastructure.ClientConnectionProvider;
import com.illumina.basespace.infrastructure.NotImplementedException;
import com.sun.jersey.api.client.WebResource;

public class DefaultFileRequestHandler implements FileRequestHandler
{
    private ClientConnectionProvider connectionProvider;
    private ApiClient apiClient;
    private Map<String,FileRedirectMetaData>downloadMetaDataCache = new Hashtable<String,FileRedirectMetaData>();
    private final Logger logger = Logger.getLogger(DefaultFileRequestHandler.class.getPackage().getName());
    
    
    public DefaultFileRequestHandler(ApiClient apiClient,ClientConnectionProvider connectionProvider)
    {
        this.apiClient = apiClient;
        this.connectionProvider = connectionProvider;
    }

    @Override
    public InputStream getInputStream(File file)
    {
        return getInputStream(file, 0, file.getSize());
    }

    @Override
    public InputStream getInputStream(File file, long start, long end)
    {
        try
        {
            return getInputStreamInternal(file, start, end);
        }
        catch (RuntimeException t)
        {
            throw t;
        }
    }

    @Override
    public URI getURI(File file)
    {
        WebResource resource = connectionProvider.getClient()
                .resource(UriBuilder.fromUri(connectionProvider.getConfiguration().getApiRootUri())
                            .path(connectionProvider.getConfiguration().getVersion()).build()).path("files")
                .path(String.valueOf(file.getId())).path("content");
        return resource.getURI();
    }
    
    protected FileRedirectMetaData getRedirectMetaData(File file)
    {
        FileRedirectMetaData metaData = downloadMetaDataCache.get(file.getId());
        if (metaData == null || new Date().after(metaData.getExpires()))
        {
            logger.warning("Redirect metaData for " + file.getName() + " does not exist or expired...fetching...");
            metaData =  apiClient.getFileRedirectMetaData(file).get();
            downloadMetaDataCache.put(file.getId(),metaData);
        }
        return metaData;
    }

    protected InputStream getInputStreamInternal(File file, long start, long end)
    {
        try
        {
            FileRedirectMetaData metaData = getRedirectMetaData(file);
            InputStream in = connectionProvider.getClient()
                .resource(metaData.getHrefContent())
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .accept(MediaType.TEXT_HTML)
                .accept(MediaType.APPLICATION_XHTML_XML)
                .header("Range","bytes=" + start + "-" + end)
                .get(InputStream.class);
            return in;
        }
        catch(ResourceForbiddenException forbidden)
        {
            downloadMetaDataCache.remove(file.getId());
            throw forbidden;
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    
    @Override
    public void download(File file, long fileStart, long len, java.io.File target, long targetStart)
    {
        if (true)
        {
            throw new NotImplementedException();
        }
        
        FileChannel fc = null;
        RandomAccessFile ras = null;
        InputStream in = null;
        boolean canceled = false;

        final int CHUNK_SIZE = 8192; // for part downloads, reduce the number of
                                     // calls by 1/2.
        long progress = 0;

        try
        {
            if ((fileStart + len) > file.getSize())
            {
                throw new Exception("Invalid download range start(" + fileStart + ") + len (" + len + ") > file size ("
                        + file.getSize() + ")");
            }
            if (target.isDirectory())
            {
                if (!target.exists() && !target.mkdirs())
                {
                    throw new IllegalArgumentException("Unable to create local folder " + target.toString());
                }
                target = new java.io.File(target, new String(file.getName()));
            }

            in = getInputStream(file, fileStart, fileStart + len - 1); // These
                                                                       // are
                                                                       // *positions*;
                                                                       // end at
                                                                       // len
                                                                       // minus
                                                                       // 1

            // Note: It sounds simple to use a FileChannel to sparsely write a
            // file. It isn't.
            // The code is sensitive to the right combination of usage. I use a
            // RandomAccessStream
            // and aquire a FileChannel from it. It did not work if you just use
            // the RAS. It did not work
            // if you try to open the FC w/o the RAS. It did not work if you use
            // a RAS:FC but allocate
            // a ByteBuffer once, call bb.clear, bb.put, then fc.write(bb). This
            // caused the FC to write
            // garbage for CHUNK_SIZE-bb.length.
            // Thus we now always use the ByteBuffer.wrap and the FC from a RAS.

            ras = new RandomAccessFile(target, "rw");
            fc = ras.getChannel(); // Open for WRITE default.
            fc.position(targetStart); // Place the position at our place in the
                                      // file
            fc.force(true);
            progress = 0;
            byte[] outputByte = new byte[CHUNK_SIZE];
            int bytesRead = 0;
            readTheFile: while ((bytesRead = in.read(outputByte, 0, CHUNK_SIZE)) != -1)
            {
                fc.write(ByteBuffer.wrap(outputByte, 0, bytesRead));
                progress += bytesRead;

            }
            fc.close();
            ras.close();
            in.close();
            fc = null;
            ras = null;
            in = null;
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (Throwable t)
        {
            throw new RuntimeException("Error during file download", t);
        }
        finally
        {
            try
            {
                if (fc != null) fc.close();
            }
            catch (Throwable t)
            {
            }
            try
            {
                if (in != null) in.close();
            }
            catch (Throwable t)
            {
            }
            try
            {
                if (ras != null) ras.close();
            }
            catch (Throwable t)
            {
            }
            if (canceled)
            {
                if (target != null) target.delete();
            }
        }

    }

     public void download(File file, java.io.File destination, DownloadListener listener)
     {
        FileOutputStream fos = null;
        InputStream in = null;
        boolean canceled = false;
        long progress = 0;
        try
        {
            if (destination.isDirectory())
            {
                if (!destination.exists() && !destination.mkdirs())
                {
                    throw new IllegalArgumentException("Unable to create local folder " + destination.toString());
                }
                destination = new java.io.File(destination,file.getName());
            }
            
            final int CHUNK_SIZE = 4096;
            in = getInputStream(file);
            fos = new FileOutputStream(destination);
           
            byte[] outputByte = new byte[CHUNK_SIZE];
            int bytesRead = 0;
            readTheFile: while (!canceled && (bytesRead = in.read(outputByte, 0, CHUNK_SIZE)) != -1)
            {
                fos.write(outputByte, 0, bytesRead);
                progress += bytesRead;
                if (listener != null)
                {
                    DownloadEvent evt = new DownloadEvent(file,progress,file.getSize());
                    listener.progress(evt);
                    canceled = evt.isCanceled();
                }
            }
            fos.close();
            in.close();
            fos = null;
            in = null;
            if (listener != null)
                listener.complete(new DownloadEvent(file,progress,file.getSize()));
        }
        catch (BaseSpaceException bs)
        {
            throw bs;
        }
        catch (Throwable t)
        {
            throw new RuntimeException("Error during file download", t);
        }
        finally
        {
            try
            {
                if (in != null) in.close();
            }
            catch (Throwable t)
            {
            }
            try
            {
                if (fos != null) fos.close();
            }
            catch (Throwable t)
            {
            }
            if (canceled)
            {
                if (destination != null) 
                {
                    destination.delete();
                    if (listener !=  null)
                        listener.canceled(new DownloadEvent(file,progress,file.getSize()));
                }
            }
        }

    }

   


}
