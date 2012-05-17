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

import java.lang.annotation.Annotation;

/**
 * 
 * @author bking
 *
 */
class BaseSpaceUtilities
{
    static void assertNotNull(Object object,String msg)
    {
        if (object == null)throw new RuntimeException("Unallowed null: " + msg);
    }
    
    @SuppressWarnings("unchecked")
    static <T extends Annotation> T getAnnotation(Class<T>annotationClass,Class<?>targetClass)
    {
        Annotation path = targetClass.getAnnotation(annotationClass);
        assertNotNull(path,targetClass.getName() + " missing required annotation " + annotationClass.getName());
        return (T) path;
    }
}
