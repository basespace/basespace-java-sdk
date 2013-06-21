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

package com.illumina.basespace.auth;

import java.net.URI;

import com.illumina.basespace.infrastructure.BaseSpaceException;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Indicates an attempt was made to retrieve a server resource without adequate permissions
 * in the context of the current client user permissions
 * @author bking
 *
 */
public class ResourceForbiddenException extends BaseSpaceException
{
    private String operation;
    public ResourceForbiddenException(String operation,URI uri)
    {
        super(uri,0);
        this.operation = operation;
    }
    
    public ResourceForbiddenException(String operation,String message,URI uri)
    {
        super(uri,message,ClientResponse.Status.FORBIDDEN.getStatusCode());
        this.operation = operation;
    }
    
    
    public String getMessage()
    {
        if (super.getMessage() != null)return super.getMessage();
        return operation + " to " + getUri().toString() + " is forbidden for the current user. Please check permissions.";
    }
}
