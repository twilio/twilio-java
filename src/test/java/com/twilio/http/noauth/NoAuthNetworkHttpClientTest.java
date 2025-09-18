package com.twilio.http.noauth;

import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Response;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoAuthNetworkHttpClientTest {

    private NoAuthNetworkHttpClient client;

    @Mock
    private NoAuthRequest mockRequest;

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
        client = new NoAuthNetworkHttpClient();
    }

    private void setup(
        final int statusCode,
        final String content,
        final HttpMethod method
    ) throws IOException {
        final InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"));

        when(mockRequest.getMethod()).thenReturn(method);
        when(mockRequest.constructURL()).thenReturn(new URL("http://foo.com/hello"));
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.FORM_URLENCODED);
        when(mockClient.execute(any())).thenReturn(mockResponse);
        when(mockEntity.isRepeatable()).thenReturn(true);
        when(mockEntity.getContentLength()).thenReturn(1L);
        when(mockEntity.getContent()).thenReturn(stream);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockResponse.getCode()).thenReturn(statusCode);
    }


    @Test
    public void testReliableRequest() {
        NoAuthRequest request = mock(NoAuthRequest.class);
        when(request.getMethod()).thenReturn(HttpMethod.GET);

        NoAuthNetworkHttpClient clientSpy = spy(client);
        doReturn(new Response("", 204)).when(clientSpy).makeRequest(request);

        clientSpy.reliableRequest(request);

        assertNotNull(clientSpy.getLastRequest());
        assertNotNull(clientSpy.getLastResponse());
    }

    @Test
    public void testShouldRetry() {
        Response resp = new Response("error", 500);
        boolean shouldRetry = client.shouldRetry(resp, new int[]{500});
        assertEquals(true, shouldRetry);

        resp = new Response("success", 200);
        shouldRetry = client.shouldRetry(resp, new int[]{500});
        assertEquals(false, shouldRetry);

        shouldRetry = client.shouldRetry(null, new int[]{500});
        assertEquals(true, shouldRetry);
    }

    @Test
    public void testDefaultConstructor() throws IOException {
        // Create client using default constructor
        NoAuthNetworkHttpClient defaultClient = new NoAuthNetworkHttpClient();

        // Setup mock NoAuthRequest
        NoAuthRequest request = mock(NoAuthRequest.class);
        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.constructURL()).thenReturn(new URL("http://example.com"));
        when(request.getHeaderParams()).thenReturn(new HashMap<>());

        // This test verifies that the default constructor creates a functional client
        // We can't directly test the internals, but we can verify the client was constructed
        // without exceptions and has the expected type
        assertNotNull(defaultClient);
        assertEquals(NoAuthNetworkHttpClient.class, defaultClient.getClass());
    }

    @Test
    public void testRequestConfigConstructor() {
        // Create a custom RequestConfig
        RequestConfig customConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(5000))
                .build();

        // Create client with custom RequestConfig
        NoAuthNetworkHttpClient configClient = new NoAuthNetworkHttpClient(customConfig);

        // Verify client was created successfully
        assertNotNull(configClient);
        assertEquals(NoAuthNetworkHttpClient.class, configClient.getClass());
    }

    @Test
    public void testRequestAndSocketConfigConstructor() {
        // Create custom configs
        RequestConfig customRequestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(5000))
                .build();

        SocketConfig customSocketConfig = SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(5000))
                .build();

        // Create client with both custom configs
        NoAuthNetworkHttpClient fullConfigClient = new NoAuthNetworkHttpClient(customRequestConfig, customSocketConfig);

        // Verify client was created successfully
        assertNotNull(fullConfigClient);
        assertEquals(NoAuthNetworkHttpClient.class, fullConfigClient.getClass());
    }
}
