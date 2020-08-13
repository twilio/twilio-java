package com.twilio.http;

import com.twilio.exception.ApiConnectionException;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class NetworkHttpClientTest {

    private NetworkHttpClient client;

    @Mocked
    private Request mockRequest;

    @Mocked
    private URL mockUrl;

    @Mocked
    private HttpClientBuilder mockBuilder;

    @Mocked
    private CloseableHttpClient mockClient;

    @Mocked
    private CloseableHttpResponse mockResponse;

    @Mocked
    private StatusLine mockStatusLine;

    @Mocked
    private HttpEntity mockEntity;

    @Before
    public void setUp() {
        new NonStrictExpectations() {{
            mockBuilder.setDefaultHeaders((Collection<Header>) any);
            result = mockBuilder;

            mockBuilder.build();
            result = mockClient;
        }};

        client = new NetworkHttpClient(mockBuilder);
    }

    private void setup(
        final int statusCode,
        final String content,
        final HttpMethod method,
        final Boolean requiresAuthentication
    ) throws IOException {
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new NonStrictExpectations() {{
            mockRequest.getMethod();
            result = method;

            mockRequest.constructURL();
            result = mockUrl;

            mockRequest.requiresAuthentication();
            result = requiresAuthentication;

            mockRequest.getAuthString();
            result = "foo:bar";

            mockRequest.getPostParams();

            mockClient.execute((HttpUriRequest) any);
            result = mockResponse;

            mockResponse.getEntity();
            result = mockEntity;

            mockEntity.isRepeatable();
            result = true;

            mockEntity.getContentLength();
            result = 1;

            mockEntity.getContent();
            result = stream;

            mockResponse.getStatusLine();
            result = mockStatusLine;

            mockStatusLine.getStatusCode();
            result = statusCode;
        }};
    }

    @Test
    public void testGet() throws IOException {
        setup(200, "frobozz", HttpMethod.GET, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 200);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test(expected = ApiConnectionException.class)
    public void testMakeRequestIOException() throws IOException {
        new NonStrictExpectations() {{
            mockBuilder.setDefaultHeaders((Collection<Header>) any);
            result = mockBuilder;

            mockBuilder.build();
            result = mockClient;

            mockRequest.getMethod();
            result = HttpMethod.GET;

            mockRequest.constructURL();
            result = mockUrl;

            mockRequest.requiresAuthentication();
            result = true;

            mockRequest.getAuthString();
            result = "foo:bar";

            mockClient.execute((HttpUriRequest) any);
            result = new ApiConnectionException("foo");
        }};

        client.makeRequest(mockRequest);
        fail("ApiConnectionException was expected");
    }

    @Test
    public void testPost() throws IOException {
        setup(201, "frobozz", HttpMethod.POST, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 201);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test
    public void testReliableRequest() {
        Request request = new Request(HttpMethod.GET, "/uri");

        new NonStrictExpectations(client) {{
            client.makeRequest((Request) any);
            result = new Response("", TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT);
        }};

        client.reliableRequest(request);
        assertNotNull(client.getLastRequest());
        assertNotNull(client.getLastResponse());
    }

    @Test
    public void testReliableRequestWithRetries() {
        Request request = new Request(HttpMethod.GET, "/uri");

        new NonStrictExpectations(client) {{
            client.makeRequest((Request) any);
            result = null;
            times = 3;
        }};

        client.reliableRequest(request);
    }

    @Test
    public void testReliableRequestWithRetries100() {
        Request request = new Request(HttpMethod.GET, "/uri");

        new NonStrictExpectations(client) {{
            client.makeRequest((Request) any);
            result = new Response("", 500);
        }};

        client.reliableRequest(request);
    }

    @Test
    public void testDelete() throws IOException {
        setup(204, "", HttpMethod.DELETE, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 204);
        assertEquals(resp.getContent(), "");
    }

    @Test
    public void testAuthedGet() throws IOException {
        setup(200, "frobozz", HttpMethod.GET, true);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 200);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test
    public void testErrorResponse() throws IOException {
        setup(404, "womp", HttpMethod.GET, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 404);
        assertEquals(resp.getContent(), "womp");
    }
}
