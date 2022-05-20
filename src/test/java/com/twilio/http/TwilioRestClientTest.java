package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.rest.Domains;
import mockit.Injectable;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class TwilioRestClientTest {
    @Injectable
    private TwilioRestClient twilioRestClient;

    @Injectable
    private TwilioRestClient twilioRestClientExtension;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
        twilioRestClientExtension = new TwilioRestClient.Builder("AC123", "AUTH TOKEN")
                .userAgentExtensions(Arrays.asList("Hello1", "Hello2"))
                .build();
    }

    @Test
    public void testRequest() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                "/2010-04-01/Accounts/" + "AC123" + "/Messages/" + "MM123" + ".json"
        );
        Response resp = twilioRestClient.request(request);
        assertNotNull(resp);
    }

    @Test
    public void testRequestWithExtention() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                "/2010-04-01/Accounts/" + "AC123" + "/Messages/" + "MM123" + ".json"
        );
        Response resp = twilioRestClientExtension.request(request);
        assertNotNull(resp);
    }

    @Test
    public void testRequestWithExtentionEmpty() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                "/2010-04-01/Accounts/" + "AC123" + "/Messages/" + "MM123" + ".json"
        );
        twilioRestClientExtension = new TwilioRestClient.Builder("AC123", "AUTH TOKEN")
                .userAgentExtensions(Arrays.asList())
                .build();
        Response resp = twilioRestClientExtension.request(request);
        assertNotNull(resp);
    }
}
