package com.illumina.basespace.property;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.illumina.basespace.ApiClient;
import com.illumina.basespace.util.TypeHelper;

public class PropertyArrayDeserializer extends JsonDeserializer<Property<?>[]> 
{
    private final Logger logger = Logger.getLogger(ApiClient.class.getPackage().getName());
    
    @Override
    public Property<?>[] deserialize(JsonParser parser, DeserializationContext context) throws IOException,
            JsonProcessingException
    {
        List<Property<?>>properties = new ArrayList<Property<?>>();
        ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        if (node instanceof ArrayNode)
        {
            ArrayNode array = (ArrayNode)node;
            for (int i = 0; i < array.size();i++)
            {
                properties.add(TypeHelper.INSTANCE.parseProperty((ObjectNode) array.get(i)));
            }
            return properties.toArray(new Property[properties.size()]);
        }
        return null;
    }
}
