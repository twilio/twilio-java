package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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
        assertEquals(3, inboundPrices.size());

        MessagingCountry.MessagingPrice inboundSmsPrice = inboundPrices.get(0);
        assertEquals(NumberType.MOBILE, inboundSmsPrice.getNumberType());
        assertEquals(new BigDecimal("0.0075"), inboundSmsPrice.getBasePrice());
        assertEquals(new BigDecimal("0.0070"), inboundSmsPrice.getCurrentPrice());

        MessagingCountry.MessagingPrice inboundSmsPriceNational = inboundPrices.get(1);
        assertEquals(NumberType.NATIONAL, inboundSmsPriceNational.getNumberType());
        assertEquals(new BigDecimal("0.0055"), inboundSmsPriceNational.getBasePrice());
        assertEquals(new BigDecimal("0.0050"), inboundSmsPriceNational.getCurrentPrice());

        MessagingCountry.MessagingPrice inboundSmsPriceTollFree = inboundPrices.get(2);
        assertEquals(NumberType.TOLL_FREE, inboundSmsPriceTollFree.getNumberType());
        assertEquals(new BigDecimal("0.0035"), inboundSmsPriceTollFree.getBasePrice());
        assertEquals(new BigDecimal("0.0030"), inboundSmsPriceTollFree.getCurrentPrice());
    }
}

