package com.twilio.jwt.client;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

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

        assertEquals(
            "scope:client:outgoing?appSid=AP123&clientName=CL123&appParams=foo%3Dbar",
            scope.getPayload()
        );
    }
}
