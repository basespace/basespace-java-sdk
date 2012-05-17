package com.illumina.basespace;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

class DateDeserializer extends JsonDeserializer<Date> 
{
    private static final DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    protected Logger logger = Logger.getLogger(BaseSpaceEntity.class.getPackage().getName());
    
    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException
    {
        //   /Date(2012-05-01T17:37:53.0000000)/
        try
        {
            return dateFormat.parse( parser.getText().substring(6,parser.getTextLength()-2));
        }
        catch(Throwable t)
        {
            logger.log(Level.SEVERE,"Error deserializing date from JSON object", t);
            return null;
        }
    }
    
    
}
