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
import com.illumina.basespace.entity.ApiResource;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.entity.AppSessionStatus;
import com.illumina.basespace.entity.File;
import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.entity.ProductCompact;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.entity.ReferenceCompact;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.entity.UploadStatus;
import com.illumina.basespace.file.DownloadListener;
import com.illumina.basespace.infrastructure.ResourceNotFoundException;
import com.illumina.basespace.param.CoverageParams;
import com.illumina.basespace.param.FileParams;
import com.illumina.basespace.param.PositionalQueryParams;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.property.Property;
import com.illumina.basespace.response.CreatePurchaseResponse;
import com.illumina.basespace.response.GetAppResultResponse;
import com.illumina.basespace.response.GetAppSessionResponse;
import com.illumina.basespace.response.GetCoverageMetadataResponse;
import com.illumina.basespace.response.GetCoverageResponse;
import com.illumina.basespace.response.GetFileRedirectMetaDataResponse;
import com.illumina.basespace.response.GetFileResponse;
import com.illumina.basespace.response.GetFileUploadResponse;
import com.illumina.basespace.response.GetGenomeResponse;
import com.illumina.basespace.response.GetProjectResponse;
import com.illumina.basespace.response.GetPropertyResponse;
import com.illumina.basespace.response.GetPurchaseResponse;
import com.illumina.basespace.response.GetRefundResponse;
import com.illumina.basespace.response.GetRunResponse;
import com.illumina.basespace.response.GetSampleResponse;
import com.illumina.basespace.response.GetUserResponse;
import com.illumina.basespace.response.GetVariantSetResponse;
import com.illumina.basespace.response.ListAppResultsResponse;
import com.illumina.basespace.response.ListAppSessionsResponse;
import com.illumina.basespace.response.ListFilesResponse;
import com.illumina.basespace.response.ListGenomesResponse;
import com.illumina.basespace.response.ListProductsResponse;
import com.illumina.basespace.response.ListProjectsResponse;
import com.illumina.basespace.response.ListPropertiesResponse;
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
     * Gets the current user
     * @return the user single item response
     */
    public GetUserResponse getCurrentUser();

    /**
     * To retrieve a specific project by its Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the project
     * @param id project id
     * @return the project single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetProjectResponse getProject(String id) throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Retrieve a list of projects that the user owns or has been invited to
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the project for it to be visible in this listing
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the project list response
     */
    public ListProjectsResponse getProjects(QueryParams params);
    
    /**
     * Create a project for the current user
     * <br><br><span class="strong">Permissions:</span> CREATE PROJECTS access to the user's data
     * @param projectName The name of the project
     * @return the project single item response
     */
    public GetProjectResponse createProject(String projectName);
    
    /**
     * Retrieve a specific sample by its Id
     * @param id sample id
     * @return the sample single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetSampleResponse getSample(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Retrieve a collection of samples inside of a given project
     * @param project project for which samples will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the sample list response
     */
    public ListSamplesResponse getSamples(ProjectCompact project,QueryParams params);

    /**
     * Get a list of samples for a run
     * @param run run for which samples will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the sample list response
     */
    public ListSamplesResponse getSamples(RunCompact run,QueryParams params);
    
    /**
     * Retrieve a list of AppResults within a specific project Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the Project
     * @param project project for which appresults will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the appresult list response
     */
    public ListAppResultsResponse getAppResults(ProjectCompact project,QueryParams params);
    
    /**
     * Returns a list of the AppResults that were created by an AppSession
     * <br><br><span class="strong">Permissions:</span> A valid access token for the user
     * @param appSession appSession for which appresults will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the appresult list response
     */
    public ListAppResultsResponse getAppResults(AppSessionCompact appSession,QueryParams params);
    
    /**
     * Retrieve a specific AppResult by its Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the AppResult either directly or via access to a project containing the AppResult
     * @param id appresult id
     * @return the appresult single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetAppResultResponse getAppResult(String id)throws ResourceNotFoundException,ResourceForbiddenException;

    /**
     * To create or change an AppResult within a specific project. A new appresult is created within this project
     * <br><br><span class="strong">Permissions:</span> WRITE access to the project within which the AppResult is, or will be, contained
     * @param project project which will contain the appresult
     * @param name Name of the AppResult (required)
     * @param description Brief decription of the AppResult
     * @param hrefAppSession  Specifies a location for the AppSession for the new AppResult. This field may only be set if the status parameter of this AppSession is neither complete nor aborted, and also the application field must match.
     * @param references The Reference field shows this AppResult's inputs or relationship to other resources in BaseSpace
     * @return the appresult single item response
     */
    public GetAppResultResponse createAppResult(ProjectCompact project,String name,String description,URI hrefAppSession,
            ReferenceCompact[]references);
        
    /**
     * Retrieve an appsession by its Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to any containing AppResults gives visibility to appsession, the clientid and clientsecret must match the appsession's application 
     * @param id appsession id
     * @return the appsession single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetAppSessionResponse getAppSession(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * A list of the current user's AppSessions
     * @param appId The Id of the Application that created this AppSession
     * @param status To Filter the list by Status of the AppSession, this query parameter can be used
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the appsession list response
     */
    public ListAppSessionsResponse getAppSessions(String appId,String status,QueryParams params);
    
    /**
     * Change the current status of the AppSession by its Id
     * <br><br><span class="strong">Permissions:</span> WRITE access to the referenced AppResults through its parent Project
     * @param appsession appsession which will be updated
     * @param status Status of the AppSession, the Samples or AppResults created will inherit this value. This parameter is REQUIRED otherwise an error will be thrown
     * @param statusSummary A brief summary of what is currently happening with the AppResult or Sample that will be displayed for the user next to the status. This may be updated multiple times. This field has a limit of 128 characters
     * @return the appsession single item response
     */
    public GetAppSessionResponse updateAppSession(AppSessionCompact appsession,AppSessionStatus status,String statusSummary);
  
    /**
     * Retrieve a specific run by its Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the Run
     * @param id run id
     * @return the run single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetRunResponse getRun(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Retrieve a list of runs accessible by the user current user
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the runs the current user has access to
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the run list response
     */
    public ListRunsResponse getRuns(QueryParams params);
   
    /**
     * Retrieve a collection of files belonging to a given sample Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the Sample to which the files belong
     * @param sample the sample for which files will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the file list response
     */
    public ListFilesResponse getFiles(SampleCompact sample,FileParams params);
    
    /**
     * Retrieve a list of files within a given AppResult by its Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the AppResult  to which the files belong
     * @param appResult the appResult for which files will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the file list response
     */
    public ListFilesResponse getFiles(AppResultCompact appResult,FileParams params);
    
    /**
     * Retrieve a collection of files belonging to a given run Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the Run to which the files belong
     * @param run the run for which files will be retrieved
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the file list response
     */
    public ListFilesResponse getFiles(RunCompact run,FileParams params);
    
    /**
     * Retrieve information about a given file by its Id
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the Run, Sample, or AppResult to which the file belongs
     * @param id file id
     * @return the file single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetFileResponse getFile(String id)throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Change or create files within a particular AppResult
     * <br><br><span class="strong">Permissions:</span> WRITE access to the AppResult to which the file belongs
     * @param appresult appresult which will contain the file
     * @param fileName The name of the file as it will be in the AppResult
     * @param contentType  the content type of the file being uploaded. This header is required. When downloading this file, the same Content-Type will be returned.
     * @param directory Use if the data has a directory structure. This will create a directory within the AppResult to place this file.
     * @return the file single item response
     */
    public GetFileResponse startMultipartUpload(AppResultCompact appresult,String fileName,String contentType,String directory);
    
    /**
     * To upload file parts to the particular file Id. Parts may be uploaded in parallel and, in the event that the connection fails, the upload for each part may be retried. The parts will be sorted in ascending order regardless of the order of upload (i.e. part 5 can be uploaded before part 4, but will still be sorted normally once fully uploaded). 
     * <br><br><span class="strong">Permissions:</span> WRITE access to the particular file
     * @param file file for which part will be uploaded
     * @param partNumber Each part must be numbered from 1 to 10000, when uploaded the file's parts will be sorted in ascending order.
     * @param MD5Hash A base64 encoded 128bit MD5 checksum may be provided prior to the upload, which will be checked by the server. By providing a computed value, an app can ensure that the checksum is matched after upload. If the server-calculated value does not match the provided value, the upload will be rejected. Although it is optional, it is highly recommended to ensure data integrity after upload
     * @param part stream for the part
     * @return the file upload single item response
     */
    public GetFileUploadResponse uploadFilePart(FileCompact file,int partNumber,String MD5Hash,InputStream part);
    
    /**
     * This method is used to change the upload status for a multi-part file upload
     * <br><br><span class="strong">Permissions:</span> WRITE access to the particular file that will be changed
     * @param file file for which status will be changed
     * @param status The status of the upload
     * @return the file single item response
     */
    public GetFileResponse setMultipartUploadStatus(FileCompact file,UploadStatus status);
    
    
    /**
     * Get file redirect metadata for a file
     * @param file
     * @return the single response
     */
    public GetFileRedirectMetaDataResponse getFileRedirectMetaData(FileCompact file);
    
    /**
     * Download the contents of a file
     * <br><br><span class="strong">Permissions:</span> READ access to the Run, Sample, or AppResult to which the file belongs
     * @param file the which will be downloaded
     * @param targetFolder local path which is the target of the download. If target is a file, the source contents will be copied to that file. If target is a directory, a file with the same name as the source file will be created in the target folder and its contents copied
     * @param listener optional (can be null)listener to be notified of download events
     */
    public void download(com.illumina.basespace.entity.File file,java.io.File target,
            DownloadListener listener);
    
    /**
     * Get the download URI for a file
     * @param file
     * @return the download URI
     */
    public URI getDownloadURI(FileCompact file);
    
    /**
     * Get the input stream for a file
     * @param file
     * @return the InputStream
     */
    public InputStream getFileInputStream(FileCompact file);
    
    /**
     * Get a byte range input stream for a file
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the file
     * @param file
     * @param start start of range
     * @param end end of range
     * @return the InputStream
     */
    public InputStream getFileInputStream(FileCompact file,long start,long end);
    
    /**
     * Retrieve a variant set
     * <br><br><span class="strong">Permissions:</span> BROWSE access to the file
     * @param file the vcf file on which to query
     * @return the variantset single item response
     */
    public GetVariantSetResponse getVariantSet(File file);
    
    /**
     * Retrieve variants in a specific chromosome
     * <br><br><span class="strong">Permissions:</span> READ access to the AppResult to which the source file belongs
     * @param file the vcf file on which to perform query
     * @param chromosome The chromosome within which the variantset will be analyzed.
     * @param params positional query for the variant
     * @return the variant list response
     */
    public ListVariantsResponse getVariants(File file,String chromosome,PositionalQueryParams params);
    
    /**
     * Retrieve variants in a specific chromosome
     * <br><br><span class="strong">Permissions:</span> READ access to the AppResult to which the source file belongs
     * @param file the vcf file on which to perform query
     * @param chromosome The chromosome within which the variantset will be analyzed.
     * @param params positional query for the variant
     * @return a string containing 0..n records from the vcf file delimited according to the vcf specification
     */
    public String getVariantRawRecord(FileCompact file,String chromosome,PositionalQueryParams params);
    
    /**
     * Retrieve coverage data in a specific chromosome
     * <br><br><span class="strong">Permissions:</span> READ access to the sample to which the source file belongs
     * @param file the bam file on which to perform query
     * @param chromosome The chromosome within which the coverage will be analyzed
     * @param params coverage parameters
     * @return the coverage single item response
     */
    public GetCoverageResponse getCoverage(File file,String chromosome,CoverageParams params);
    
    /**
     * Retrieve metadata about the coverage in a chromosome
     * <br><br><span class="strong">Permissions:</span> READ access to the sample to which the source file belongs
     * @param file the bam file on which to perform query
     * @param chromosome The chromosome within which the coverage will be analyzed
     * @return the coverage metadata single item response
     */
    public GetCoverageMetadataResponse getCoverageMetaData(File file,String chromosome);
    
    /**
     * Retrieve a list of all genomes available in BaseSpace
     * @param params optional (can be null) parameters to limit the scope of results
     * @return the genome list response
     */
    public ListGenomesResponse getGenomes(QueryParams params);
    
    /**
     * Retrieve a specific genome by its Id
     * @param id genome id
     * @return the genome single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetGenomeResponse getGenome(String id)throws ResourceNotFoundException,ResourceForbiddenException;

    /**
     * Get a list of products accessible to current user
     * @param params optional query parameters to limit the result scope
     * @return the list response
     */
    public ListProductsResponse getProducts(String[]tags,String[]productIds,QueryParams params);

    /**
     * Retrieve a specific purchase by its Id. You will only receive a response if you are the app owner or the user of the purchase
     * @param id purchase id
     * @return the purchase single item response
     * @throws ResourceNotFoundException
     * @throws ResourceForbiddenException
     */
    public GetPurchaseResponse getPurchase(String id) throws ResourceNotFoundException,ResourceForbiddenException;
    
    /**
     * Initiate a purchase for the user. Use this method to begin the process of charging a user for the application. When creating a purchase, keep your access_token and RefundSecret for your records, your app will need these in order to issue a Refund on a Purchase
     * @param appsession The AppSession that this Purchase is associated with
     * @param products An array of the Products that are part of this Purchase
     * @return the create purchase single item response
     */
    public CreatePurchaseResponse createPurchase(AppSessionCompact appsession,ProductCompact[]products);
    
    /**
     * To refund this purchase and give the user their iCredits back
     * @param purchaseId Id of the purchase that will be refunded
     * @param refundSecret The RefundSecret provided in the Response from the POST: purchases request
     * @param comment An optional comment can be added for this refund
     * @return the create refund single item response
     */
    public GetRefundResponse createRefund(String purchaseId,String refundSecret,String comment);

    public ListPropertiesResponse getProperties(ApiResource resource);
    
    public GetPropertyResponse getProperty(ApiResource resource,String propertyName);
   
    public ListPropertiesResponse putProperties(ApiResource resource,Property[]properties);
    
    public void deleteProperty(ApiResource resource,String propertyName);
}
