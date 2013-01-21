package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.infrastructure.NotImplementedException;
import com.illumina.basespace.param.QueryParams;

public abstract class ApiResponse<C, F>
{
    public F get()
    {
        throw new NotImplementedException();
    }

    public C[] items()
    {
        throw new NotImplementedException();
    }

    public QueryParams getQueryMetadata()
    {
        throw new NotImplementedException();
    }

    @JsonProperty("ResponseStatus")
    private ResponseStatus message;

    public ResponseStatus getMessage()
    {
        return message;
    }

    public void setMessage(ResponseStatus message)
    {
        this.message = message;
    }

    @JsonProperty("Notifications")
    private String[] notifications;

    public String[] getNotifications()
    {
        return notifications;
    }

    public void setNotifications(String[] notifications)
    {
        this.notifications = notifications;
    }

}
