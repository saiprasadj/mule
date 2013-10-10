/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.test.usecases.sync;

import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TcpToFileTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "org/mule/test/usecases/sync/tcp-to-file.xml";
    }

    @Test
    public void testSyncResponse() throws Exception
    {
        MuleClient client = new MuleClient(muleContext);
        String payload = "payload";

        client.sendNoReceive("tcp://localhost:4444", payload, null);

        MuleMessage msg = client.request("file://temp/tests/mule", 10000);
        assertNotNull(msg);
        assertEquals(payload, msg.getPayloadAsString());
    }
}
