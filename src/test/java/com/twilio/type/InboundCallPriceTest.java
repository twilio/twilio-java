package com.twilio.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

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
        assertEquals(1.00, icp.getBasePrice(), 0.00);
        assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        assertEquals(InboundCallPrice.Type.MOBILE, icp.getType());
    }

    @Test
    public void testFromJsonTollFree() throws IOException {
        String json = "{\n" +
                "    \"base_price\": 1.00,\n" +
                "    \"current_price\": 2.00,\n" +
                "    \"type\": \"toll free\"\n" +
                "}";

        InboundCallPrice icp = fromJson(json, InboundCallPrice.class);
        assertEquals(1.00, icp.getBasePrice(), 0.00);
        assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        assertEquals(InboundCallPrice.Type.TOLLFREE, icp.getType());
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
        assertEquals(1.00, icp.getBasePrice(), 0.00);
        assertEquals(2.00, icp.getCurrentPrice(), 0.00);
        assertEquals(InboundCallPrice.Type.TOLLFREE, icp.getType());
    }
}
