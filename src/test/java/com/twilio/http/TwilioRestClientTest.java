package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.rest.Domains;
import com.twilio.rest.api.v2010.account.Message;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
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
    public void testFetchRedirect() {
        new Expectations() {{
            final Request request = new Request(HttpMethod.GET, Domains.API.toString(),
                "/2010-04-01/Accounts/AC123/Messages/MM123.json");

            twilioRestClient.request(request);
            times = 1;
            result = new Response("{\"redirect_to\": \"somewhere\"}", 307);
        }};

        final Message response = Message.fetcher("AC123", "MM123").fetch();
        assertNotNull(response);
    }
}
