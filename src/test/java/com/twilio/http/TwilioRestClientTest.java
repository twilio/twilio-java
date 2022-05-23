package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.rest.Domains;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TwilioRestClientTest {
    @Mocked
    private TwilioRestClient twilioRestClient;

    @Injectable
    private TwilioRestClient twilioRestClientExtension;

    private List<String> userAgentStringExtensions = Arrays.asList("ce-appointment-reminders/1.0.0", "code-exchange");

    private static final String USER_NAME = "AC123";

    private static final String TOKEN = "AUTH TOKEN";

    private static final String URI = "/2010-04-01/Accounts/AC123/Messages/MM123.json";

    @Before
    public void setUp() throws Exception {
        Twilio.init(USER_NAME, TOKEN);
    }

    @Test
    public void testRequest() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );

        Response resp = twilioRestClient.request(request);
        assertNotNull(resp);
    }

    @Test
    public void testRequestWithExtension() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        twilioRestClientExtension = new TwilioRestClient.Builder(USER_NAME, TOKEN)
                .userAgentExtensions(userAgentStringExtensions)
                .build();
        twilioRestClientExtension.request(request);
        assertEquals(userAgentStringExtensions, request.getUserAgentExtensions());
    }

    @Test
    public void testRequestWithExtensionEmpty() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        twilioRestClientExtension = new TwilioRestClient.Builder(USER_NAME, TOKEN)
                .userAgentExtensions(Collections.emptyList())
                .build();
        twilioRestClientExtension.request(request);
        assertNull(request.getUserAgentExtensions());
    }

    @Test
    public void testRequestWithExtensionNull() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        twilioRestClientExtension = new TwilioRestClient.Builder(USER_NAME, TOKEN)
                .userAgentExtensions(null)
                .build();
        twilioRestClientExtension.request(request);
        assertNull(request.getUserAgentExtensions());
    }
}
