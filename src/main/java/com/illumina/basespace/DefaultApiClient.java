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

import java.io.InputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import com.illumina.basespace.entity.AppResult;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.file.DefaultFileRequestHandler;
import com.illumina.basespace.file.FileRequestHandler;
import com.illumina.basespace.infrastructure.ClientConnectionProvider;
import com.illumina.basespace.param.CoverageParams;
import com.illumina.basespace.param.FileParams;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.param.VariantParams;
import com.illumina.basespace.param.VariantParams.ReturnFormat;
import com.illumina.basespace.response.GetAppResultResponse;
import com.illumina.basespace.response.GetAppSessionResponse;
import com.illumina.basespace.response.GetCoverageMetadataResponse;
import com.illumina.basespace.response.GetCoverageResponse;
import com.illumina.basespace.response.GetFileResponse;
import com.illumina.basespace.response.GetProjectResponse;
import com.illumina.basespace.response.GetRunResponse;
import com.illumina.basespace.response.GetSampleResponse;
import com.illumina.basespace.response.GetUserResponse;
import com.illumina.basespace.response.ListAppResultsResponse;
import com.illumina.basespace.response.ListFilesResponse;
import com.illumina.basespace.response.ListProjectsResponse;
import com.illumina.basespace.response.ListRunsResponse;
import com.illumina.basespace.response.ListSamplesResponse;
import com.illumina.basespace.response.ListVariantsResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Default implementation of a BaseSpace session
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
        this.fileRequestHandler = new DefaultFileRequestHandler(connectionProvider);
        logger.setLevel(Level.ALL);
    }

    @Override
    public GetUserResponse getCurrentUser()
    {
        return getConnectionProvider().getResponse(GetUserResponse.class,"users/current",null);
    }

    @Override
    public ListProjectsResponse getProjects(QueryParams params)
    {
        return getConnectionProvider().getResponse(ListProjectsResponse.class,"users/current/projects",params);
    }

    @Override
    public GetProjectResponse getProject(String id)
    {
        return getConnectionProvider().getResponse(GetProjectResponse.class,"projects/" + id,null);
    }
    
    @Override
    public GetProjectResponse createProject(ProjectCompact project)
    {
        return getConnectionProvider().postResource(GetProjectResponse.class, "projects", project);
    }
    

    @Override
    public ListSamplesResponse getSamples(ProjectCompact project, QueryParams params)
    {
        return getConnectionProvider().getResponse(ListSamplesResponse.class,"projects/" + project.getId() + "/samples",params);

    }

    @Override
    public ListSamplesResponse getSamples(RunCompact run, QueryParams params)
    {
        return getConnectionProvider().getResponse(ListSamplesResponse.class,"runs/" + run.getId() + "/samples",params);

    }

    @Override
    public GetSampleResponse getSample(String id)
    {
        return getConnectionProvider().getResponse(GetSampleResponse.class,"samples/" + id,null);
    }

    @Override
    public GetAppResultResponse getAppResult(String id)
    {
        return getConnectionProvider().getResponse(GetAppResultResponse.class,"appresults/" + id,null);
    }

    @Override
    public ListAppResultsResponse getAppResults(ProjectCompact project, QueryParams params)
    {
        return getConnectionProvider().getResponse(ListAppResultsResponse.class,"projects/" + project.getId() + "/appresults",params);

    }
    
    @Override
    public GetAppSessionResponse getAppSession(String id)
    {
        return getConnectionProvider().getResponse(GetAppSessionResponse.class,"appsessions/" + id + "/appresults",null);
    }


    @Override
    public ListRunsResponse getRuns(QueryParams params)
    {
        return getConnectionProvider().getResponse(ListRunsResponse.class,"users/current/runs",params);

    }

    @Override
    public GetRunResponse getRun(String id)
    {
        return getConnectionProvider().getResponse(GetRunResponse.class,"runs/" + id,null);
    }

    @Override
    public ListFilesResponse getFiles(SampleCompact sample, FileParams params)
    {
        return getConnectionProvider().getResponse(ListFilesResponse.class,"samples/" + sample.getId() +"/files",params);

    }

    @Override
    public ListFilesResponse getFiles(RunCompact run, FileParams params)
    {
        return getConnectionProvider().getResponse(ListFilesResponse.class,"runs/" + run.getId() + "/files",params);
    }

    @Override
    public ListFilesResponse getFiles(AppResultCompact appResult, FileParams params)
    {
        return getConnectionProvider().getResponse(ListFilesResponse.class,"appresult/" + appResult.getId() + "/files",params);

    }

    @Override
    public GetFileResponse getFile(String id)
    {
        return getConnectionProvider().getResponse(GetFileResponse.class,"files/" + id,null);
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
    public void download(final com.illumina.basespace.entity.File file,java.io.File localFolder)
    {
        getFileRequestHandler().download(file, localFolder);
    }

    @Override
    public void download(final com.illumina.basespace.entity.File file, long fileStart, long len, java.io.File target,
			     long targetStart)
    {
        getFileRequestHandler().download(file,fileStart,len,target,targetStart);
    }
    
    @Override
    public GetCoverageResponse getCoverage(File file, String chromosome, CoverageParams params)
    {
        return getConnectionProvider().getResponse(GetCoverageResponse.class,file.getHrefCoverage()
                + "/" + chromosome,params);
    }

    @Override
    public GetCoverageMetadataResponse getCoverageMetaData(File file, String chromosome)
    {
        return getConnectionProvider().getResponse(GetCoverageMetadataResponse.class,file.getHrefCoverage()
                + "/" + chromosome + "/meta",null);
    }

    @Override
    public ListVariantsResponse queryVariantJSON(File file, String chromosome, VariantParams params)
    {
        return getConnectionProvider().getResponse(ListVariantsResponse.class,file.getHrefVariants() + "/variants/" + chromosome,params);
    }

    @Override
    public String queryVariantRaw(File file, String chromosome, VariantParams params)
    {
        params.setFormat(ReturnFormat.vcf);
        return getConnectionProvider().getJSONResponse(file.getHrefVariants() + "/variants/" + chromosome,params);
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

    private ClientConnectionProvider getConnectionProvider()
    {
        return this.connectionProvider;
    }
    private FileRequestHandler getFileRequestHandler()
    {
       return this.fileRequestHandler;
    }

    @Override
    public GetAppSessionResponse updateAppSession(AppSessionCompact appSession)
    {
        return getConnectionProvider().postResource(GetAppSessionResponse.class, "appsessions/" + appSession.getId(), appSession);
    }

    @Override
    public GetAppResultResponse createAppResult(ProjectCompact project,AppResult appResult)
    {
        return getConnectionProvider().postResource(GetAppResultResponse.class, "projects/" + project.getId() + "/appresults", appResult);
    }

}