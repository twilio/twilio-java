package com.twilio.http;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HttpUtilityTest {

    private List<String> userAgentExtensionsNull;
    private List<String> userAgentExtensions;

    private HttpUtility httpUtility;

    @Before
    public void setUp() {
        userAgentExtensionsNull = new ArrayList<>();
        userAgentExtensions = Arrays.asList("ce-appointment-reminders/1.0.0 ", "code-exchange");
    }

    @Test
    public void getUserAgentStringTest01() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensionsNull);
        assertNotNull(userAgentString);
    }

    @Test
    public void getUserAgentStringTest02() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensions, true);
        assertEquals(userAgentString.contains("custom"), true);
    }
}
