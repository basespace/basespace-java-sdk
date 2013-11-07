package com.illumina.basespace.property;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.illumina.basespace.util.TypeHelper;

public class MultiValuePropertyDeserializer extends JsonDeserializer<MultiValueProperty<?>>
{

    @Override
    public MultiValueProperty<?> deserialize(JsonParser parser, DeserializationContext context) throws IOException,
            JsonProcessingException
    {
        ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        if (node instanceof ObjectNode)
        {
           return TypeHelper.INSTANCE.parseMultiValueProperty((ObjectNode)node);
        }
        return null;
    }

}
