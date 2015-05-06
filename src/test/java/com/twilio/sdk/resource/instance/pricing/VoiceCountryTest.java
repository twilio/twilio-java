package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VoiceCountryTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/voice_country.json");
    }

    @Test
    public void testGetVoiceCountry() throws Exception {
        setExpectedServerReturnCode(200);
        VoiceCountry country = pricingClient.getVoiceCountry("EE");
        assertNotNull(country);
        assertEquals("Estonia", country.getCountry());
        assertEquals("EE", country.getIsoCountry());
        assertEquals("USD", country.getPriceUnit());

        List<VoiceCountry.OutboundPrefixPrice> outboundPrefixPrices = country.getOutboundPrefixPrices();
        assertEquals(3, outboundPrefixPrices.size());

        VoiceCountry.OutboundPrefixPrice outboundPrefixPrice = outboundPrefixPrices.get(0);
        assertEquals("Programmable Outbound Minute - Estonia", outboundPrefixPrice.getFriendlyName());
        assertEquals("372", outboundPrefixPrice.getPrefixes().get(0));
        assertEquals(new BigDecimal("0.033"), outboundPrefixPrice.getBasePrice());
        assertEquals(new BigDecimal("0.030"), outboundPrefixPrice.getCurrentPrice());

        List<VoiceCountry.InboundCallPrice> inboundCallPrices = country.getInboundCallPrices();
        assertEquals(2, inboundCallPrices.size());

        VoiceCountry.InboundCallPrice inboundCallPrice = inboundCallPrices.get(0);
        assertEquals(NumberType.MOBILE, inboundCallPrice.getNumberType());
        assertEquals(new BigDecimal("0.0075"), inboundCallPrice.getBasePrice());
        assertEquals(new BigDecimal("0.0070"), inboundCallPrice.getCurrentPrice());
    }
}
