package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for {@link InboundCallPrice}.
 */
public class InboundCallPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"base_price\": 1.00,\n" +
            "    \"current_price\": 2.00,\n" +
            "    \"type\": \"mobile\"\n" +
            "}";

        InboundCallPrice icp = fromJson(json, InboundCallPrice.class);
        Assert.assertEquals(1.00, icp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        Assert.assertEquals(InboundCallPrice.Type.MOBILE, icp.getType());
    }

    @Test
    public void testFromJsonTollFree() throws IOException {
        String json = "{\n" +
                "    \"base_price\": 1.00,\n" +
                "    \"current_price\": 2.00,\n" +
                "    \"type\": \"toll free\"\n" +
                "}";

        InboundCallPrice icp = fromJson(json, InboundCallPrice.class);
        Assert.assertEquals(1.00, icp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        Assert.assertEquals(InboundCallPrice.Type.TOLLFREE, icp.getType());
    }

    @Test
    public void testFromJsonExtraField() throws IOException {
        String json = "{\n" +
            "    \"base_price\": 1.00,\n" +
            "    \"current_price\": 2.00,\n" +
            "    \"type\": \"toll free\",\n" +
            "    \"foo\": \"bar\"\n" +
            "}";

        InboundCallPrice icp = fromJson(json, InboundCallPrice.class);
        Assert.assertEquals(1.00, icp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        Assert.assertEquals(InboundCallPrice.Type.TOLLFREE, icp.getType());
    }
}
