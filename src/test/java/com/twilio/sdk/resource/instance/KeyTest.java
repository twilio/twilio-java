package com.twilio.sdk.resource.instance;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class KeyTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("key.json");
    }

    @Test
    public void testCreate() throws Exception {
        setExpectedServerReturnCode(201);
        Key signingKey = restClient.getAccount().getKey("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertNotNull(signingKey);
        assertEquals("Test Key", signingKey.getFriendlyName());
    }

    @Test
    public void testDelete() throws Exception {
        setExpectedServerAnswer(null);
        setExpectedServerReturnCode(204);
        assertTrue(restClient.getAccount().getKey("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").delete());
    }

    @Test
    public void testGet() {
        Key signingKey = restClient.getAccount().getKey("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertNotNull(signingKey);
        assertEquals("SKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", signingKey.getSid());
        assertEquals("Test Key", signingKey.getFriendlyName());
        assertNotNull(signingKey.getDateCreated());
        assertNotNull(signingKey.getDateUpdated());
    }
}
