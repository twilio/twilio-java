package com.twilio.http;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twilio.rest.Domains;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TwilioRestClientTest {
    private TwilioRestClient twilioRestClient;
    @Mock
    private HttpClient httpClient;

    private TwilioRestClient twilioRestClientExtension;

    private List<String> userAgentStringExtensions = Arrays.asList("ce-appointment-reminders/1.0.0", "code-exchange");

    private static final String USER_NAME = "AC123";

    private static final String TOKEN = "AUTH TOKEN";

    private static final String URI = "/2010-04-01/Accounts/AC123/Messages/MM123.json";

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        twilioRestClient = new TwilioRestClient(new TwilioRestClient.Builder("AC123", "AUTH TOKEN").httpClient(httpClient));
    }

    @Test
    public void testRequest() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        when(httpClient.reliableRequest(request)).thenReturn(new Response("", 200));

        Response resp = twilioRestClient.request(request);
        assertNotNull(resp);
    }

    @Test
    public void testRequestWithCustomObjectMapper() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        TwilioRestClient client = new TwilioRestClient.Builder(USER_NAME, TOKEN)
                .objectMapper(new ObjectMapper().registerModule(new JavaTimeModule()))
                .httpClient(httpClient)
                .build();

        when(httpClient.reliableRequest(request)).thenReturn(new Response("", 200));

        Response resp = client.request(request);
        assertNotNull(resp);
    }

    @Test
    public void testUsesSingletonDefaultObjectMapper() {
        TwilioRestClient client1 = new TwilioRestClient.Builder(USER_NAME, TOKEN)
            .build();
        TwilioRestClient client2 = new TwilioRestClient.Builder(USER_NAME, TOKEN)
            .build();

        assertTrue(client1.getObjectMapper() == client2.getObjectMapper());
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
                .httpClient(httpClient)
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
                .httpClient(httpClient)
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
                .httpClient(httpClient)
                .build();
        twilioRestClientExtension.request(request);
        assertNull(request.getUserAgentExtensions());
    }
}
