package com.twilio.http;

import com.twilio.constant.EnumConstants;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidationClientTest {
    @Test
    public void testHttpGet() throws Exception {
        exerciseHttpMethod(HttpMethod.GET);
    }

    @Test
    public void testHttpPost() throws Exception {
        exerciseHttpMethod(HttpMethod.POST);
        testContentType(HttpMethod.POST);
    }

    @Test
    public void testHttpPut() throws Exception {
        exerciseHttpMethod(HttpMethod.PUT);
    }

    private void exerciseHttpMethod(final HttpMethod httpMethod) throws Exception {
        final KeyPair keyPair = generateKeyPair();
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("hello, world!"));
        final String path = "/example123";
        final HttpUrl url = server.url(path);
        final ValidationClient client = new ValidationClient("dummy-sid1", "dummy-sid2", "dummy-signing-key", keyPair.getPrivate());
        final Request request = new Request(httpMethod, url.url().toString());
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        final Response response = client.makeRequest(request);
        assertEquals(200, response.getStatusCode());
        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals(httpMethod.name(), recordedRequest.getMethod());
        final String validationHeaderValue = recordedRequest.getHeader("Twilio-Client-Validation");
        assertNotNull(validationHeaderValue);
        assertTrue(validationHeaderValue.length() > 0);
    }
    private void testContentType(final HttpMethod httpMethod) throws Exception {
        final KeyPair keyPair = generateKeyPair();
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{\n" +
                "    \"key1\": \"value1\",\n" +
                "    \"key2\": \"value2\"\n" +
                "}"));
        final String path = "/example123";
        final HttpUrl url = server.url(path);
        final ValidationClient client = new ValidationClient("dummy-sid1", "dummy-sid2", "dummy-signing-key", keyPair.getPrivate());
        final Request request = new Request(httpMethod, url.url().toString());
        request.setContentType(EnumConstants.ContentType.JSON);
        String body = "{\"from\":\"+12345\",\"body\":\"message body\",\"messages\":[{\"to\":\"+12345\"}]}";
        request.setBody(body);
        final Response response = client.makeRequest(request);
        assertEquals(200, response.getStatusCode());
        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals(httpMethod.name(), recordedRequest.getMethod());
        assertEquals(EnumConstants.ContentType.JSON.getValue(), recordedRequest.getHeader("Content-Type"));
        final String validationHeaderValue = recordedRequest.getHeader("Twilio-Client-Validation");
        assertNotNull(validationHeaderValue);
        assertTrue(validationHeaderValue.length() > 0);
    }

    private static KeyPair generateKeyPair() throws Exception {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
}
