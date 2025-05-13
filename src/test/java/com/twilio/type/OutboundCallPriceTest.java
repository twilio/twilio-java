package com.twilio.type;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link OutboundCallPrice}.
 */
public class OutboundCallPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"base_price\": 1.00,\n" +
            "    \"current_price\": 2.00\n" +
            "}";

        OutboundCallPrice ocp = fromJson(json, OutboundCallPrice.class);
        assertEquals(1.00, ocp.getBasePrice(), 0.00);
        assertEquals(2.00, ocp.getCurrentPrice(), 0.00);
    }
}
