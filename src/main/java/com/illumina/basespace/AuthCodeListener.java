package com.illumina.basespace;

import java.util.EventListener;

/**
 * 
 * @author bking
 *
 */
interface AuthCodeListener extends EventListener
{
    void authCodeReceived(AuthCodeEvent evt);
}
