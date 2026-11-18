package com.twilio.http;

import com.twilio.exception.InvalidRequestException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    @Test
    public void validatePathParamAllowsValidValues() {
        assertEquals("FW00000000000000000000000000000000",
            HttpUtility.validatePathParam("FW00000000000000000000000000000000"));
        assertEquals("+15551234567", HttpUtility.validatePathParam("+15551234567"));
        assertEquals("my name.txt", HttpUtility.validatePathParam("my name.txt"));
    }

    @Test
    public void validatePathParamRejectsInvalidValues() {
        String[] invalid = {null, "", ".", "..", "FW1/Executions/FN1", "FW1?x=1", "FW1#f", "../FW1"};
        for (String value : invalid) {
            try {
                HttpUtility.validatePathParam(value);
                fail("Expected InvalidRequestException for: " + value);
            } catch (InvalidRequestException e) {
                // expected
            }
        }
    }
}
