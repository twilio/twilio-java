package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for {@link InboundSmsPrice}.
 */
public class InboundSmsPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"base_price\": 1.00,\n" +
            "    \"current_price\": 2.00,\n" +
            "    \"number_type\": \"mobile\"\n" +
            "}";

        InboundSmsPrice icp = fromJson(json, InboundSmsPrice.class);
        Assert.assertEquals(1.00, icp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        Assert.assertEquals(InboundSmsPrice.Type.MOBILE, icp.getType());
    }

    @Test
    public void testFromJsonTollFree() throws IOException {
        String json = "{\n" +
                "    \"base_price\": 1.00,\n" +
                "    \"current_price\": 2.00,\n" +
                "    \"number_type\": \"toll free\"\n" +
                "}";

        InboundSmsPrice icp = fromJson(json, InboundSmsPrice.class);
        Assert.assertEquals(1.00, icp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        Assert.assertEquals(InboundSmsPrice.Type.TOLLFREE, icp.getType());
    }
}
