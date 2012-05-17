package com.illumina.basespace;

import java.util.EventListener;

/**
 * 
 * @author bking
 *
 */
interface AuthTokenListener extends EventListener
{
    void authTokenReceived(AuthTokenEvent evt);
}
