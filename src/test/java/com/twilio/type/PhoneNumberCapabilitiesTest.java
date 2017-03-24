package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for {@link PhoneNumberCapabilities}.
 */
public class PhoneNumberCapabilitiesTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"MMS\": true,\n" +
            "    \"SMS\": false,\n" +
            "    \"voice\": true\n" +
            "}";

        PhoneNumberCapabilities pnc = fromJson(json, PhoneNumberCapabilities.class);
        Assert.assertTrue(pnc.getMms());
        Assert.assertTrue(pnc.getVoice());
        Assert.assertFalse(pnc.getSms());
    }

    @Test
    public void testFromJsonExtraField() throws IOException {
        String json = "{\n" +
            "    \"MMS\": true,\n" +
            "    \"SMS\": false,\n" +
            "    \"voice\": true,\n" +
            "    \"foo\": true\n" +
            "}";

        PhoneNumberCapabilities pnc = fromJson(json, PhoneNumberCapabilities.class);
        Assert.assertTrue(pnc.getMms());
        Assert.assertTrue(pnc.getVoice());
        Assert.assertFalse(pnc.getSms());
    }
}
