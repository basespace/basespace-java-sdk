package com.illumina.basespace.file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.ws.rs.core.UriBuilder;

import com.illumina.basespace.entity.File;
import com.illumina.basespace.infrastructure.BaseSpaceException;
import com.illumina.basespace.infrastructure.ClientConnectionProvider;
import com.sun.jersey.api.client.WebResource;

public class DefaultFileRequestHandler implements FileRequestHandler
{
    private ClientConnectionProvider connectionProvider;
    
    public DefaultFileRequestHandler(ClientConnectionProvider connectionProvider)
    {
        this.connectionProvider = connectionProvider;
    }


   

    @Override
    public InputStream getInputStream(File file)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InputStream getInputStream(File file, long start, long end)
    {
        try
        {
            return getInputStreamInternal(file, start, end, false);
        }
        catch (BaseSpaceException bs)
        {
            try
            {
                //File access has probably expired, attempt to obtain new link, if this doesn't work, exception out
                return getInputStreamInternal(file, start, end, true);
            }
            catch (BaseSpaceException bs1)
            {
                throw bs1;
            }
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
                .resource( UriBuilder.fromUri(connectionProvider.getConfiguration().getApiRootUri()).path(connectionProvider.getConfiguration().getVersion()).build())
                .path("files")
                .path(String.valueOf(file.getId())).path("content");
        return resource.getURI();
    }

    
    private InputStream getInputStreamInternal(File file,long start,long end,boolean refreshMeta)
    {
        return null;
        /*
        try
        {
            FileMetaData fileInfo = fileToUriMap.get(file.getId());
            if (refreshMeta || (fileInfo == null || new Date().after(fileInfo.getExpires())))
            {
                fileInfo = getFileContentInfo(file);
                fileToUriMap.put(file.getId(), fileInfo);
            }

            InputStream in = getClient()
                .resource(new URI(fileInfo.getHrefContent()))
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .accept(MediaType.TEXT_HTML)
                .accept(MediaType.APPLICATION_XHTML_XML)
                .header("Range","bytes=" + start + "-" + end)
                .get(InputStream.class);
            return in;
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }*/
    }


    @Override
    public void download(File file, long fileStart, long len, java.io.File target, long targetStart)
    {
        FileChannel fc = null; 
        RandomAccessFile ras = null;
        InputStream in = null;
        boolean canceled = false;

    final int CHUNK_SIZE = 8192; // for part downloads, reduce the number of calls by 1/2. 
    long progress = 0;

    try{
        if ((fileStart + len) > file.getSize()){
        throw new Exception("Invalid download range start("+fileStart+ ") + len ("+len+") > file size ("+file.getSize()+")");
        }
            if (target.isDirectory()){
                if (!target.exists() && !target.mkdirs())
            {
            throw new IllegalArgumentException("Unable to create local folder " + target.toString());
            }
                target = new java.io.File(target,new String(file.getName()));
        }


        in = getInputStream(file,  fileStart, fileStart+len-1); // These are *positions*; end at len minus 1

        // Note: It sounds simple to use a FileChannel to sparsely write a file.  It isn't.
        // The code is sensitive to the right combination of usage.  I use a RandomAccessStream
        // and aquire a FileChannel from it.  It did not work if you just use the RAS.  It did not work
        // if you try to open the FC w/o the RAS.  It did not work if you use a RAS:FC but allocate
        // a ByteBuffer once, call bb.clear, bb.put, then fc.write(bb).  This caused the FC to write
        // garbage for CHUNK_SIZE-bb.length.  
        // Thus we now always use the ByteBuffer.wrap and the FC from a RAS. 

        ras = new RandomAccessFile(target,"rw");
        fc = ras.getChannel(); // Open for WRITE default. 
        fc.position(targetStart);  // Place the position at our place in the file
        fc.force(true);
            progress = 0;
            byte[] outputByte = new byte[CHUNK_SIZE];
            int bytesRead = 0;
            readTheFile:
            while((bytesRead = in.read(outputByte, 0, CHUNK_SIZE)) != -1)
            {
        fc.write(ByteBuffer.wrap(outputByte,0,bytesRead));      
                progress += bytesRead;

            }
        fc.close();
        ras.close();
            in.close();
        fc = null;
        ras = null;
            in = null;
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            throw new RuntimeException("Error during file download",t);
        }
        finally
        {
            try{if (fc != null)fc.close();}catch(Throwable t){}
            try{if (in != null)in.close();}catch(Throwable t){}
            try{if (ras != null)ras.close();}catch(Throwable t){}
             if (canceled)
            {
                if (target != null)target.delete();
            }
        }
        
    }
    
    @Override
    public void download(File file, java.io.File localFolder)
    {
        FileOutputStream fos = null;
        InputStream in = null;
        boolean canceled = false;
        try
        {
            final int CHUNK_SIZE = 4096;
            if (localFolder.isDirectory())
            {
                if (!localFolder.exists() && !localFolder.mkdirs())
                {
                    throw new IllegalArgumentException("Unable to create local folder " + localFolder.toString());
                }
                localFolder = new java.io.File(localFolder,file.getName());
            }
            in = getInputStream(file); 
            fos = new FileOutputStream(localFolder);
            long progress = 0;
            byte[] outputByte = new byte[CHUNK_SIZE];
            int bytesRead = 0;
            
            readTheFile:
            while((bytesRead = in.read(outputByte, 0, CHUNK_SIZE)) != -1)
            {
                fos.write(outputByte, 0, bytesRead);
                progress += bytesRead;
       

            }
            fos.close();
            in.close();
            fos = null;
            in = null;
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            throw new RuntimeException("Error during file download",t);
        }
        finally
        {
            try{if (in != null)in.close();}catch(Throwable t){}
            try{if (fos != null)fos.close();}catch(Throwable t){}
             if (canceled)
            {
                if (localFolder != null)localFolder.delete();
            }
        }

    }
    

    /*
    private FileMetaData getFileContentInfo(File file)
    {
        try
        {
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
            queryParams.add("redirect", "meta");
            ClientResponse resp =   getRootApiWebResource().path("files")
                    .path(String.valueOf(file.getId())).path("content")
                    .queryParams(queryParams)
                    .accept(MediaType.APPLICATION_OCTET_STREAM)
                    .accept(MediaType.TEXT_HTML)
                    .accept(MediaType.APPLICATION_XHTML_XML)
                    .get(ClientResponse.class);
            
            String sResp = resp.getEntity(String.class);
            FileMetaData fileInfo = mapper.readValue( mapper.readValue(sResp, JsonNode.class).findPath(RESPONSE).toString(), FileMetaData.class);
            logger.info(fileInfo.toString());
            return fileInfo;
        }
        catch(BaseSpaceException bs)
        {
            throw bs;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }*/
}
