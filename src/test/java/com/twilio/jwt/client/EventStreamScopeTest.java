package com.twilio.jwt.client;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link EventStreamScope}.
 */
public class EventStreamScopeTest {

    @Test
    public void testGenerate() throws UnsupportedEncodingException {
        Map<String, String> filters = new HashMap<>();
        filters.put("foo", "bar");

        Scope scope = new EventStreamScope.Builder()
            .filters(filters)
            .build();

        assertEquals(
            "scope:stream:subscribe?path=/2010-04-01/Events&appParams=foo%3Dbar",
            scope.getPayload()
        );
    }

}
