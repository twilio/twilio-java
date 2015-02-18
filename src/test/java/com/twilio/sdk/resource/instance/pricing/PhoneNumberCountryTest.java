package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneNumberCountryTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/phone_number_country.json");
    }

    @Test
    public void testGetPhoneNumberCountry() throws Exception {
        setExpectedServerReturnCode(200);

        PhoneNumberCountry country = pricingClient.getPhoneNumberCountry("EE");
        assertNotNull(country);

        assertEquals("Estonia", country.getCountry());
        assertEquals("EE", country.getIsoCountry());
        assertEquals("USD", country.getPriceUnit());

        List<PhoneNumberCountry.NumberPrice> numberPrices = country.getPhoneNumberPrices();
        assertEquals(2, numberPrices.size());

        PhoneNumberCountry.NumberPrice price = numberPrices.get(0);
        assertEquals(NumberType.MOBILE, price.getNumberType());
        assertEquals(new BigDecimal("3.00"), price.getBasePrice());
        assertEquals(new BigDecimal("3.00"), price.getCurrentPrice());
    }
}
