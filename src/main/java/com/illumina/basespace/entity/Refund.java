/**
* Copyright 2013 Illumina
* 
 * Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*    http://www.apache.org/licenses/LICENSE-2.0
* 
 *  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

package com.illumina.basespace.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.illumina.basespace.util.DateDeserializer;


public class Refund extends ApplicationTransaction
{
    @JsonDeserialize(using=DateDeserializer.class)
    @JsonProperty("DateRefunded")
    private Date dateRefunded;
    public Date getDateRefunded()
    {
        return dateRefunded;
    }
   
    @JsonProperty("UserRefundedBy")
    private UserCompact userRefundedBy;
    public UserCompact getUserRefundedBy()
    {
        return userRefundedBy;
    }

    @JsonProperty("RefundComment")
    private String refundComment;
    public String getRefundComment()
    {
        return refundComment;
    }
    
    private String refundSecret;
    public String getRefundSecret()
    {
        return refundSecret;
    }
    @Override
    public String toString()
    {
        return "Refund [dateRefunded=" + dateRefunded + ", userRefundedBy=" + userRefundedBy + ", refundComment="
                + refundComment + ", refundSecret=" + refundSecret + ", toString()=" + super.toString() + "]";
    }

    
}
