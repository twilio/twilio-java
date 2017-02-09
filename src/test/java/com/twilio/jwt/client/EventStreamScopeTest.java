package com.twilio.jwt.client;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

        Assert.assertEquals(
            "scope:stream:subscribe?path=/2010-04-01/Events&appParams=foo%3Dbar",
            scope.getPayload()
        );
    }

}
