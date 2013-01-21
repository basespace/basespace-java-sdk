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

import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.param.CoverageParams;
import com.illumina.basespace.param.FileParams;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.param.VariantParams;
import com.illumina.basespace.response.GetAppResultResponse;
import com.illumina.basespace.response.GetAppSessionResponse;
import com.illumina.basespace.response.GetCoverageMetadataResponse;
import com.illumina.basespace.response.GetCoverageResponse;
import com.illumina.basespace.response.GetFileResponse;
import com.illumina.basespace.response.GetProjectResponse;
import com.illumina.basespace.response.GetRunResponse;
import com.illumina.basespace.response.GetSampleResponse;
import com.illumina.basespace.response.GetUserResponse;
import com.illumina.basespace.response.ListAppResultsReponse;
import com.illumina.basespace.response.ListFilesResponse;
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
     * Retrieve the current user in the context of the client id used to create this session
     * @return the current user
     */
    public GetUserResponse getCurrentUser();

    /**
     * Retrieve a project by id
     * @param id
     * @return the retrieved project
     */
    public GetProjectResponse getProject(String id);
    
    /**
     * Get a list of projects for a user 
     * @param user the user for which projects will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of projects, or an empty list
     */
    public ListProjectsResponse getProjects(QueryParams params);
    
    public GetProjectResponse createProject(ProjectCompact project);
    
    
    /**
     * Retrieve a sample by id
     * @param id
     * @return the retrieved sample
     */
    public GetSampleResponse getSample(String id);
    
    /**
     * Get a list of samples for a user 
     * @param project the project for which samples will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of samples, or an empty list
     */
    public ListSamplesResponse getSamples(ProjectCompact project,QueryParams params);

    /**
     * Get a list of samples for a run
     * @param run the run for which samples will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of samples, or an empty list
     */
    public ListSamplesResponse getSamples(RunCompact run,QueryParams params);
    
    /**
     * Get a list of app results for a project
     * @param project the project for which appresults will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of app results, or an empty list
     */
    public ListAppResultsReponse getAppResults(ProjectCompact project,QueryParams params);
    
    /**
     * Retrieve an appresult by id
     * @param id
     * @return the retrieved appresult
     */
    public GetAppResultResponse getAppResult(String id);
    
    
    public GetAppSessionResponse getAppSession(String id);
    
    public GetAppSessionResponse updateAppSession(AppSessionCompact appSession);
  
    /**
     * Retrieve a run by id
     * @param id
     * @return the retrieved run
     */
    public GetRunResponse getRun(String id);
    
    /**
     * Get a list of runs for a user
     * @param user the user for which runs will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of runs, or an empty list
     */
    public ListRunsResponse getRuns(QueryParams params);
   
    
    /**
     * Get a list of files for a sample
     * @param sample the sample for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public ListFilesResponse getFiles(SampleCompact sample,FileParams params);
    
    /**
     * Get a list of files for an app result
     * @param appResult the appresult for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public ListFilesResponse getFiles(AppResultCompact appResult,FileParams params);
    
    /**
     * Get a list of files for a run
     * @param run the run for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public ListFilesResponse getFiles(RunCompact run,FileParams params);
    
    /**
     * Retrieve a file by id
     * @param id
     * @return the retrieved file
     */
    public GetFileResponse getFile(String id);

    /**
     * Download a file to local folder. The destination file will have the same name as the source file.
     * @param file the file to download
     * @param targetFolder the target local folder
     */
    public void download(com.illumina.basespace.entity.File file,java.io.File targetFolder);
    
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
     * Get the root URI for BaseSpace API operations
     * @return the root URI
     */
    public URI getRootURI();
    
    /**
     * Get the download URI for a BaseSpace File
     * @param file the file for which to retrieve URI
     * @return the download URI for the file
     */
    public URI getDownloadURI(File file);
    
    /**
     * Get the input stream for a BaseSpace file
     * @param file the file for which to get the InputStream
     * @return the input stream for the file
     */
    public InputStream getFileInputStream(File file);
    
    /**
     * Get an input stream byte range for a basespace file
     * @param file the file for which to get the InputStream
     * @param start the starting position for the stream
     * @param end the ending position for the stream
     * @return the input stream for the file
     */
    public InputStream getFileInputStream(File file,long start,long end);
    
    /**
     * Perform a variant query against the JSON API
     * @param file the variant file to query
     * @param chromosome the chromosome to query
     * @param params the variant fetch parameters
     * @return a list of Variant records, or an empty list
     */
    public ListVariantsResponse queryVariantJSON(File file,String chromosome,VariantParams params);
    
    /**
     * Perform a variant query against the raw API
     * @param file the variant file to query
     * @param chromosome the chromosome to query
     * @param params the variant fetch parameters
     * @return a string containing one or more records from the vcf
     */
    public String queryVariantRaw(File file,String chromosome,VariantParams params);
    
    /**
     * Retrieve coverage information for a file
     * @param file 
     * @param chromosome
     * @param start
     * @param end
     * @param zoomLevel
     * @return a Coverage Record
     */
    public GetCoverageResponse getCoverage(File file,String chromosome,CoverageParams params);
    
    /**
     * Get coverage meta data for a file
     * @param file
     * @param chromosome
     * @return Metadata of the coverage
     */
    public GetCoverageMetadataResponse getCoverageMetaData(File file,String chromosome);
    
    
  
    
}
