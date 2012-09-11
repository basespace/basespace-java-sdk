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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illumina.basespace.VariantFetchParams.ReturnFormat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Default implementation of a BaseSpace session
 * @author bking
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
class DefaultBaseSpaceSession implements BaseSpaceSession
{
    private static Logger logger = Logger.getLogger(DefaultBaseSpaceSession.class.getPackage().getName());
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String RESPONSE = "Response";
    private static final String ITEMS = "Items";
    private List<DownloadListener>downloadListeners;
    private BaseSpaceConfiguration configuration;
    private Map<Long,FileMetaData>fileToUriMap = new HashMap<Long,FileMetaData>();
    
    /**
     * Create a BaseSpace session 
     * @param config the configuration to use to establish the session
     */
    DefaultBaseSpaceSession(BaseSpaceConfiguration configuration,String accessToken)
    {
        this.configuration = configuration;
        this.accessToken = accessToken;
    }
    
    @Override
    public User getCurrentUser()
    {
        return getSingle(User.class,"current");
    }

    @Override
    public User getUser(String id)
    {
        return getSingle(User.class,id);
    }

    //
    @Override
    public List<Project> getProjects(User user,FetchParams params)
    {
        return (List)getList(user,Project.class,params);
    }
    
    @Override
    public Project getProject(String id)
    {
        return getSingle(Project.class,id);
    }

    @Override
    public List<Sample> getSamples(Project project,FetchParams params)
    {
        return (List)getList(project,Sample.class,params);
    }
    
    @Override
    public List<Sample> getSamples(Run run, FetchParams params)
    {
        return (List)getList(run,Sample.class,params);
    }
    

    @Override
    public Sample getSample(String id)
    {
        return getSingle(Sample.class,id);
    }

    @Override
    public List<AppResult> getAppResults(Project project, FetchParams params)
    {
        return (List)getList(project,AppResult.class,params);
    }

    @Override
    public List<Run> getRuns(User user,FetchParams params)
    {
        return (List)getList(user,Run.class,params);
    }
    
    @Override
    public Run getRun(String id)
    {
        return getSingle(Run.class,id);
    }
    
    @Override
    public List<File> getFiles(Sample sample,FetchParams params)
    {
        return (List)getList(sample,File.class,params);
    }
    
    @Override
    public List<File> getFiles(Run run,FetchParams params)
    {
        return (List)getList(run,File.class,params);
    }
    
    @Override
    public List<File> getFiles(AppResult appResults, FetchParams params)
    {
        return (List)getList(appResults,File.class,params);
    }


    @Override
    public File getFile(String id)
    {
        return getSingle(File.class,id);
    }

    @Override
    public URI getDownloadURI(File file)
    {
        WebResource resource = getRootApiWebResource().path("files")
                .path(String.valueOf(file.getId())).path("content");
        return resource.getURI();
    }
    
    @Override
    public InputStream getFileInputStream(File file)
    {
        return getFileInputStream(file,0,file.getSize());
    }
    
