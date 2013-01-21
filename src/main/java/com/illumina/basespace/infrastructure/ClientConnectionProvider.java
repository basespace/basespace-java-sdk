package com.illumina.basespace.infrastructure;

import com.illumina.basespace.ApiConfiguration;
import com.illumina.basespace.entity.UserOwnedResource;
import com.illumina.basespace.param.QueryParams;
import com.illumina.basespace.response.ApiResponse;
import com.sun.jersey.api.client.Client;

public interface ClientConnectionProvider
{
    public Client getClient();
    public ApiConfiguration getConfiguration();
    public <T extends ApiResponse<?,?>> T getResponse(Class<? extends ApiResponse<?,?>>clazz,
            String path,QueryParams params);
    public <T extends ApiResponse<?,?>> T postResource(Class<? extends ApiResponse<?,?>>clazz,
            String path,UserOwnedResource resource);    
    public String getJSONResponse(String path,QueryParams params); 
    
}
