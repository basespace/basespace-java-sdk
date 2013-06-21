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

package com.illumina.basespace.response;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.illumina.basespace.entity.Purchase;
import com.illumina.basespace.entity.PurchaseCompact;

public class CreatePurchaseResponse extends ApiResponse<PurchaseCompact, Purchase>
{
    @JsonProperty("Response")
    private PurchaseResult item;

    @Override
    public PurchaseResult get()
    {
        return item;
    }

    public class PurchaseResult extends Purchase
    {
        @JsonProperty("HrefPurchaseDialog")
        private URI hrefPurchaseDialog;
        public URI getHrefPurchaseDialog()
        {
            return hrefPurchaseDialog;
        }
        public void setHrefPurchaseDialog(URI hrefPurchaseDialog)
        {
            this.hrefPurchaseDialog = hrefPurchaseDialog;
        }
        
        private String refundSecret;
        public String getRefundSecret()
        {
            return refundSecret;
        }
        public void setRefundSecret(String refundSecret)
        {
            this.refundSecret = refundSecret;
        }
    }
}
