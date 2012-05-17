package com.illumina.basespace.test;

import com.illumina.basespace.BaseSpaceConfiguration;

public abstract class TestBaseSpaceConfiguration implements BaseSpaceConfiguration
{

    @Override
    public String getVersion()
    {
        return "v1pre1";
    }

    @Override
    public String getApiRootUri()
    {
        return "https://api.cloud-endor.illumina.com";
    }

    @Override
    public String getAuthTokenUriFragment()
    {
        return "/oauthv2/token";
    }

    @Override
    public String getAuthorizationUri()
    {
        return "https://cloud-endor.illumina.com/oauth/authorize";
    }

    @Override
    public int getAuthCodeListenerPort()
    {
        return 7777;
    }

}