    @Override
    public InputStream getFileInputStream(File file,long start,long end)
    {
        try
        {
            FileMetaData fileInfo = fileToUriMap.get(file.getId());
            if (fileInfo == null || new Date().after(fileInfo.getExpires()))
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
        catch(ForbiddenResourceException fr)
        {
            throw fr;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
        
    }
    
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
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    
    @Override
    public void download(final com.illumina.basespace.File file,java.io.File target)
    {
        FileOutputStream fos = null;
        InputStream in = null;
        boolean canceled = false;
        try
        {
            final int CHUNK_SIZE = 4096;
            if (target.isDirectory())
            {
                if (!target.exists() && !target.mkdirs())
                {
                    throw new IllegalArgumentException("Unable to create local folder " + target.toString());
                }
                target = new java.io.File(target,file.getName());
            }
            in = getFileInputStream(file);
            fos = new FileOutputStream(target);
            long progress = 0;
            byte[] outputByte = new byte[CHUNK_SIZE];
            int bytesRead = 0;
            
            readTheFile:
            while((bytesRead = in.read(outputByte, 0, CHUNK_SIZE)) != -1)
            {
                fos.write(outputByte, 0, bytesRead);
                progress += bytesRead;
                DownloadEvent evt = new DownloadEvent(this,file.getHref(),progress,file.getSize());
                fireProgressEvent(evt);
                if (evt.isCanceled())
                {
                    canceled = true;
                    break readTheFile;
                }
            }
            fos.close();
            in.close();
            fos = null;
            in = null;
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
                if (target != null)target.delete();
                DownloadEvent evt = new DownloadEvent(this,file.getHref(),0,file.getSize());
                fireCanceledEvent(evt);
            }
            else
            {
                DownloadEvent evt = new DownloadEvent(this,file.getHref(),file.getSize(),file.getSize());
                fireCompleteEvent(evt);
            }
        }
    }

    
    protected <T extends BaseSpaceEntity> T getSingle(Class<? extends BaseSpaceEntity>clazz,String id)
    {
        try
        {
            UriPath path = BaseSpaceUtilities.getAnnotation(UriPath.class, clazz);
            ClientResponse response = getClient().resource(
                    configuration.getApiRootUri())
                    .path(configuration.getVersion())
                    .path(path.value()).path(id)
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_JSON).get(ClientResponse.class);
            String rtn = response.getEntity(String.class);
            return (T) mapper.readValue( mapper.readValue(rtn, JsonNode.class).findPath(RESPONSE).toString(), clazz);
        }
        catch(ForbiddenResourceException fr)
        {
            throw fr;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }
    

    @Override
    public <T extends File> T getFileExtendedInfo(long fileId, Class<T> clazz)
    {
        try
        {
            WebResource resource = getRootApiWebResource().path("files")
                    .path(String.valueOf(fileId));
            ClientResponse response = getClient().resource(resource.getURI())
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_JSON).get(ClientResponse.class);
            String rtn = response.getEntity(String.class);
            logger.info(rtn);
            return (T) mapper.readValue( mapper.readValue(rtn, JsonNode.class).findPath(RESPONSE).toString(), clazz);
        }
        catch(RuntimeException rte)
        {
            throw rte;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

    @Override
    public CoverageRecord getCoverage(ExtendedFileInfo file,String chromosome, int start, int end, int zoomLevel)
    {
        try
        {
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
            queryParams.add("startPos", String.valueOf(start));
            queryParams.add("endPos", String.valueOf(end));
            queryParams.add("zoomLevel", String.valueOf(zoomLevel));
            
            WebResource resource = getClient().resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(file.getHrefCoverage())
                    .path(chromosome)
                    .build())
                    .queryParams(queryParams);
    
            ClientResponse response = getClient().resource(resource.getURI())
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_JSON).get(ClientResponse.class);
           
            switch(response.getClientResponseStatus())
            {
                case OK:
                    String data = response.getEntity(String.class);       
                    logger.info(data);
                    CoverageRecord record = mapper.readValue( mapper.readValue(data, JsonNode.class).findPath(RESPONSE).toString(), CoverageRecord.class);
                    logger.info(record.toString());
                    return record;
                default:
                    data = response.getEntity(String.class);       
                    Response status = mapper.readValue(data,Response.class);
                    throw new IllegalArgumentException(status.getResponseStatus().getMessage());
            }
        }
        catch(RuntimeException rte)
        {
            throw rte;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }  
    }
    

