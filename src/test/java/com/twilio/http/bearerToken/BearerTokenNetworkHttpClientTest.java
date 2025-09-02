package com.twilio.http.bearerToken;

import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Response;
import com.twilio.http.bearertoken.BearerTokenNetworkHttpClient;
import com.twilio.http.bearertoken.BearerTokenRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BearerTokenNetworkHttpClientTest {

    private BearerTokenNetworkHttpClient client;

    @Mock
    private BearerTokenRequest mockRequest;

    @Spy
    private HttpClientBuilder mockBuilder;

    @Mock
    private CloseableHttpClient mockClient;

    @Mock
    private CloseableHttpResponse mockResponse;

    @Mock
    private HttpEntity mockEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doReturn(mockClient).when(mockBuilder).build();
        client = new BearerTokenNetworkHttpClient(mockBuilder);
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
        when(mockRequest.getAuthString()).thenReturn("Bearer token123");
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.FORM_URLENCODED);
        when(mockClient.execute(any())).thenReturn(mockResponse);
        when(mockEntity.isRepeatable()).thenReturn(true);
        when(mockEntity.getContentLength()).thenReturn(1L);
        when(mockEntity.getContent()).thenReturn(stream);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockResponse.getCode()).thenReturn(statusCode);
    }

    @Test
    public void testGet() throws IOException {
        setup(200, "success", HttpMethod.GET, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(200, resp.getStatusCode());
        assertEquals("success", resp.getContent());
    }

    @Test
    public void testGetWithAuthentication() throws IOException {
        setup(200, "authenticated", HttpMethod.GET, true);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(200, resp.getStatusCode());
        assertEquals("authenticated", resp.getContent());
    }

    @Test
    public void testPost() throws IOException {
        setup(201, "created", HttpMethod.POST, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(201, resp.getStatusCode());
        assertEquals("created", resp.getContent());
    }

    @Test
    public void testJsonPost() throws IOException {
        setup(201, "created", HttpMethod.POST, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.JSON);
        String body = "{\"name\":\"value\"}";
        when(mockRequest.getBody()).thenReturn(body);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(201, resp.getStatusCode());
        assertEquals("created", resp.getContent());
    }

    @Test
    public void testFormUrlEncodedPost() throws IOException {
        setup(201, "form-urlencoded", HttpMethod.POST, false);

        // Set up post parameters
        Map<String, List<String>> postParams = new HashMap<>();
        postParams.put("name", Arrays.asList("John Doe"));
        postParams.put("tags", Arrays.asList("customer", "premium"));
        when(mockRequest.getPostParams()).thenReturn(postParams);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(201, resp.getStatusCode());
        assertEquals("form-urlencoded", resp.getContent());
    }

    @Test
    public void testPut() throws IOException {
        setup(200, "updated", HttpMethod.PUT, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(200, resp.getStatusCode());
        assertEquals("updated", resp.getContent());
    }

    @Test
    public void testPatch() throws IOException {
        setup(200, "patched", HttpMethod.PATCH, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(200, resp.getStatusCode());
        assertEquals("patched", resp.getContent());
    }

    @Test
    public void testDelete() throws IOException {
        setup(204, "", HttpMethod.DELETE, false);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(204, resp.getStatusCode());
        assertEquals("", resp.getContent());
    }

    @Test
    public void testWithCustomHeaders() throws IOException {
        setup(200, "headers", HttpMethod.GET, false);

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("X-Custom-Header", Arrays.asList("CustomValue"));
        headers.put("Accept-Language", Arrays.asList("en-US"));
        when(mockRequest.getHeaderParams()).thenReturn(headers);

        Response resp = client.makeRequest(mockRequest);

        assertEquals(200, resp.getStatusCode());
        assertEquals("headers", resp.getContent());
    }

    @Test(expected = ApiException.class)
    public void testIOException() throws IOException {
        when(mockRequest.getMethod()).thenReturn(HttpMethod.GET);
        when(mockRequest.constructURL()).thenReturn(new URL("http://foo.com/hello"));
        when(mockRequest.requiresAuthentication()).thenReturn(false);
        when(mockClient.execute(any())).thenThrow(new IOException("Connection error"));

        client.makeRequest(mockRequest);
    }

    @Test
    public void testReliableRequest() {
        BearerTokenRequest request = mock(BearerTokenRequest.class);
        when(request.getMethod()).thenReturn(HttpMethod.GET);

        BearerTokenNetworkHttpClient clientSpy = spy(client);
        doReturn(new Response("", 204)).when(clientSpy).makeRequest(request);

        clientSpy.reliableRequest(request);

        assertNotNull(clientSpy.getLastRequest());
        assertNotNull(clientSpy.getLastResponse());
    }
}
