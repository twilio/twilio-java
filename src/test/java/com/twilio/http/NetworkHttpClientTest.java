package com.twilio.http;

import com.twilio.exception.ApiConnectionException;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NetworkHttpClientTest {

    @Tested
    private NetworkHttpClient tested;

    @Mocked
    private Request mockRequest;

    @Mocked
    private URL mockUrl;

    @Mocked
    private HttpURLConnection mockConn;

    @Mocked
    private OutputStream mockOutputStream;

    @Mocked
    private OutputStreamWriter mockWriter;

    @Test
    public void testGet() throws IOException {
        String content = "frobozz";
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL();
            result = mockUrl;
            mockRequest.getMethod();
            result = HttpMethod.GET;
            mockUrl.openConnection();
            result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("GET");

            mockRequest.requiresAuthentication();
            result = false;
            mockConn.connect();

            mockConn.getResponseCode();
            result = 200;
            mockConn.getErrorStream();
            result = null;
            mockConn.getInputStream();
            result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 200);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test(expected = ApiConnectionException.class)
    public void testMakeRequestIOException() throws IOException {
        NetworkHttpClient client = new NetworkHttpClient();

        new NonStrictExpectations() {{
            mockUrl.openConnection();
            result = new IOException();
        }};

        client.makeRequest(new Request(HttpMethod.GET, "http://www.example.com"));
        fail("ApiConnectionException was expected");
    }

    @Test
    public void testPost() throws IOException {
        String content = "frobozz";
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL();
            result = mockUrl;
            mockRequest.getMethod();
            result = HttpMethod.POST;
            mockUrl.openConnection();
            result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("POST");

            mockRequest.requiresAuthentication();
            result = false;
            mockConn.setDoOutput(true);
            mockConn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            mockConn.connect();

            mockRequest.encodeFormBody();
            result = "foo=bar&baz=quux";
            mockConn.getOutputStream();
            result = mockOutputStream;
            new OutputStreamWriter(mockOutputStream);
            result = mockWriter;
            mockWriter.write("foo=bar&baz=quux");
            mockWriter.close();

            mockConn.getResponseCode();
            result = 201;
            mockConn.getErrorStream();
            result = null;
            mockConn.getInputStream();
            result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
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
        String content = "";
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL();
            result = mockUrl;
            mockRequest.getMethod();
            result = HttpMethod.DELETE;
            mockUrl.openConnection();
            result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("DELETE");

            mockRequest.requiresAuthentication();
            result = false;
            mockConn.connect();

            mockConn.getResponseCode();
            result = 204;
            mockConn.getErrorStream();
            result = null;
            mockConn.getInputStream();
            result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 204);
        assertEquals(resp.getContent(), "");
    }

    @Test
    public void testAuthedGet() throws IOException {
        String content = "frobozz";
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL();
            result = mockUrl;
            mockRequest.getMethod();
            result = HttpMethod.GET;
            mockUrl.openConnection();
            result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("GET");

            mockRequest.requiresAuthentication();
            result = true;
            mockRequest.getUsername();
            result = "foo";
            mockRequest.getPassword();
            result = "bar";
            mockConn.setRequestProperty("Authorization", "Basic Zm9vOmJhcg==");

            mockConn.connect();

            mockConn.getResponseCode();
            result = 200;
            mockConn.getErrorStream();
            result = null;
            mockConn.getInputStream();
            result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 200);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test
    public void testErrorResponse() throws IOException {
        String error = "womp";
        final InputStream stream = new ByteArrayInputStream(error.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL();
            result = mockUrl;
            mockRequest.getMethod();
            result = HttpMethod.GET;
            mockUrl.openConnection();
            result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("GET");

            mockRequest.requiresAuthentication();
            result = true;
            mockRequest.getUsername();
            result = "foo";
            mockRequest.getPassword();
            result = "bar";
            mockConn.setRequestProperty("Authorization", "Basic Zm9vOmJhcg==");

            mockConn.connect();

            mockConn.getResponseCode();
            result = 404;
            mockConn.getErrorStream();
            result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 404);
        assertEquals(resp.getContent(), "womp");
    }
}