    @Override
    public CoverageMetaData getCoverageMetaData(ExtendedFileInfo file, String chromosome)
    {
        try
        {
            WebResource resource = getClient().resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(file.getHrefCoverage())
                    .path(chromosome)
                    .path("meta")
                    .build());
            ClientResponse response = getClient().resource(resource.getURI())
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_JSON).get(ClientResponse.class);
            switch(response.getClientResponseStatus())
            {
                case OK:
                    String data = response.getEntity(String.class);       
                    logger.info(data);
                    CoverageMetaData metaData = mapper.readValue( mapper.readValue(data, JsonNode.class).findPath(RESPONSE).toString(), CoverageMetaData.class);
                    logger.info(metaData.toString());
                    return metaData;
                default:
                    data = response.getEntity(String.class);       
                    Response status = mapper.readValue(data,Response.class);
                    throw new IllegalArgumentException(status.getResponseStatus().getMessage());
            }
        }
        catch(RuntimeException rte)
        {
            throw rte;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }  
    }


    @Override
    public List<VariantRecord> queryVariantJSON(ExtendedFileInfo file, String chromosome,VariantFetchParams params)
    {
        try
        {
            params.setFormat(ReturnFormat.json);
            String rtn = queryVariantsInternal(file,chromosome,params);
            logger.info(rtn);
            JsonNode responseNode = mapper.readValue(rtn, JsonNode.class).findPath(ITEMS);
            List<VariantRecord>records = new ArrayList<VariantRecord>();
            for(int i = 0; i < responseNode.size(); i++)
            {
                records.add((VariantRecord)mapper.readValue(responseNode.get(i).toString(),VariantRecord.class));
            }            
            return records;
        }
        catch(Throwable t)
        {
            throw new RuntimeException("Error during JSON query " + t);
        }   
    }  
    
    @Override
    public String queryVariantRaw(ExtendedFileInfo file, String chromosome,VariantFetchParams params)
    {
        params.setFormat(ReturnFormat.vcf);
        String rtn = queryVariantsInternal(file,chromosome,params);
        logger.info(rtn);
        return rtn;
    }
    
    protected String queryVariantsInternal(ExtendedFileInfo file, String chromosome, VariantFetchParams params)
    {
        if (file == null || file.getHrefVariants() == null)
        {
            throw new IllegalArgumentException("null VariantFile or hrefVariants property for VariantFile object");
        }
        try
        {
            MultivaluedMap<String, String> queryParams = params == null? new MultivaluedMapImpl():params.toMap();
            WebResource resource = getClient().resource(UriBuilder.fromUri(configuration.getApiRootUri())
                    .path(file.getHrefVariants())
                    .path("variants")
                    .path(chromosome)
                    .build())
                    .queryParams(queryParams);
            ClientResponse response = getClient().resource(resource.getURI())
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_JSON).get(ClientResponse.class);
            switch(response.getClientResponseStatus())
            {
                case OK:
                    return response.getEntity(String.class);
                default:
                    Response status = mapper.readValue(response.getEntity(String.class),Response.class);
                    throw new IllegalArgumentException(status.getResponseStatus().getMessage());
            }
        }
        catch(ForbiddenResourceException fr)
        {
            throw fr;
        }
        catch(IllegalArgumentException iae)
        {
            throw iae;
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }       
    }
   
    
    protected List<BaseSpaceEntity>getList(BaseSpaceEntity containingObject,Class<? extends BaseSpaceEntity>clazz,
            FetchParams params)
    {
        return getList(containingObject,clazz,null,params);
    }
    
    protected List<BaseSpaceEntity>getList(BaseSpaceEntity containingObject,Class<? extends BaseSpaceEntity>clazz,
            String targetPath,FetchParams params)
    {
        try
        {
            ///v1pre1/users/{Id}/projects
            List<BaseSpaceEntity>rtn = new ArrayList<BaseSpaceEntity>();
            UriPath containerUri = BaseSpaceUtilities.getAnnotation(UriPath.class, containingObject.getClass());
            UriPath targetUri = BaseSpaceUtilities.getAnnotation(UriPath.class,clazz);
            
            MultivaluedMap<String, String> queryParams = params == null? new MultivaluedMapImpl():params.toMap();
            String response = getRootApiWebResource()
                    .path(containerUri.value())
                    .path(String.valueOf(containingObject.getId()))
                    .path(targetPath != null?targetPath:targetUri.value())
                    .queryParams(queryParams)
                    .accept(MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_JSON).get(String.class);
            logger.info(response);
            JsonNode responseNode = mapper.readValue(response, JsonNode.class).findPath(ITEMS);
            for(int i = 0; i < responseNode.size(); i++)
            {
                rtn.add(mapper.readValue(responseNode.get(i).toString(),clazz));
            }
            return rtn;
        }
        catch(ForbiddenResourceException fr)
        {
            throw fr;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException(t.getMessage());
        }
    }
    
    private String accessToken;
    protected String getAccessToken()
    {
        return accessToken;
    }
    
    protected WebResource getRootApiWebResource()
    {
        return getClient().resource( UriBuilder.fromUri(configuration.getApiRootUri()).path(configuration.getVersion()).build());
    }

    
    private Client client;
    private Client getClient()
    {
        if (client !=  null)return client;
        return client = createClient();
    }
    private Client createClient()
    {
        ClientConfig config = new DefaultClientConfig();
        
        URLConnectionClientHandler urlConnectionClientHandler = 
                new URLConnectionClientHandler(new BaseSpaceURLConnectionFactory(this.configuration));

        Client client = new Client(urlConnectionClientHandler,config); 
        client.addFilter(new ClientFilter()
        {
            @Override
            public ClientResponse handle(ClientRequest request) throws ClientHandlerException
            {
                logger.info(request.getMethod() + " to " + request.getURI().toString());
                if (accessToken != null)
                {
                    logger.finer("Auth token " + accessToken);
                    request.getHeaders().add("x-access-token",accessToken);
                }
                ClientResponse response = getNext().handle(request); 
                switch(response.getClientResponseStatus())
                {
                    case FORBIDDEN:
                        throw new ForbiddenResourceException(request.getURI());
                        
                }
                return response;
            }
        });
        return client;
    }
    
    public void addDownloadListener(DownloadListener listener)
    {
        if (downloadListeners == null)
            downloadListeners =  Collections.synchronizedList(new ArrayList<DownloadListener>());
        
        if (!downloadListeners.contains(listener))
            downloadListeners.add(listener);
    }
    public void removeDownloadListener(DownloadListener listener)
    {
        if (downloadListeners == null)return;
        if (downloadListeners.contains(listener))
            downloadListeners.remove(listener);
    }
    
    protected void fireProgressEvent(DownloadEvent evt)
    {
        if (downloadListeners == null)return;
        for(DownloadListener listener:downloadListeners)
        {
            listener.progress(evt);
         }
    }
    protected void fireCompleteEvent(DownloadEvent evt)
    {
        if (downloadListeners == null)return;
        for(DownloadListener listener:downloadListeners)
        {
            listener.complete(evt);
        }
    }
    protected void fireCanceledEvent(DownloadEvent evt)
    {
        if (downloadListeners == null)return;
        for(DownloadListener listener:downloadListeners)
        {
            listener.canceled(evt);
         }
    }

    @Override
    public URI getRootURI()
    {
        try
        {
            return new URI(configuration.getApiRootUri());
        }
        catch(Throwable t)
        {
            throw new RuntimeException(t);
        }
    }



 
}
