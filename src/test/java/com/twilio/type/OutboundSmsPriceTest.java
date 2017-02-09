package com.twilio.type;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for {@link OutboundSmsPrice}.
 */
public class OutboundSmsPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"mcc\": \"mcc\",\n" +
            "    \"mnc\": \"mnc\",\n" +
            "    \"carrier\": \"att\",\n" +
            "    \"prices\": [{\n" +
            "        \"type\": \"local\",\n" +
            "        \"base_price\": 1.00,\n" +
            "        \"current_price\": 2.00\n" +
            "    }]\n" +
            "}";

        OutboundSmsPrice osp = fromJson(json, OutboundSmsPrice.class);
        Assert.assertEquals("mcc", osp.getMcc());
        Assert.assertEquals("mnc", osp.getMnc());
        Assert.assertEquals("att", osp.getCarrier());
        Assert.assertEquals(Lists.newArrayList(
            new InboundSmsPrice(1.00, 2.00, InboundSmsPrice.Type.LOCAL)
        ), osp.getPrices());
    }
}
