package com.twilio.http;

import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.http.IRequest.FormParameters;
import com.twilio.http.IRequest.FormParameters.Type;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NetworkHttpClientTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

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
    private HttpEntity mockEntity;

    @Mock
    private MultipartEntityBuilder mockMultipartEntityBuilder;

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);
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
    public void testJsonPost() throws IOException {
        setup(201, "frobozz", HttpMethod.POST, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.JSON);
        String body = "{\"from\":\"+12345\",\"body\":\"message body\",\"messages\":[{\"to\":\"+12345\"}]}";
        when(mockRequest.getBody()).thenReturn(body);
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

    @Test
    public void testMultipartFormDataWithTextOnly() throws IOException {
        // Mock setup
        setup(201, "success", HttpMethod.POST, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.MULTIPART_FORM_DATA);

        // Create test form parameters
        List<FormParameters> formParameters = new ArrayList<>();
        formParameters.add(new FormParameters("name", Type.TEXT, "John Doe"));
        formParameters.add(new FormParameters("email", Type.TEXT, "john@example.com"));
        when(mockRequest.getFormParameters()).thenReturn(formParameters);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(201, response.getStatusCode());
        assertEquals("success", response.getContent());

    }

    @Test
    public void testMultipartFormDataWithFile() throws IOException {
        // Create a temporary file for testing
        File testFile = temporaryFolder.newFile("test.jpg");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("test image data");
        }

        // Mock setup
        setup(201, "success", HttpMethod.POST, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.MULTIPART_FORM_DATA);

        // Create test form parameters
        List<FormParameters> formParameters = new ArrayList<>();
        formParameters.add(new FormParameters("file", Type.FILE, testFile.getAbsolutePath()));
        when(mockRequest.getFormParameters()).thenReturn(formParameters);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.MULTIPART_FORM_DATA);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(201, response.getStatusCode());
        assertEquals("success", response.getContent());
    }

    @Test
    public void testMultipartFormDataWithMixedContent() throws IOException {
        // Create a temporary file for testing
        File testFile = temporaryFolder.newFile("test.pdf");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("test pdf data");
        }

        // Mock setup
        setup(201, "success", HttpMethod.POST, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.MULTIPART_FORM_DATA);

        // Create test form parameters with both text and file
        List<FormParameters> formParameters = new ArrayList<>();
        formParameters.add(new FormParameters("name", Type.TEXT, "John Doe"));
        formParameters.add(new FormParameters("file", Type.FILE, testFile.getAbsolutePath()));
        when(mockRequest.getFormParameters()).thenReturn(formParameters);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.MULTIPART_FORM_DATA);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(201, response.getStatusCode());
        assertEquals("success", response.getContent());
    }

    @Test
    public void testPutMethod() throws IOException {
        // Mock setup for PUT request
        setup(200, "updated", HttpMethod.PUT, false);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(200, response.getStatusCode());
        assertEquals("updated", response.getContent());
    }

    @Test
    public void testPutMethodWithBody() throws IOException {
        // Mock setup for PUT request with a JSON body
        setup(200, "updated", HttpMethod.PUT, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.JSON);
        String jsonBody = "{\"name\":\"New Name\",\"active\":true}";
        when(mockRequest.getBody()).thenReturn(jsonBody);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(200, response.getStatusCode());
        assertEquals("updated", response.getContent());

    }

    @Test
    public void testPatchMethod() throws IOException {
        // Mock setup for PATCH request
        setup(200, "patched", HttpMethod.PATCH, false);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(200, response.getStatusCode());
        assertEquals("patched", response.getContent());
    }

    @Test
    public void testPatchMethodWithJsonBody() throws IOException {
        // Mock setup for PATCH request with a JSON body
        setup(200, "patched", HttpMethod.PATCH, false);
        when(mockRequest.getContentType()).thenReturn(EnumConstants.ContentType.JSON);
        String jsonBody = "{\"status\":\"active\"}";
        when(mockRequest.getBody()).thenReturn(jsonBody);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify
        assertEquals(200, response.getStatusCode());
        assertEquals("patched", response.getContent());
    }


    @Test
    public void testFormUrlEncodedMultipleParameters() throws IOException {
        // Mock setup
        setup(201, "created", HttpMethod.POST, false);

        // Set up multiple parameters
        Map<String, List<String>> postParams = new HashMap<>();
        postParams.put("name", Arrays.asList("John Doe"));
        postParams.put("email", Arrays.asList("john@example.com"));
        postParams.put("age", Arrays.asList("30"));
        postParams.put("tags", Arrays.asList("customer", "premium"));
        when(mockRequest.getPostParams()).thenReturn(postParams);

        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify response
        assertEquals(201, response.getStatusCode());
        assertEquals("created", response.getContent());
    }

    @Test
    public void testRequestWithMultipleHeaders() throws IOException {
        // Mock setup for POST request
        setup(201, "created", HttpMethod.POST, false);

        // Set up multiple header parameters
        Map<String, List<String>> headerParams = new HashMap<>();
        headerParams.put("X-Custom-Header", Arrays.asList("CustomValue"));
        headerParams.put("Accept-Language", Arrays.asList("en-US"));
        headerParams.put("Cache-Control", Arrays.asList("no-cache"));
        headerParams.put("X-Request-ID", Arrays.asList("abc123"));
        when(mockRequest.getHeaderParams()).thenReturn(headerParams);


        // Make the request
        Response response = client.makeRequest(mockRequest);

        // Verify response
        assertEquals(201, response.getStatusCode());
        assertEquals("created", response.getContent());

    }

}
