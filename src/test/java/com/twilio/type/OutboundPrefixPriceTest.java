package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Test class for {@link OutboundPrefixPrice}.
 */
public class OutboundPrefixPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"prefixes\": [\n" +
            "        \"abc\",\n" +
            "        \"xyz\"\n" +
            "    ],\n" +
            "    \"friendly_name\": \"name\",\n" +
            "    \"base_price\": \"1.00\",\n" +
            "    \"current_price\": \"2.00\"\n" +
            "}";

        OutboundPrefixPrice opp = fromJson(json, OutboundPrefixPrice.class);
        Assert.assertEquals(Arrays.asList("abc", "xyz"), opp.getPrefixes());
        Assert.assertEquals("name", opp.getFriendlyName());
        Assert.assertEquals(1.00, opp.getBasePrice(), 0.00);
        Assert.assertEquals(2.00, opp.getCurrentPrice(), 0.00);
        Assert.assertEquals(new BigDecimal("1.00"), opp.getBasePriceDecimal());
        Assert.assertEquals(new BigDecimal("2.00"), opp.getCurrentPriceDecimal());

    }
}
