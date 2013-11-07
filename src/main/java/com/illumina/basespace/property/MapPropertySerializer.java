package com.illumina.basespace.property;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MapPropertySerializer extends JsonSerializer<Map<String,String[]>>
{

    @Override
    public void serialize(Map<String, String[]> map, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException,
            JsonProcessingException
    {
        jsonGenerator.writeStartArray();
        for(String key:map.keySet())
        {
            
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("Key",key);
            jsonGenerator.writeArrayFieldStart("Values");
            for(String val:map.get(key))
            {
                jsonGenerator.writeString(val);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
