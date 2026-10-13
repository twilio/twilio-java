package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Test class for {@link OutboundCallPrice}.
 */
public class OutboundCallPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"base_price\": \"1.00\",\n" +
            "    \"current_price\": \"2.00\"\n" +
            "}";

        OutboundCallPrice ocp = fromJson(json, OutboundCallPrice.class);
        Assert.assertEquals(1.00, ocp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, ocp.getCurrentPrice(), 0.00);
        Assert.assertEquals(new BigDecimal("1.00"), ocp.getBasePriceDecimal());
        Assert.assertEquals(new BigDecimal("2.00"), ocp.getCurrentPriceDecimal());
    }
}
