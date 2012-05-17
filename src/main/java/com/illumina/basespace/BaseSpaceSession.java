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
     * Retrieve an analysis by id
     * @param id
     * @return the retrieved analysis
     * @throws ForbiddenResourceException if inadequate permissions in the current context to perform the operation
     */
    public Analysis getAnalysis(String id)throws ForbiddenResourceException;
    
    /**
     * Get a list of analyses for a project 
     * @param project the project for which analyses will be retrieved
     * @param optional params fetch parameters to limit the scope of the result list
     * @return a list of analyses, or an empty list
     */
    public List<Analysis> getAnalyses(Project project,FetchParams params);
  
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
     * Get a list of files for an analysis
     * @param analysis the analysis for which files will be retrieved
     * @param params optional fetch parameters to limit the scope of the result list
     * @return a list of files, or an empty list
     */
    public List<File> getFiles(Analysis analysis,FetchParams params);
    
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
    public void download(com.illumina.basespace.File file,java.io.File targetFolder,
            DownloadProgressListener progressListener);
}
