package com.twilio.jwt.taskrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.twilio.http.HttpMethod;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Policy}.
 */
public class PolicyTest {

    @Test
    public void testToJson() throws IOException {
        Map<String, FilterRequirement> filter = new HashMap<>();
        filter.put("foo", FilterRequirement.REQUIRED);

        Policy p = new Policy.Builder()
            .url("http://localhost")
            .method(HttpMethod.GET)
            .postFilter(Collections.<String, FilterRequirement>emptyMap())
            .queryFilter(filter)
            .build();

        assertEquals(
                "{\"allow\":true,\"method\":\"GET\",\"post_filter\":{},\"query_filter\":{\"foo\":{\"required\":true}},\"url\":\"http://localhost\"}",
                p.toJson());
    }

}
