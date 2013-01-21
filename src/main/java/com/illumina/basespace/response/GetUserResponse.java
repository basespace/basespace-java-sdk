package com.illumina.basespace.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.User;
import com.illumina.basespace.entity.UserCompact;

public class GetUserResponse extends ApiResponse<UserCompact, User>
{
    @JsonProperty("Response")
    private User item;

    @Override
    public User get()
    {
        return item;
    }
}
