package com.illumina.basespace.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.illumina.basespace.ApiClient;
import com.illumina.basespace.entity.ApiResource;
import com.illumina.basespace.entity.AppResultCompact;
import com.illumina.basespace.entity.AppSessionCompact;
import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.entity.GenomeCompact;
import com.illumina.basespace.entity.ProjectCompact;
import com.illumina.basespace.entity.PurchaseCompact;
import com.illumina.basespace.entity.RunCompact;
import com.illumina.basespace.entity.SampleCompact;
import com.illumina.basespace.property.*;

/**
 * General purpose helper class
 * @author bking
 *
 */
public class TypeHelper
{
    public final static TypeHelper INSTANCE = new TypeHelper();
    private Map<Class<? extends ApiResource>,String>typeToPathMap = new HashMap<Class<? extends ApiResource>, String>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = Logger.getLogger(ApiClient.class.getPackage().getName());
    
    public static final String PROPERTY_STRING = "string";
    public static final String PROPERTY_STRING_ARRAY = "string[]";
    public static final String PROPERTY_MAP = "map";
    public static final String PROPERTY_MAP_ARRAY = "map[]";
    public static final String PROPERTY_SAMPLE = "sample";
    public static final String PROPERTY_SAMPLE_ARRAY = "sample[]";
    public static final String PROPERTY_APPRESULT = "appresult";
    public static final String PROPERTY_APPRESULT_ARRAY = "appresult[]";
    public static final String PROPERTY_RUN = "run";
    public static final String PROPERTY_RUN_ARRAY = "run[]";
    public static final String PROPERTY_APPSESSION = "appsession";
    public static final String PROPERTY_APPSESSION_ARRAY = "appsession[]";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_PROJECT_ARRAY = "project[]";
    
    private final String[] PROPERTY_TYPES = {PROPERTY_STRING,PROPERTY_STRING_ARRAY,PROPERTY_MAP,PROPERTY_MAP_ARRAY,
        PROPERTY_SAMPLE,PROPERTY_SAMPLE_ARRAY,PROPERTY_APPRESULT,PROPERTY_APPRESULT_ARRAY,PROPERTY_RUN,PROPERTY_RUN_ARRAY,
        PROPERTY_APPSESSION,PROPERTY_APPSESSION_ARRAY,PROPERTY_PROJECT,PROPERTY_PROJECT_ARRAY};
    
    private final Map<String,Class<? extends Property<?>>>propertyToTypeMap = new HashMap<String,Class<? extends Property<?>>>(PROPERTY_TYPES.length);
    private final Map<String,Class<? extends MultiValueProperty<?>>>multiValuepropertyToTypeMap = new HashMap<String,Class<? extends MultiValueProperty<?>>>(PROPERTY_TYPES.length);
    
    private TypeHelper() 
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        
        
        typeToPathMap.put(ProjectCompact.class, "projects");
        typeToPathMap.put(SampleCompact.class, "samples");
        typeToPathMap.put(RunCompact.class, "runs");
        typeToPathMap.put(AppSessionCompact.class, "appsessions");
        typeToPathMap.put(AppResultCompact.class, "appresults");
        typeToPathMap.put(FileCompact.class, "files");
        typeToPathMap.put(GenomeCompact.class, "genomes");
        typeToPathMap.put(PurchaseCompact.class, "purchases");
        
        
        propertyToTypeMap.put(PROPERTY_STRING, StringProperty.class);
        propertyToTypeMap.put(PROPERTY_STRING_ARRAY, StringProperty.class);
        propertyToTypeMap.put(PROPERTY_MAP, MapProperty.class);
        propertyToTypeMap.put(PROPERTY_MAP_ARRAY, MapProperty.class);
        propertyToTypeMap.put(PROPERTY_APPRESULT, AppResultReference.class);
        propertyToTypeMap.put(PROPERTY_APPRESULT_ARRAY, AppResultReference.class);
        propertyToTypeMap.put(PROPERTY_SAMPLE, SampleReference.class);
        propertyToTypeMap.put(PROPERTY_SAMPLE_ARRAY, SampleReference.class);
        propertyToTypeMap.put(PROPERTY_RUN, RunReference.class);
        propertyToTypeMap.put(PROPERTY_RUN_ARRAY, RunReference.class);
        propertyToTypeMap.put(PROPERTY_APPSESSION, AppSessionReference.class);
        propertyToTypeMap.put(PROPERTY_APPSESSION_ARRAY, AppSessionReference.class);
        propertyToTypeMap.put(PROPERTY_PROJECT, ProjectReference.class);
        propertyToTypeMap.put(PROPERTY_PROJECT_ARRAY, ProjectReference.class);
        
        multiValuepropertyToTypeMap.put(PROPERTY_STRING_ARRAY, StringMultiValueProperty.class);
        
    }
    
    public ObjectMapper getObjectMapper()
    {
        return mapper;
    }

    public String getResourcePath(Class<? extends ApiResource>check,boolean noMatchException)
    {
        for(Class<?>clazz:typeToPathMap.keySet())
        {
            if (clazz.isAssignableFrom(check))
            {
                return typeToPathMap.get(clazz);
            }
        }
        if (noMatchException)throw new IllegalArgumentException("Could not lookup API resource path for type " + check.getName() + ". This type is not supported");
        return null;
            
    }
    
    public Property<?> parseProperty(ObjectNode node)throws IOException
    {
        TextNode val = (TextNode)node.get("Type");
        if (val == null)throw new IllegalArgumentException("Missing type for property");
        Class<? extends Property<?>>clazz = propertyToTypeMap.get(val.asText());
        if (clazz == null)throw new IllegalArgumentException("Unable to find Property class for " + val.asText());
        logger.fine(val.asText() + "," + clazz.toString() + "-->" + node.toString());
        Property<?> prop = mapper.readValue(node.toString(),clazz);
        return prop;
    }
    public MultiValueProperty<?> parseMultiValueProperty(ObjectNode node)throws IOException
    {
        TextNode val = (TextNode)node.get("Type");
        if (val == null)throw new IllegalArgumentException("Missing type for property");
        Class<? extends MultiValueProperty<?>>clazz = multiValuepropertyToTypeMap.get(val.asText());
        if (clazz == null)throw new IllegalArgumentException("Unable to find MultiValueProperty class for " + val.asText());
        logger.fine(val.asText() + "," + clazz.toString() + "-->" + node.toString());
        MultiValueProperty<?> prop = mapper.readValue(node.toString(),clazz);
        return prop;
    }
}
