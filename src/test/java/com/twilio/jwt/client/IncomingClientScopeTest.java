package com.twilio.jwt.client;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link IncomingClientScope}.
 */
public class IncomingClientScopeTest {

    @Test
    public void testGenerate() throws UnsupportedEncodingException {
        Scope scope = new IncomingClientScope("foobar");
        assertEquals(
            "scope:client:incoming?clientName=foobar",
            scope.getPayload()
        );
    }
}
