package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for {@link IceServer}.
 */
public class IceServerTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"credential\": \"apn\",\n" +
            "    \"username\": \"twilio\",\n" +
            "    \"url\": \"https://www.twilio.ca\"\n" +
            "}";

        IceServer is = fromJson(json, IceServer.class);
        Assert.assertEquals("https://www.twilio.ca", is.getUrl().toString());
        Assert.assertEquals("apn", is.getCredential());
        Assert.assertEquals("twilio", is.getUsername());
    }
}
