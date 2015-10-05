package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.pricing.MessagingCountry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessagingCountriesTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/messaging_countries.json");
    }

    @Test
    public void testGetMessagingCountries() throws Exception {
        setExpectedServerReturnCode(200);

        MessagingCountryList list = pricingClient.getMessagingCountries();
        assertNotNull(list);

        List<MessagingCountry> dataList = list.getPageData();
        assertEquals(3, dataList.size());

        MessagingCountry country = dataList.get(0);
        assertEquals("AC", country.getIsoCountry());
        assertEquals("Ascension", country.getCountry());
    }
}
