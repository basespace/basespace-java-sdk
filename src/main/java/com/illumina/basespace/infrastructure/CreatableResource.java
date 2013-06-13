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

import com.sun.jersey.api.representation.Form;

/**
 * A resource that can be created and must support representing itself in an HTTP-friendly
 * data structure
 * @author bking
 *
 */
public interface CreatableResource
{
    /**
     * Convert this resource to a Form for HTTP-POST transmission
     * @return this resource as a Form
     */
    public Form toForm();
    
    /**
     * Convert this resource to a JSON string
     * @param context Conversion context 
     * @return a JSON string representation of this resource
     */
    public String toJson(ConversionContext context);
}
