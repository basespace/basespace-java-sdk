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

import java.net.URI;

/**
 * Base class for BaseSpace specific exceptions
 * @author bking
 *
 */
public class BaseSpaceException extends RuntimeException
{
    private URI uri;
    private int errorCode;
    
    public BaseSpaceException(String message)
    {
        this(message,null,null);
    }

    public BaseSpaceException(String message,Throwable cause)
    {
        this(message,cause,null);
    }
    
    public BaseSpaceException(String message,Throwable cause,URI uri)
    {
        this(message,cause,uri,0);
    }
    
    public BaseSpaceException(URI uri,int errorCode)
    {
        this(null,null,uri,errorCode);
    }
    
    public BaseSpaceException(URI uri,String message,int errorCode)
    {
        this(message,null,uri,errorCode);
    }
    
    public BaseSpaceException(String message,Throwable cause,URI uri,int errorCode)
    {
        super(message,cause);
        this.uri = uri;
        this.errorCode = errorCode;
    }

    public BaseSpaceException(Throwable cause)
    {
        super(cause);
    }
 

    public BaseSpaceException()
    {
        super();
    }

    public URI getUri()
    {
        return uri;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public String getMessage()
    {
        StringBuilder sb = new StringBuilder(256);
        sb.append("An error occurred calling the BaseSpace API");
        sb.append("\n\tError Code: " + getErrorCode());
        sb.append("\n\tMessage: " + (super.getMessage()!= null?super.getMessage():"<None>"));
        sb.append("\n\tUri: " + (getUri()!= null?getUri().toString():""));
        return sb.toString();
    }

    
    
    
    
}