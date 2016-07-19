package com.twilio.sdk.client;

import com.twilio.sdk.http.HttpClient;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.NetworkHttpClient;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.http.TwilioRestClient;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwilioRestClientTest {

    @Test
    public void testGetObjectMapper() {
        TwilioRestClient twilioRestClient = new TwilioRestClient("AC123", "AUTH TOKEN");
        assertNotNull(twilioRestClient.getObjectMapper());
    }

    @Test
    public void testRequest(@Mocked final HttpClient httpClient) {
        TwilioRestClient twilioRestClient = new TwilioRestClient("AC123", "AUTH TOKEN");
        final Request request = new Request(HttpMethod.GET, "http://www.example.com", "AC123");

        new NonStrictExpectations() {{
            httpClient.reliableRequest(request);
            result = new Response("", TwilioRestClient.HTTP_STATUS_CODE_OK);
        }};

        Response response = twilioRestClient.request(request);

        assertNotNull(response);
    }

    @Test
    public void testSetHttpClient() {
        TwilioRestClient twilioRestClient = new TwilioRestClient("AC123", "AUTH TOKEN");
        HttpClient httpClient = new NetworkHttpClient();
        twilioRestClient.setHttpClient(httpClient);
        assertEquals(httpClient, twilioRestClient.getHttpClient());
    }

}
