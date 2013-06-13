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
package com.illumina.basespace;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.illumina.basespace.entity.AppResult;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.file.DefaultFileRequestHandler;
import com.illumina.basespace.file.DownloadListener;
import com.illumina.basespace.file.FileRequestHandler;
import com.illumina.basespace.infrastructure.ClientConnectionProvider;
import com.illumina.basespace.param.CoverageParams;
import com.illumina.basespace.param.CreateFileParams;
import com.illumina.basespace.param.FileParams;
import com.illumina.basespace.param.Mappable;
import com.illumina.basespace.param.PositionalQueryParams;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.response.GetAppResultResponse;
import com.illumina.basespace.response.GetAppSessionResponse;
import com.illumina.basespace.response.GetCoverageMetadataResponse;
import com.illumina.basespace.response.GetCoverageResponse;
import com.illumina.basespace.response.GetFileRedirectMetaDataResponse;
import com.illumina.basespace.response.GetFileResponse;
import com.illumina.basespace.response.GetGenomeResponse;
import com.illumina.basespace.response.GetProjectResponse;
import com.illumina.basespace.response.GetRunResponse;
import com.illumina.basespace.response.GetSampleResponse;
import com.illumina.basespace.response.GetUserResponse;
import com.illumina.basespace.response.ListAppResultsResponse;
import com.illumina.basespace.response.ListFilesResponse;
import com.illumina.basespace.response.ListGenomesResponse;
import com.illumina.basespace.response.ListProjectsResponse;
import com.illumina.basespace.response.ListRunsResponse;
import com.illumina.basespace.response.ListSamplesResponse;
import com.illumina.basespace.response.ListVariantsResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Default implementation of a BaseSpace client
  * @author bking
 */
class DefaultApiClient implements ApiClient
{
    private final Logger logger = Logger.getLogger(DefaultApiClient.class.getPackage().getName());
    private ClientConnectionProvider connectionProvider;
    private FileRequestHandler fileRequestHandler;

    /**
     * Create a BaseSpace session
     * @param config the configuration to use to establish the session
     */
    DefaultApiClient(ClientConnectionProvider connectionProvider)
    {
        this.connectionProvider = connectionProvider;
        this.fileRequestHandler = new DefaultFileRequestHandler(this,connectionProvider);
        logger.setLevel(Level.ALL);
    }

    @Override
    public GetUserResponse getCurrentUser()
    {
        return getConnectionProvider().getResponse(GetUserResponse.class,"users/current",null,null);
    }

    @Override
    public ListProjectsResponse getProjects(QueryParams params)
    {
        return getConnectionProvider().getResponse(ListProjectsResponse.class,"users/current/projects",params,null);
    }

    @Override
    public GetProjectResponse getProject(String id)
    {
        return getConnectionProvider().getResponse(GetProjectResponse.class,"projects/" + id,null,null);
    }
    
    @Override
    public GetProjectResponse createProject(ProjectCompact project)
    {
        return getConnectionProvider().postResource(GetProjectResponse.class, "projects", project,null,null);
    }
    

    @Override
    public ListSamplesResponse getSamples(ProjectCompact project, QueryParams params)
    {
        return getConnectionProvider().getResponse(ListSamplesResponse.class,"projects/" + project.getId() + "/samples",params,null);

    }

    @Override
    public ListSamplesResponse getSamples(RunCompact run, QueryParams params)
    {
        return getConnectionProvider().getResponse(ListSamplesResponse.class,"runs/" + run.getId() + "/samples",params,null);

    }

    @Override
    public GetSampleResponse getSample(String id)
    {
        return getConnectionProvider().getResponse(GetSampleResponse.class,"samples/" + id,null,null);
    }

    @Override
    public GetAppResultResponse getAppResult(String id)
    {
        return getConnectionProvider().getResponse(GetAppResultResponse.class,"appresults/" + id,null,null);
    }

    @Override
    public ListAppResultsResponse getAppResults(ProjectCompact project, QueryParams params)
    {

        
        return getConnectionProvider().getResponse(ListAppResultsResponse.class,"projects/" + project.getId() + "/appresults",params,null);

    }
    
    @Override
    public GetAppSessionResponse getAppSession(String id)
    {
        return getConnectionProvider().getResponse(GetAppSessionResponse.class,"appsessions/" + id,null,null);
    }


    @Override
    public ListRunsResponse getRuns(QueryParams params)
    {
        return getConnectionProvider().getResponse(ListRunsResponse.class,"users/current/runs",params,null);

    }

    @Override
    public GetRunResponse getRun(String id)
    {
        return getConnectionProvider().getResponse(GetRunResponse.class,"runs/" + id,null,null);
    }

    @Override
    public ListFilesResponse getFiles(SampleCompact sample, FileParams params)
    {
        return getConnectionProvider().getResponse(ListFilesResponse.class,"samples/" + sample.getId() +"/files",params,null);

    }

