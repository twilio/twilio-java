package com.twilio.jwt.taskrouter;

import com.twilio.http.HttpMethod;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

        Assert.assertEquals(
                "{\"url\":\"http://localhost\",\"method\":\"GET\",\"query_filter\":{\"foo\":{\"required\":true}},\"post_filter\":{},\"allow\":true}",
                p.toJson());
    }

}
