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
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
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
    private URI apiUri;
     
    /**
     * Create a BaseSpace session 
     * @param config the configuration to use to establish the session
     */
    DefaultBaseSpaceSession(URI apiUri,AuthToken authToken)
    {
        this.apiUri = apiUri;
        this.token = authToken;
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
    public Sample getSample(String id)
    {
        return getSingle(Sample.class,id);
    }

    @Override
    public List<Analysis> getAnalyses(Project project,FetchParams params)
    {
        return (List)getList(project,Analysis.class,params);
    }
    
    @Override
    public Analysis getAnalysis(String id)
    {
        return getSingle(Analysis.class,id);
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
    public List<File> getFiles(Analysis analysis,FetchParams params)
    {
        return (List)getList(analysis,File.class,"/results",params);

    }

    @Override
    public File getFile(String id)
    {
        return getSingle(File.class,id);
    }

    @Override
    public void download(com.illumina.basespace.File file,java.io.File targetFolder,
            DownloadProgressListener progressListener)
    {
        FileOutputStream fos = null;
        InputStream in = null;
        try
        {
            final int CHUNK_SIZE = 4096;
            if (!targetFolder.isDirectory())
            {
                throw new IllegalArgumentException("Target folder must be a directory");
            }
            if (!targetFolder.exists() && !targetFolder.mkdirs())
            {
                throw new IllegalArgumentException("Unable to create local folder " + targetFolder.toString());
            }
            targetFolder = new java.io.File(targetFolder,file.getName());
            
            in= getRootApiWebResource().path("files")
                        .path(String.valueOf(file.getId())).path("content")
                        .accept(MediaType.APPLICATION_OCTET_STREAM)
                        .accept(MediaType.TEXT_HTML)
                        .accept(MediaType.APPLICATION_XHTML_XML)
                        .get(InputStream.class);
            
            fos = new FileOutputStream(targetFolder);
            long progress = 0;
            byte[] outputByte = new byte[CHUNK_SIZE];
            while(in.read(outputByte, 0, CHUNK_SIZE) != -1)
            {
                fos.write(outputByte, 0, CHUNK_SIZE);
                if (progressListener != null)
                {
                    progress += CHUNK_SIZE;
                    progressListener.progress(progress, file.getSize());
                }
            }
            fos.close();
            in.close();
            fos = null;
            in = null;
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            throw new RuntimeException("Error during file download",t);
        }
        finally
        {
            try{if (in != null)in.close();}catch(Throwable t){}
            try{if (fos != null)fos.close();}catch(Throwable t){}
        }
    }
    
    protected <T extends BaseSpaceEntity> T getSingle(Class<? extends BaseSpaceEntity>clazz,String id)
    {
        try
        {
            UriPath path = BaseSpaceUtilities.getAnnotation(UriPath.class, clazz);
            ClientResponse response = getClient().resource(apiUri).path(path.value()).path(id)
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
            JsonNode responseNode = mapper.readValue(response, JsonNode.class).findPath(RESPONSE);
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
            throw new RuntimeException(t.getMessage());
        }
    }
    
    private AuthToken token;
    protected AuthToken getAuthToken()
    {
        return token;
    }
    
    protected WebResource getRootApiWebResource()
    {
        return getClient().resource(apiUri);
    }

    
    private Client client;
    private Client getClient()
    {
        if (client !=  null)return client;
        return client = createClient(false);
    }
    private Client createClient(boolean ssl)
    {
        ClientConfig config = new DefaultClientConfig();
        
        Client client = Client.create(config);
        client.addFilter(new ClientFilter()
        {
            @Override
            public ClientResponse handle(ClientRequest request) throws ClientHandlerException
            {
                logger.info(request.getMethod() + " to " + request.getURI().toString());
                if (getAuthToken() != null)
                {
                    logger.fine("Auth token " + getAuthToken().getAccessToken());
                    request.getHeaders().add("x-access-token",getAuthToken().getAccessToken());
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
}
