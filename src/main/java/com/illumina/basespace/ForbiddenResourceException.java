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

import java.net.URI;

/**
 * Indicates an attempt was made to retrieve a server resource without adequate permissions
 * in the context of the current session user permissions
 * @author bking
 *
 */
public class ForbiddenResourceException extends RuntimeException
{
    private URI uri;
    public ForbiddenResourceException(URI uri)
    {
        this.uri = uri;
    }
    
    public String getMessage()
    {
        return "Resource at " + uri.toString() + " is forbidden. Please check permissions.";
    }
}
