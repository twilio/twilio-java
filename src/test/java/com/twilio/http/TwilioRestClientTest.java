package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.rest.Domains;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TwilioRestClientTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
    }

    @Test
    @Ignore
    public void testRequest() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                "/2010-04-01/Accounts/" + "AC123" + "/Messages/" + "MM123" + ".json"
        );

        Response resp = twilioRestClient.request(request);
        assertNotNull(resp);
    }
}
