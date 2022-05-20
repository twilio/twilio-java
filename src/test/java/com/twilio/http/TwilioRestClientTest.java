package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.rest.Domains;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwilioRestClientTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Injectable
    private TwilioRestClient twilioRestClientExtension;

    private List<String> userAgentStringExtensions;

    @Before
    public void setUp() throws Exception {
        Twilio.init("AC123", "AUTH TOKEN");
        userAgentStringExtensions = Arrays.asList("ce-appointment-reminders/1.0.0", "code-exchange");
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
        twilioRestClientExtension = new TwilioRestClient.Builder("AC123", "AUTH TOKEN")
                .userAgentExtensions(userAgentStringExtensions)
                .build();
        twilioRestClientExtension.request(request);
        assertEquals(request.getUserAgentExtensions(), userAgentStringExtensions);
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
        twilioRestClientExtension.request(request);
        assertEquals(request.getUserAgentExtensions(), null);
    }

    @Test
    public void testRequestWithExtentionNull() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                "/2010-04-01/Accounts/" + "AC123" + "/Messages/" + "MM123" + ".json"
        );
        twilioRestClientExtension = new TwilioRestClient.Builder("AC123", "AUTH TOKEN")
                .userAgentExtensions(null)
                .build();
        twilioRestClientExtension.request(request);
        assertEquals(request.getUserAgentExtensions(), null);
    }
}
