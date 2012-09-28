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

/**
 * This exception wraps a BaseSpace Access Denied response when attempting to obtain an access token
 * @author bking
 *
 */
public class AccessDeniedException extends BaseSpaceException
{
    @Override
    public String getMessage()
    {
        return "The user was denied authorization for the application";
    }
    
}
