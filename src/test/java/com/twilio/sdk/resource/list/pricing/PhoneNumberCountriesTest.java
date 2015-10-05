package com.twilio.sdk.resource.list.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.pricing.PhoneNumberCountry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneNumberCountriesTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/phone_number_countries.json");
    }

    @Test
    public void testGetPhoneNumberCountries() throws Exception {
        setExpectedServerReturnCode(200);

        PhoneNumberCountryList phoneNumberCountries = pricingClient.getPhoneNumberCountries();
        assertNotNull(phoneNumberCountries);

        List<PhoneNumberCountry> dataList = phoneNumberCountries.getPageData();
        assertEquals(3, dataList.size());

        PhoneNumberCountry country = dataList.get(0);
        assertEquals("AC", country.getIsoCountry());
        assertEquals("Ascension", country.getCountry());
    }
}
