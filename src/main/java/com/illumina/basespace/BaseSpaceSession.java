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
import java.util.List;


/**
 * Defines the operations that can be performed against the BaseSpace API
 * @author bking
 *
 */
public interface BaseSpaceSession
{
    /**
     * Retrieve the current user in the context of the client id used to create this session
     * @return the current user
     */
    public User getCurrentUser();

    /**
     * Retrieve a user by id
     * @param id
     * @return the retrieved user
     * @throws ForbiddenResourceException if inadequate permissions in the current context to perform the operation
     */
    public User getUser(String id)throws ForbiddenResourceException;
    
    /**
     * Retrieve a project by id
     * @param id
     * @return the retrieved project
     * @throws ForbiddenResourceException if inadequate permissions in the current context to perform the operation
     */
    public Project getProject(String id)throws ForbiddenResourceException;
    
    /**
     * Get a list of projects for a user 
     * @param user the user for which projects will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of projects, or an empty list
     */
    public List<Project> getProjects(User user,FetchParams params);
    
    /**
     * Retrieve a sample by id
     * @param id
     * @return the retrieved sample
     * @throws ForbiddenResourceException if inadequate permissions in the current context to perform the operation
     */
    public Sample getSample(String id)throws ForbiddenResourceException;
    
    /**
     * Get a list of samples for a user 
     * @param project the project for which samples will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of samples, or an empty list
     */
    public List<Sample> getSamples(Project project,FetchParams params);

    /**
     * Get a list of samples for a run
     * @param run the run for which samples will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of samples, or an empty list
     */
    public List<Sample> getSamples(Run run,FetchParams params);
    
    /**
     * Get a list of app results for a project
     * @param project the project for which appresults will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of app results, or an empty list
     */
    public List<AppResult> getAppResults(Project project,FetchParams params);
  
    /**
     * Retrieve a run by id
     * @param id
     * @return the retrieved run
     * @throws ForbiddenResourceException if inadequate permissions in the current context to perform the operation
     */
    public Run getRun(String id)throws ForbiddenResourceException;
    
    /**
     * Get a list of runs for a user
     * @param user the user for which runs will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of runs, or an empty list
     */
    public List<Run> getRuns(User user,FetchParams params);
   
    
    /**
     * Get a list of files for a sample
     * @param sample the sample for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public List<File> getFiles(Sample sample,FetchParams params);
    
    /**
     * Get a list of files for an app result
     * @param appresult the appresult for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public List<File> getFiles(AppResult appResult,FetchParams params);
    
    /**
     * Get a list of files for a run
     * @param run the run for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public List<File> getFiles(Run run,FetchParams params);
    
    /**
     * Retrieve a file by id
     * @param id
     * @return the retrieved file
     * @throws ForbiddenResourceException if inadequate permissions in the current context to perform the operation
     */
    public com.illumina.basespace.File getFile(String id)throws ForbiddenResourceException;

    /**
     * Download a file to local folder. The destination file will have the same name as the source file.
     * @param file the file to download
     * @param targetFolder the target local folder
     * @param progressListener an optional implementor of the DownloadProgressListener interface that wishes to track progress of the download
     */
    public void download(com.illumina.basespace.File file,java.io.File targetFolder);
    
    /**
     * Add a download listener to this session
     * @param listener
     */
    public void addDownloadListener(DownloadListener listener);
    
    /**
     * Remove a download listener from this session
     * @param listener
     */
    public void removeDownloadListener(DownloadListener listener);
    
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
     * Get extended information for a file
     * @param file the source file for extended information
     * @param clazz the subclass of file to load with more specific information
     * @return the specific subclass of file with extended properties loaded
     */
    public <T extends File>T getFileExtendedInfo(long fileId,Class<T>clazz);
    
    /**
     * Perform a variant query against the JSON API
     * @param file the variant file to query
     * @param chromosome the chromosome to query
     * @param params the variant fetch parameters
     * @return a list of Variant records, or an empty list
     */
    public List<VariantRecord> queryVariantJSON(ExtendedFileInfo file,String chromosome,VariantFetchParams params);
    
    /**
     * Perform a variant query against the raw API
     * @param file the variant file to query
     * @param chromosome the chromosome to query
     * @param params the variant fetch parameters
     * @return a string containing one or more records from the vcf
     */
    public String queryVariantRaw(ExtendedFileInfo file,String chromosome,VariantFetchParams params);
    
    /**
     * Retrieve coverage information for a file
     * @param file 
     * @param chromosome
     * @param start
     * @param end
     * @param zoomLevel
     * @return a Coverage Record
     */
    public CoverageRecord getCoverage(ExtendedFileInfo file,String chromosome,int start,int end,int zoomLevel);
    
    /**
     * Get coverage meta data for a file
     * @param file
     * @param chromosome
     * @return
     */
    public CoverageMetaData getCoverageMetaData(ExtendedFileInfo file,String chromosome);
    
}
