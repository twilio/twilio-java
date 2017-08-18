package com.twilio.http;

import com.twilio.exception.ApiConnectionException;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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

    @Tested
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

    private void setup(
        final int statusCode,
        final String content,
        final HttpMethod method,
        final Boolean requiresAuthentication
    ) throws IOException {
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockBuilder.setDefaultHeaders((Collection<Header>) any);
            result = mockBuilder;

            mockBuilder.build();
            result = mockClient;

            mockRequest.getMethod();
            result = method;

            mockRequest.constructURL();
            result = mockUrl;

            mockRequest.requiresAuthentication();
            result = requiresAuthentication;

            if (requiresAuthentication) {
                mockRequest.getAuthString();
                result = "foo:bar";
            }

            if (method == HttpMethod.POST) {
                mockRequest.getPostParams();
            }

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

            mockResponse.getEntity();
            result = null;
        }};
    }

    @Test
    public void testGet() throws IOException {
        setup(200, "frobozz", HttpMethod.GET, false);

        client = new NetworkHttpClient(mockBuilder);
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

        client = new NetworkHttpClient(mockBuilder);
        client.makeRequest(mockRequest);
        fail("ApiConnectionException was expected");
    }

    @Test
    public void testPost() throws IOException {
        setup(201, "frobozz", HttpMethod.POST, false);

        client = new NetworkHttpClient(mockBuilder);
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 201);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test
    public void testReliableRequest() {
        final HttpClient httpClient = new NetworkHttpClient();
        Request request = new Request(HttpMethod.GET, "/uri");

        new NonStrictExpectations(httpClient) {{
            httpClient.makeRequest((Request) any);
            result = new Response("", TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT);
        }};

        httpClient.reliableRequest(request);
        assertNotNull(httpClient.getLastRequest());
        assertNotNull(httpClient.getLastResponse());
    }

    @Test
    public void testReliableRequestWithRetries() {
        final HttpClient httpClient = new NetworkHttpClient();
        Request request = new Request(HttpMethod.GET, "/uri");

        new NonStrictExpectations(httpClient) {{
            httpClient.makeRequest((Request) any);
            result = null;
            times = 3;
        }};

        httpClient.reliableRequest(request);
    }

    @Test
    public void testReliableRequestWithRetries100() throws InterruptedException {
        final HttpClient httpClient = new NetworkHttpClient();
        Request request = new Request(HttpMethod.GET, "/uri");

        new NonStrictExpectations(httpClient) {{
            httpClient.makeRequest((Request) any);
            result = new Response("", 500);
        }};

        httpClient.reliableRequest(request);
    }

    @Test
    public void testDelete() throws IOException {
        setup(204, "", HttpMethod.DELETE,false);

        client = new NetworkHttpClient(mockBuilder);
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 204);
        assertEquals(resp.getContent(), "");
    }

    @Test
    public void testAuthedGet() throws IOException {
        setup(200, "frobozz", HttpMethod.GET,true);

        client = new NetworkHttpClient(mockBuilder);
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 200);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test
    public void testErrorResponse() throws IOException {
        setup(404, "womp", HttpMethod.GET,false);

        client = new NetworkHttpClient(mockBuilder);
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 404);
        assertEquals(resp.getContent(), "womp");
    }
}
