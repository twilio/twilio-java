package com.twilio.http;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HttpUtilityTest {
    private List<String> userAgentExtensionsEmpty = new ArrayList<>();
    private List<String> userAgentExtensions = Arrays.asList("ce-appointment-reminders/1.0.0", "code-exchange");

    @Test
    public void getUserAgentStringTest() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensions);
        assertTrue(userAgentString.endsWith("ce-appointment-reminders/1.0.0 code-exchange"));
    }

    @Test
    public void getUserAgentStringEmptyTest() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensionsEmpty);
        assertFalse(userAgentString.contains("ce-appointment-reminders/1.0.0 code-exchange"));
    }

    @Test
    public void getUserAgentStringCustomTest() {
        String userAgentString = HttpUtility.getUserAgentString(userAgentExtensions, true);
        assertTrue(userAgentString.endsWith("ce-appointment-reminders/1.0.0 code-exchange custom"));
    }
}
