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

package com.illumina.basespace.infrastructure;

import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illumina.basespace.ApiConfiguration;
import com.illumina.basespace.param.Mappable;
import com.illumina.basespace.response.ApiResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.representation.Form;
/**
 * 
 * @author bking
 *
 */
public interface ClientConnectionProvider
{
    public Client getClient();
    public ApiConfiguration getConfiguration();
    
    
    public <T extends ApiResponse<?,?>> T getResponse(Class<? extends ApiResponse<?,?>>clazz,
            String path,Mappable params,Map<String,String>headers);
    
    
    public <T extends ApiResponse<?,?>> T postForm(Class<? extends ApiResponse<?,?>>clazz,
            String path,Map<String,String>headers,Form data);  
    
    public <T extends ApiResponse<?,?>> T postJson(Class<? extends ApiResponse<?,?>>clazz,
            String path,Map<String,String>headers,Jsonable json);  
    
    public <T extends ApiResponse<?,?>> T putFile(Class<? extends ApiResponse<?,?>>clazz,
            String path,Map<String,String>headers,InputStream file);  
    
    public String getStringResponse(String path,Mappable params,Map<String,String>headers); 
   
    public ObjectMapper getMapper();
    
    
    
}
