package com.twilio.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.hc.core5.http.ContentType;
import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {
    
    private TestHttpClient client;
    
    @Before
    public void setup() {
        client = new TestHttpClient();
    }

    @Test
    public void testGetContentTypePdf() {
        Path path = Paths.get("document.pdf");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("application/pdf", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypeJpg() {
        Path path = Paths.get("image.jpg");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("image/jpeg", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypeJpeg() {
        Path path = Paths.get("image.jpeg");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("image/jpeg", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypePng() {
        Path path = Paths.get("image.png");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("image/png", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypeDefaultForUnknown() {
        Path path = Paths.get("file.unknown");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("application/octet-stream", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypeCaseInsensitive() {
        Path path = Paths.get("image.PNG");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("image/png", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypeWithPathAndDots() {
        Path path = Paths.get("/path/to/my.file.pdf");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("application/pdf", contentType.getMimeType());
    }

    @Test
    public void testGetContentTypeWithoutExtension() {
        Path path = Paths.get("noextension");
        ContentType contentType = HttpClient.getContentType(path);
        assertEquals("application/octet-stream", contentType.getMimeType());
    }
    
    @Test
    public void testShouldRetryWithNullResponse() {
        assertTrue(client.shouldRetry(null, new int[]{HttpClient.ANY_500}));
    }
    
    @Test
    public void testShouldRetryWithSpecificStatusCode() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(429);
        
        assertTrue(client.shouldRetry(response, new int[]{429}));
        assertFalse(client.shouldRetry(response, new int[]{503}));
    }
    
    @Test
    public void testShouldRetryWithAny100StatusCode() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(100);
        
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_100}));
        
        when(response.getStatusCode()).thenReturn(199);
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_100}));
        
        when(response.getStatusCode()).thenReturn(200);
        assertFalse(client.shouldRetry(response, new int[]{HttpClient.ANY_100}));
    }
    
    @Test
    public void testShouldRetryWithAny200StatusCode() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(200);
        
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_200}));
        
        when(response.getStatusCode()).thenReturn(299);
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_200}));
        
        when(response.getStatusCode()).thenReturn(300);
        assertFalse(client.shouldRetry(response, new int[]{HttpClient.ANY_200}));
    }
    
    @Test
    public void testShouldRetryWithAny300StatusCode() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(300);
        
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_300}));
        
        when(response.getStatusCode()).thenReturn(399);
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_300}));
        
        when(response.getStatusCode()).thenReturn(400);
        assertFalse(client.shouldRetry(response, new int[]{HttpClient.ANY_300}));
    }
    
    @Test
    public void testShouldRetryWithAny400StatusCode() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(400);
        
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_400}));
        
        when(response.getStatusCode()).thenReturn(499);
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_400}));
        
        when(response.getStatusCode()).thenReturn(500);
        assertFalse(client.shouldRetry(response, new int[]{HttpClient.ANY_400}));
    }
    
    @Test
    public void testShouldRetryWithAny500StatusCode() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(500);
        
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_500}));
        
        when(response.getStatusCode()).thenReturn(599);
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_500}));
        
        when(response.getStatusCode()).thenReturn(600);
        assertFalse(client.shouldRetry(response, new int[]{HttpClient.ANY_500}));
    }
    
    @Test
    public void testShouldRetryWithMultipleRetryCodes() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(429);
        
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_500, 429}));
        
        when(response.getStatusCode()).thenReturn(503);
        assertTrue(client.shouldRetry(response, new int[]{HttpClient.ANY_500, 429}));
        
        when(response.getStatusCode()).thenReturn(200);
        assertFalse(client.shouldRetry(response, new int[]{HttpClient.ANY_500, 429}));
    }
    
    @Test
    public void testShouldRetryWithDefaultRetryCodes() {
        Response response = mock(Response.class);
        
        when(response.getStatusCode()).thenReturn(500);
        assertTrue(client.shouldRetry(response, HttpClient.RETRY_CODES));
        
        when(response.getStatusCode()).thenReturn(503);
        assertTrue(client.shouldRetry(response, HttpClient.RETRY_CODES));
        
        when(response.getStatusCode()).thenReturn(200);
        assertFalse(client.shouldRetry(response, HttpClient.RETRY_CODES));
        
        when(response.getStatusCode()).thenReturn(400);
        assertFalse(client.shouldRetry(response, HttpClient.RETRY_CODES));
    }
    
    // Test HttpClient subclass to expose protected method for testing
    private static class TestHttpClient extends HttpClient {
        @Override
        public Response makeRequest(Request request) {
            return null; // Not needed for testing
        }
        
        @Override
        public boolean shouldRetry(Response response, int[] retryCodes) {
            return super.shouldRetry(response, retryCodes);
        }
    }
}