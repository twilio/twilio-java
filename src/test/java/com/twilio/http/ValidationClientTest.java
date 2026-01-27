package com.twilio.http;

import com.twilio.constant.EnumConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import java.io.IOException;

import com.twilio.exception.ApiException;

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

    @Test
    public void testHttpPatch() throws Exception {
        exerciseHttpMethod(HttpMethod.PATCH);
        testContentType(HttpMethod.PATCH);
    }

    @Test
    public void testHttpDelete() throws Exception {
        exerciseHttpMethod(HttpMethod.DELETE);
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

    @Test
    public void testFormUrlEncodedMultipleParameters() throws Exception {
        // Set up the test environment
        final KeyPair keyPair = generateKeyPair();
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("success"));

        final String path = "/resource";
        final HttpUrl url = server.url(path);
        final ValidationClient client = new ValidationClient(
            "AC123456789",
            "CR987654321",
            "signing-key-123",
            keyPair.getPrivate()
        );

        // Create the request with form parameters
        final Request request = new Request(HttpMethod.POST, url.url().toString());
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);

        // Add multiple form parameters, including one with multiple values
        Map<String, List<String>> postParams = new HashMap<>();
        postParams.put("name", Arrays.asList("John Doe"));
        postParams.put("email", Arrays.asList("john.doe@example.com"));
        postParams.put("phone", Arrays.asList("+12125551234"));
        postParams.put("tags", Arrays.asList("customer", "premium", "enterprise"));  // Multiple values
        postParams.put("status", Arrays.asList("active"));

        // Add the parameters to the request
        for (Map.Entry<String, List<String>> entry : postParams.entrySet()) {
            for (String value : entry.getValue()) {
                request.addPostParam(entry.getKey(), value);
            }
        }

        // Make the request
        final Response response = client.makeRequest(request);

        // Verify response
        assertEquals(200, response.getStatusCode());
        assertEquals("success", response.getContent());
    }

    @Test
    public void testRequestWithMultipleHeaders() throws Exception {
        // Set up the test environment
        final KeyPair keyPair = generateKeyPair();
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("success"));

        final String path = "/resource";
        final HttpUrl url = server.url(path);
        final ValidationClient client = new ValidationClient(
            "AC123456789",
            "CR987654321",
            "signing-key-123",
            keyPair.getPrivate()
        );

        // Create the request with custom headers
        final Request request = new Request(HttpMethod.GET, url.url().toString());

        // Add multiple custom headers
        request.addHeaderParam("X-Custom-Header", "CustomValue");
        request.addHeaderParam("Accept-Language", "en-US");
        request.addHeaderParam("Cache-Control", "no-cache");

        // Add a header with multiple values
        request.addHeaderParam("Accept", "application/json");
        request.addHeaderParam("Accept", "application/xml");

        // Add header with special characters
        request.addHeaderParam("X-Special-Chars", "value with spaces, commas; and special chars");

        // Make the request
        final Response response = client.makeRequest(request);

        // Verify response
        assertEquals(200, response.getStatusCode());
        assertEquals("success", response.getContent());
    }

    @Test(expected = ApiException.class)
    public void testIOExceptionHandling() throws Exception {
        // Set up a key pair for validation
        final KeyPair keyPair = generateKeyPair();

        // Create a server that will immediately close the connection
        final MockWebServer server = new MockWebServer();

        // Get the URL before shutting down the server (mockwebserver 5.x behavior)
        final HttpUrl url = server.url("/resource");

        // Force the server to shut down before making the request
        server.shutdown();

        // Create a request to the closed server which will cause IOException
        final ValidationClient client = new ValidationClient(
            "AC123456789",
            "CR987654321",
            "signing-key-123",
            keyPair.getPrivate()
        );
        final Request request = new Request(HttpMethod.GET, url.url().toString());

        // This should throw an ApiException wrapping the IOException
        client.makeRequest(request);
    }
}
