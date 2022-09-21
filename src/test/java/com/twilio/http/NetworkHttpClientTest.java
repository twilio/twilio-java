package com.twilio.http;

import com.twilio.exception.ApiConnectionException;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class NetworkHttpClientTest {

    private NetworkHttpClient client;

    @Mock
    private NetworkHttpClient mockedNetworkHttpClient;

    @Mock
    private Request mockRequest;

    @Spy
    private HttpClientBuilder mockBuilder;

    @Mock
    private CloseableHttpClient mockClient;

    @Mock
    private CloseableHttpResponse mockResponse;

    @Mock
    private StatusLine mockStatusLine;

    @Mock
    private HttpEntity mockEntity;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        doReturn(mockClient).when(mockBuilder).build();
        client = new NetworkHttpClient(mockBuilder);
    }

    private void setup(
        final int statusCode,
        final String content,
        final HttpMethod method,
        final Boolean requiresAuthentication
    ) throws IOException {
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        when(mockRequest.getMethod()).thenReturn(method);
        when(mockRequest.constructURL()).thenReturn(new URL("http://foo.com/hello"));
        when(mockRequest.requiresAuthentication()).thenReturn(requiresAuthentication);
        when(mockRequest.getAuthString()).thenReturn("foo:bar");
        when(mockClient.execute(any())).thenReturn(mockResponse);
        when(mockEntity.isRepeatable()).thenReturn(true);
        when(mockEntity.getContentLength()).thenReturn(1L);
        when(mockEntity.getContent()).thenReturn(stream);
        when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockStatusLine.getStatusCode()).thenReturn(statusCode);

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

        when(mockBuilder.build()).thenReturn(mockClient);
        when(mockRequest.getMethod()).thenReturn(HttpMethod.GET);
        when(mockRequest.constructURL()).thenReturn(new URL("http://foo.com/hello"));
        when(mockRequest.requiresAuthentication()).thenReturn(true);
        when(mockRequest.getAuthString()).thenReturn("foo:bar");
        when(mockClient.execute(any())).thenThrow(new ApiConnectionException("foo"));
        client = new NetworkHttpClient(mockBuilder);
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
        Request request = new Request(HttpMethod.GET, "http://foo.com/hello");

        NetworkHttpClient clientSpy = spy(client);
        doReturn(new Response("", TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT)).when(clientSpy).makeRequest(request);
        clientSpy.reliableRequest(request);
        assertNotNull(clientSpy.getLastRequest());
        assertNotNull(clientSpy.getLastResponse());
    }

    @Test
    public void testReliableRequestWithRetries() {
        Request request = new Request(HttpMethod.GET, "http://foo.com/hello");
        NetworkHttpClient clientSpy = spy(client);
        doReturn(null).when(clientSpy).makeRequest(request);
        clientSpy.reliableRequest(request);
        assertNull(clientSpy.getLastResponse());
    }

    @Test
    public void testReliableRequestWithRetries100() {
        Request request = new Request(HttpMethod.GET, "/uri");

        when(mockedNetworkHttpClient.makeRequest(request)).thenReturn(new Response("", 500));

        mockedNetworkHttpClient.reliableRequest(request);
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
