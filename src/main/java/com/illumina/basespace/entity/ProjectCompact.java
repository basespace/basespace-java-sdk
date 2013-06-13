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

package com.illumina.basespace.entity;

import com.illumina.basespace.infrastructure.ConversionContext;
import com.illumina.basespace.infrastructure.CreatableResource;
import com.illumina.basespace.util.ValidationHelper;
import com.sun.jersey.api.representation.Form;

public class ProjectCompact extends OwnedResource implements CreatableResource 
{

    public ProjectCompact()
    {
        super();
    }

    public ProjectCompact(String id)
    {
        super(id);
    }
    
    public void setName(String name)
    {
        super.setName(name);
    }
    
    @Override
    public Form toForm()
    {
        ValidationHelper.assertNotNull(getName(), "Project name");
        Form rtn = new Form();
        rtn.add("name",getName());
        return rtn;
    }

    @Override
    public String toJson(ConversionContext context)
    {
        return null;
    }
    
}
