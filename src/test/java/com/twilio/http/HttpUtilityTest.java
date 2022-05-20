package com.twilio.http;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HttpUtilityTest {
    private List<String> userAgentExtensionsEmpty = new ArrayList<>();;
    private List<String> userAgentExtensions = Arrays.asList("ce-appointment-reminders/1.0.0 ", "code-exchange");;

    @Test
    public void getUserAgentStringTest() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensionsEmpty);
        assertNotNull(userAgentString);
    }

    @Test
    public void getUserAgentStringCustomTest() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensions, true);
        assertTrue(userAgentString.contains("custom"));
    }
}
