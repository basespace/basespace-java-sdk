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

import com.illumina.basespace.auth.ResourceForbiddenException;
import com.illumina.basespace.entity.AppResult;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.file.DownloadListener;
import com.illumina.basespace.infrastructure.ResourceNotFoundException;
import com.illumina.basespace.param.CoverageParams;
import com.illumina.basespace.param.CreateFileParams;
import com.illumina.basespace.param.FileParams;
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


/**
 * Defines the operations that can be performed against the BaseSpace API
 * @author bking
 */
public interface ApiClient
{
    /**
     * Gets the current user of the access token used to create client
     * @return the single item response
     */
    public GetUserResponse getCurrentUser();


    /**
     * Get a project by id
     * @param id project id
     * @return the single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetProjectResponse getProject(String id) throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Get a list of projects accessible to current user
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListProjectsResponse getProjects(QueryParams params);
    
    /**
     * Create a project
     * @param project
     * @return
     */
    public GetProjectResponse createProject(ProjectCompact project);
    
    /**
     * Get a sample by id
     * @param id sample id
     * @return the single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetSampleResponse getSample(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Get a list of samples for a project
     * @param project
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListSamplesResponse getSamples(ProjectCompact project,QueryParams params);

    /**
     * Get a list of samples for a run
     * @param run
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListSamplesResponse getSamples(RunCompact run,QueryParams params);
    
    /**
     * Get a list of app results for a project
     * @param project
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListAppResultsResponse getAppResults(ProjectCompact project,QueryParams params);
    
    /**
     * Get an app result by id
     * @param id app result id
     * @return the single response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetAppResultResponse getAppResult(String id)throws ResourceNotFoundException,ResourceForbiddenException;

    /**
     * Create an app result
     * @param project
     * @param appResult
     * @return
     */
    public GetAppResultResponse createAppResult(ProjectCompact project,AppResult appResult);
        
    /**
     * Get an app session by id
     * @param id app session id
     * @return the single response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetAppSessionResponse getAppSession(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Update an app session
     * @param appSession
     * @return
     */
    public GetAppSessionResponse updateAppSession(AppSessionCompact appSession);
  
    /**
     * Get a run by id
     * @param id run id
     * @return the single response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetRunResponse getRun(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Get runs for the current user
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListRunsResponse getRuns(QueryParams params);
   
    /**
     * Get a list of files for a sample
     * @param sample
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListFilesResponse getFiles(SampleCompact sample,FileParams params);
    
    /**
     * Get a list of files for an app result
     * @param appResult
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListFilesResponse getFiles(AppResultCompact appResult,FileParams params);
    
    /**
     * Get a list of files for a run
     * @param run
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListFilesResponse getFiles(RunCompact run,FileParams params);
    
    /**
     * A get file by id
     * @param id file id
     * @return the single response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetFileResponse getFile(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Create a file for an app result
     * @param appResult
     * @param file
     * @param contentType
     * @param params
     * @return
     */
    public GetFileResponse createFile(AppResultCompact appResult,FileCompact file,String contentType,CreateFileParams params);
    
    /**
     * Get file redirect metadata for a file
     * @param file
     * @return the single response
     */
    public GetFileRedirectMetaDataResponse getFileRedirectMetaData(File file);
    
    /**
     * Download a file to the local file system
     * @param basespace file to download
     * @param local folder as download target location
     * @param optional download listener to be notified of download events
     */
    public void download(com.illumina.basespace.entity.File file,java.io.File targetFolder,
            DownloadListener listener);
    
    /**
     * Expose a single chunk of download to the client.  Thread-safe
     * API call.  Multiple threads may use this call to do parallel
     * fetches of a single file.
     * 
     * This is a range download from file, starting at fileStart for
     * len bytes.  It will write data into target starting at
     * targetStart.  If file is a directory, this will use
     * target/<file.getName()>-start-len.dat
     * 
     * @param file the file to download
     * @param fileStart the starting position for the file
     * @param len the length to retrieve
     * @param target the target local file
     * @param targetStart target local file starting position
     */
    public void download(final com.illumina.basespace.entity.File file, long fileStart, long len, java.io.File target,long targetStart);
    
    /**
     * Get the URI for API operations
     * @return the root URI
     */
    public URI getRootURI();
    
    /**
     * Get the download URI for a file
     * @param file
     * @return the download URI
     */
    public URI getDownloadURI(File file);
    
    /**
     * Get the input stream for a file
     * @param file
     * @return the InputStream
     */
    public InputStream getFileInputStream(File file);
    
    /**
     * Get a byte range input stream for a file
     * @param file
     * @param start start of range
     * @param end end of range
     * @return the InputStream
     */
    public InputStream getFileInputStream(File file,long start,long end);
    
    /**
     * Get a list of variants from a vcf file
     * @param vcf file
     * @param chromosome
     * @param params
     * @return the list response
     */
    public ListVariantsResponse getVariants(File file,String chromosome,PositionalQueryParams params);
    
    /**
     * Get raw record data from a vcf file
     * @param file vcf file
     * @param chromosome
     * @param params
     * @return a string containing one or more variant records, or an empty string
     */
    public String getVariantRawRecord(File file,String chromosome,PositionalQueryParams params);
    
    /**
     * Get coverage data for a bam file
     * @param file bam file
     * @param chromosome
     * @param params
     * @return the single response
     */
    public GetCoverageResponse getCoverage(File file,String chromosome,CoverageParams params);
    
    /**
     * Get coverage metadata for a bam file
     * @param file bam file
     * @param chromosome
     * @return the single response
     */
    public GetCoverageMetadataResponse getCoverageMetaData(File file,String chromosome);
    
    /**
     * Get a list of genomes
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListGenomesResponse getGenomes(QueryParams params);
    
    /**
     * Get a gene by id
     * @param id genome id
     * @return the single response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetGenomeResponse getGenome(String id)throws ResourceNotFoundException,ResourceForbiddenException;
}
