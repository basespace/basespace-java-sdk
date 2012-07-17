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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;

public class BaseSpaceURLConnectionFactory implements HttpURLConnectionFactory
{
    BaseSpaceConfiguration configuration = null;
    BaseSpaceURLConnectionFactory(BaseSpaceConfiguration config)
    {
        this.configuration = config;
    }

    @Override
    public HttpURLConnection getHttpURLConnection(URL url) throws IOException
    {
        Proxy proxy = null;
        if (configuration.getProxyHost() != null && configuration.getProxyHost().length() > 0)
        {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(configuration.getProxyHost(), configuration.getProxyPort()));
        }
        return (HttpURLConnection) (proxy != null?url.openConnection(proxy):url.openConnection());
    }

}
