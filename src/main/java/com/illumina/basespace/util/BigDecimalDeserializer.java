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

package com.illumina.basespace.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * @author bking
 *
 */
public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> 
{
    protected static Logger logger = Logger.getLogger(BigDecimalDeserializer.class.getPackage().getName());
    
    @Override
    public BigDecimal deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException
    {
        try
        {
            return new BigDecimal(parser.getText());
        }
        catch(Throwable t)
        {
            logger.log(Level.SEVERE,"Error deserializing bigdecimal from JSON object", t);
            return null;
        }
    }
}
