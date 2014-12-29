package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VoiceNumberTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/voice_number.json");
    }

    @Test
    public void testGetVoiceNumber() throws Exception {
        setExpectedServerReturnCode(200);
        VoiceNumber number = pricingClient.getVoiceNumber("+14089673429");

        assertNotNull(number);
        assertEquals("+14089673429", number.getNumber());
        assertEquals("United States", number.getCountry());
        assertEquals("US", number.getIsoCountry());
        assertEquals("usd", number.getPriceUnit());

        VoiceNumber.InboundCallPrice inboundCallPrice = number.getInboundCallPrice();
        assertEquals(NumberType.LOCAL, inboundCallPrice.getNumberType());
        assertEquals(new BigDecimal("0.0075"), inboundCallPrice.getCallBasePrice());
        assertEquals(new BigDecimal("0.0070"), inboundCallPrice.getCallCurrentPrice());

        VoiceNumber.OutboundCallPrice outboundCallPrice = number.getOutboundCallPrice();
        assertEquals(new BigDecimal("0.015"), outboundCallPrice.getCallBasePrice());
        assertEquals(new BigDecimal("0.015"), outboundCallPrice.getCallCurrentPrice());
    }
}
