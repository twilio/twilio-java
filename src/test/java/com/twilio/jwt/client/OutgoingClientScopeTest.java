package com.twilio.jwt.client;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link OutgoingClientScope}.
 */
public class OutgoingClientScopeTest {

    @Test
    public void testGenerate() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        params.put("foo", "bar");

        Scope scope = new OutgoingClientScope.Builder("AP123")
            .clientName("CL123")
            .params(params)
            .build();

        Assert.assertEquals(
            "scope:client:outgoing?appSid=AP123&clientName=CL123&appParams=foo%3Dbar",
            scope.getPayload()
        );
    }
}
