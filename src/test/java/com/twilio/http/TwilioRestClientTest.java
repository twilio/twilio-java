package com.twilio.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.twilio.Twilio;
import com.twilio.auth_strategy.BasicAuthStrategy;
import com.twilio.auth_strategy.NoAuthStrategy;
import com.twilio.rest.Domains;
import com.twilio.type.RegionEndpoints;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
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

    @Test
    public void testRequestWithNoAuthStrategy() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        request.setAuth(NoAuthStrategy.getInstance());
        twilioRestClientExtension = new TwilioRestClient.Builder(USER_NAME, TOKEN)
                .userAgentExtensions(Collections.emptyList())
                .httpClient(httpClient)
                .build();
        twilioRestClientExtension.request(request);
        assertNull(twilioRestClientExtension.getAuthStrategy());
        // AuthStrategy of Request not changing by TwilioRestClient
        assertEquals(NoAuthStrategy.getInstance(), request.getAuthStrategy());
    }

    @Test
    public void testRequestWithNoAuthStrategyWithAuthStrategy() {
        Request request = new Request(
                HttpMethod.GET,
                Domains.API.toString(),
                URI
        );
        request.setAuth(NoAuthStrategy.getInstance());
        twilioRestClientExtension = new TwilioRestClient.Builder(new BasicAuthStrategy(USER_NAME, TOKEN))
                .userAgentExtensions(Collections.emptyList())
                .httpClient(httpClient)
                .build();
        twilioRestClientExtension.request(request);
        assertNotNull(twilioRestClientExtension.getAuthStrategy());
        // AuthStrategy of Request not changing by TwilioRestClient
        assertEquals(NoAuthStrategy.getInstance(), request.getAuthStrategy());
    }

    @Test
    public void testEdgeIsSetFromRegionMap() {
        Map<String, String> regionMap = RegionEndpoints.getRegions();
        for( String key: regionMap.keySet() ) {
            TwilioRestClient client = new TwilioRestClient.Builder(USER_NAME, TOKEN).region(key).build();
            assertEquals(regionMap.get(key), client.getEdge());
        }
    }

    @Test
    public void testEdge() {
        TwilioRestClient client = new TwilioRestClient.Builder(USER_NAME, TOKEN).region("us1").edge("someEdge").build();
        assertEquals("someEdge", client.getEdge());
    }
}