    @Override
    public ListFilesResponse getFiles(RunCompact run, FileParams params)
    {
        return getConnectionProvider().getResponse(ListFilesResponse.class,"runs/" + run.getId() + "/files",params,null);
    }

    @Override
    public ListFilesResponse getFiles(AppResultCompact appResult, FileParams params)
    {
        return getConnectionProvider().getResponse(ListFilesResponse.class,"appresults/" + appResult.getId() + "/files",params,null);
    }

    @Override
    public GetFileResponse getFile(String id)
    {
        return getConnectionProvider().getResponse(GetFileResponse.class,"files/" + id,null,null);
    }

    @Override
    public URI getDownloadURI(File file)
    {
        return getFileRequestHandler().getURI(file);
    }

    @Override
    public InputStream getFileInputStream(File file)
    {
        return getFileRequestHandler().getInputStream(file);
    }

    @Override
    public InputStream getFileInputStream(File file, long start, long end)
    {
        return getFileRequestHandler().getInputStream(file,start,end);
    }

    @Override
    public void download(final com.illumina.basespace.entity.File file,java.io.File localFolder,
            DownloadListener listener)
    {
        getFileRequestHandler().download(file, localFolder,listener);
    }

    @Override
    public void download(final com.illumina.basespace.entity.File file, long fileStart, long len, java.io.File target,
			     long targetStart)
    {
        getFileRequestHandler().download(file,fileStart,len,target,targetStart);
    }
    

    @Override
    public GetFileRedirectMetaDataResponse getFileRedirectMetaData(File file)
    {
        final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        queryParams.add("redirect", "meta");
        Mappable mappable = new Mappable()
        {
            @Override
            public MultivaluedMap<String, String> toMap()
            {
                return queryParams;
            }
        };
        return getConnectionProvider().getResponse(GetFileRedirectMetaDataResponse.class,getDownloadURI(file).toString(),mappable,null);
    }
    
    @Override
    public GetCoverageResponse getCoverage(File file, String chromosome, CoverageParams params)
    {
        return getConnectionProvider().getResponse(GetCoverageResponse.class,file.getHrefCoverage()
                + "/" + chromosome,params,null);
    }

    @Override
    public GetCoverageMetadataResponse getCoverageMetaData(File file, String chromosome)
    {
        return getConnectionProvider().getResponse(GetCoverageMetadataResponse.class,file.getHrefCoverage()
                + "/" + chromosome + "/meta",null,null);
    }

    @Override
    public ListVariantsResponse getVariants(File file, String chromosome, PositionalQueryParams params)
    {
        params.addParam("Format", "json");
        return getConnectionProvider().getResponse(ListVariantsResponse.class,file.getHrefVariants() + "/variants/" + chromosome,params,null);
    }

    @Override
    public String getVariantRawRecord(File file, String chromosome, PositionalQueryParams params)
    {
        params.addParam("Format", "vcf");
        return getConnectionProvider().getStringResponse("variantset/" + file.getId() + "/variants/" + chromosome,params,null);
    }

    private String accessToken;
    protected String getAccessToken()
    {
        return accessToken;
    }

    protected WebResource getRootApiWebResource()
    {
        return getClient().resource( UriBuilder.fromUri(connectionProvider.getConfiguration().getApiRootUri()).path(connectionProvider.getConfiguration().getVersion()).build());
    }


    private Client getClient()
    {
        return connectionProvider.getClient();
    }
 
    @Override
    public URI getRootURI()
    {
        try
        {
            return new URI(connectionProvider.getConfiguration().getApiRootUri());
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

    public ClientConnectionProvider getConnectionProvider()
    {
        return this.connectionProvider;
    }
    protected FileRequestHandler getFileRequestHandler()
    {
       return this.fileRequestHandler;
    }
    

    @Override
    public GetAppSessionResponse updateAppSession(AppSessionCompact appSession)
    {
        return getConnectionProvider().postResource(GetAppSessionResponse.class, "appsessions/" + appSession.getId(), appSession,null,null);
    }

    @Override
    public GetAppResultResponse createAppResult(ProjectCompact project,AppResult appResult)
    {
        return getConnectionProvider().postResource(GetAppResultResponse.class, "projects/" + project.getId() + "/appresults", appResult,null,null);
    }

    @Override
    public ListGenomesResponse getGenomes(QueryParams params)
    {
        return getConnectionProvider().getResponse(ListGenomesResponse.class,"genomes",params,null);
    }

    @Override
    public GetGenomeResponse getGenome(String id)
    {
        return getConnectionProvider().getResponse(GetGenomeResponse.class,"genomes/" + id,null,null);
        
    }

    @Override
    public GetFileResponse createFile(AppResultCompact appResult,FileCompact file,String contentType, CreateFileParams params)
    {
        Map<String,String>headers = new HashMap<String,String>();
        headers.put("Content-Type", contentType);
        return getConnectionProvider().postResource(GetFileResponse.class,"appresult/" +appResult.getId(),file,headers,params);

    }


}