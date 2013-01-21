package com.illumina.basespace.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illumina.basespace.ApiConfiguration;

public interface ConversionContext
{
    public ApiConfiguration getApiConfiguration();
    public ObjectMapper getMapper();
}
