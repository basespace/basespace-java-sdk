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

package com.illumina.basespace.param;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CreateFileParams implements Mappable
{
    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    private String directory;
    public String getDirectory()
    {
        return directory;
    }
    public void setDirectory(String directory)
    {
        this.directory = directory;
    }
    
    private boolean multiPart;
    public boolean isMultiPart()
    {
        return multiPart;
    }
    public void setMultiPart(boolean multiPart)
    {
        this.multiPart = multiPart;
    }
    
    
    @Override
    public MultivaluedMap<String, String> toMap()
    {
        MultivaluedMap<String, String>rtn = new MultivaluedMapImpl();
        rtn.add("Name",getName());
        rtn.add("Directory",getDirectory());
        rtn.add("Multipart",Boolean.toString(isMultiPart()));
        return rtn;
    }

}
