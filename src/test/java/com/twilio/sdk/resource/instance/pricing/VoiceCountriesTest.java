package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.list.pricing.VoiceCountryList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VoiceCountriesTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/voice_countries.json");
    }

    @Test
    public void testGetVoiceCountries() throws Exception {
        setExpectedServerReturnCode(200);
        VoiceCountryList countryList = pricingClient.getVoiceCountries();

        assertNotNull(countryList);
        List<VoiceCountry> voiceCountryList = countryList.getPageData();

        assertEquals(3, voiceCountryList.size());
        VoiceCountry country = voiceCountryList.get(0);
        assertEquals("AC", country.getIsoCountry());
        assertEquals("Ascension", country.getCountry());
    }
}
