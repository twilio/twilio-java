package com.twilio.sdk.http;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class NetworkHttpClientTest {
    @Tested NetworkHttpClient tested;
    @Mocked Request mockRequest;
    @Mocked URL mockUrl;
    @Mocked HttpURLConnection mockConn;
    @Mocked OutputStream mockOutputStream;
    @Mocked OutputStreamWriter mockWriter;

    @Test
    public void testGet() throws UnsupportedEncodingException, IOException {
        String content = "frobozz";
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL(); result = mockUrl;
            mockRequest.getMethod(); result = HttpMethod.GET;
            mockUrl.openConnection(); result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("GET");

            mockRequest.requiresAuthentication(); result = false;
            mockConn.connect();

            mockConn.getResponseCode(); result = 200;
            mockConn.getErrorStream(); result = null;
            mockConn.getInputStream(); result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 200);
        assertEquals(resp.getContent(), "frobozz");
    }

    @Test
    public void testPost() throws UnsupportedEncodingException, IOException {
        String content = "frobozz";
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        new Expectations() {{
            mockRequest.constructURL(); result = mockUrl;
            mockRequest.getMethod(); result = HttpMethod.POST;
            mockUrl.openConnection(); result = mockConn;
            mockConn.setAllowUserInteraction(false);
            mockConn.addRequestProperty("Accept", "application/json");
            mockConn.addRequestProperty("Accept-Encoding", "utf-8");
            mockConn.setInstanceFollowRedirects(true);

            mockConn.setRequestMethod("POST");

            mockRequest.requiresAuthentication(); result = false;
            mockConn.setDoOutput(true);
            mockConn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            mockConn.connect();

            mockRequest.encodeFormBody(); result = "foo=bar&baz=quux";
            mockConn.getOutputStream(); result = mockOutputStream;
            new OutputStreamWriter(mockOutputStream); result = mockWriter;
            mockWriter.write("foo=bar&baz=quux");
            mockWriter.close();

            mockConn.getResponseCode(); result = 201;
            mockConn.getErrorStream(); result = null;
            mockConn.getInputStream(); result = stream;
        }};

        NetworkHttpClient client = new NetworkHttpClient();
        Response resp = client.makeRequest(mockRequest);

        assertEquals(resp.getStatusCode(), 201);
        assertEquals(resp.getContent(), "frobozz");
    }
}
