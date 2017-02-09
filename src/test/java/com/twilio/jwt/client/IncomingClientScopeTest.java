package com.twilio.jwt.client;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Test class for {@link IncomingClientScope}.
 */
public class IncomingClientScopeTest {

    @Test
    public void testGenerate() throws UnsupportedEncodingException {
        Scope scope = new IncomingClientScope("foobar");
        Assert.assertEquals(
            "scope:client:incoming?clientName=foobar",
            scope.getPayload()
        );
    }
}
