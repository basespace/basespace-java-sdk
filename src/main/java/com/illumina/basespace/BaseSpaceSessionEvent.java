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

import java.util.EventObject;

/**
 * 
 * @author bking
 *
 */
public class BaseSpaceSessionEvent extends EventObject
{
    private BaseSpaceSession session;
    private String sessionId;
    
    public BaseSpaceSessionEvent(Object src,String sessionId,BaseSpaceSession session)
    {
        super(src);
        this.session = session;
        this.sessionId = sessionId;
    }

    public BaseSpaceSession getSession()
    {
        return session;
    }

    public String getSessionId()
    {
        return sessionId;
    }
    
}
