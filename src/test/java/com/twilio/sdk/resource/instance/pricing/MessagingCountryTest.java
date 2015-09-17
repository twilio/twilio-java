package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessagingCountryTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/messaging_country.json");
    }

    @Test
    public void testGetMessagingCountry() throws Exception {
        setExpectedServerReturnCode(200);

        MessagingCountry country = pricingClient.getMessagingCountry("EE");
        assertNotNull(country);

        assertEquals("Estonia", country.getCountry());
        assertEquals("EE", country.getIsoCountry());
        assertEquals("USD", country.getPriceUnit());

        List<MessagingCountry.OutboundSmsPrice> outboundPrices = country.getOutboundSmsPrices();
        assertEquals(1, outboundPrices.size());

        List<MessagingCountry.MessagingPrice> inboundPrices = country.getInboundSmsPrices();
        assertEquals(2, inboundPrices.size());
    }
}

