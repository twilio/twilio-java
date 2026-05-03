package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ResourceDeleterTest {

    @Mock
    TwilioRestClient client;

    @Mock
    Resource mockResource;

    @BeforeClass
    public static void setUpClass() {
        Twilio.init("test-username", "test-password");
    }

    @AfterClass
    public static void tearDownClass() {
        Twilio.destroy();
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test implementation of ResourceDeleter that only implements the abstract delete method.
     */
    private static class TestResourceDeleter extends ResourceDeleter<Resource, Resource> {
        private final Resource resource;

        public TestResourceDeleter(Resource resource) {
            this.resource = resource;
        }

        @Override
        public Resource delete(TwilioRestClient client) {
            return resource;
        }
    }

    /**
     * Test implementation that also overrides deleteWithResponse.
     */
    private static class TestResourceDeleterWithResponse extends ResourceDeleter<Resource, Resource> {
        private final Resource resource;
        private final int statusCode;
        private final Header[] headers;

        public TestResourceDeleterWithResponse(Resource resource, int statusCode, Header[] headers) {
            this.resource = resource;
            this.statusCode = statusCode;
            this.headers = headers;
        }

        @Override
        public Resource delete(TwilioRestClient client) {
            return resource;
        }

        @Override
        public TwilioResponse<Resource> deleteWithResponse(TwilioRestClient client) {
            return new TwilioResponse<>(resource, statusCode, headers);
        }
    }

    @Test
    public void testDeleteWithClient() {
        TestResourceDeleter deleter = new TestResourceDeleter(mockResource);
        Resource result = deleter.delete(client);
        Assert.assertEquals(mockResource, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testDeleteReturnsCorrectResource() {
        Resource customResource = mockResource;
        TestResourceDeleter deleter = new TestResourceDeleter(customResource);
        Resource result = deleter.delete(client);
        Assert.assertSame(customResource, result);
    }

    @Test
    public void testDeleteWithDefaultClient() {
        TestResourceDeleter deleter = new TestResourceDeleter(mockResource);
        Resource result = deleter.delete();
        Assert.assertNotNull(result);
        Assert.assertEquals(mockResource, result);
    }

    @Test
    public void testDeleteAsyncWithClient() {
        try {
            TestResourceDeleter deleter = new TestResourceDeleter(mockResource);
            CompletableFuture<Resource> future = deleter.deleteAsync(client);

            Assert.assertNotNull(future);
            Assert.assertTrue(future instanceof CompletableFuture);

            Resource result = future.get();
            Assert.assertEquals(mockResource, result);
        } catch (InterruptedException | ExecutionException e) {
            Assert.fail("Async delete operation failed: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteAsyncWithDefaultClient() {
        try {
            TestResourceDeleter deleter = new TestResourceDeleter(mockResource);
            CompletableFuture<Resource> future = deleter.deleteAsync();

            Assert.assertNotNull(future);
            Assert.assertTrue(future instanceof CompletableFuture);

            Resource result = future.get();
            Assert.assertEquals(mockResource, result);
        } catch (InterruptedException | ExecutionException e) {
            Assert.fail("Async delete operation with default client failed: " + e.getMessage());
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDeleteWithResponseThrowsWhenNotOverridden() {
        TestResourceDeleter deleter = new TestResourceDeleter(mockResource);
        deleter.deleteWithResponse(client);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDeleteWithResponseDefaultClientThrowsWhenNotOverridden() {
        TestResourceDeleter deleter = new TestResourceDeleter(mockResource);
        deleter.deleteWithResponse();
    }

    @Test
    public void testDeleteWithResponseReturnsCorrectContent() {
        Header[] headers = new Header[]{new BasicHeader("Content-Type", "application/json")};
        TestResourceDeleterWithResponse deleter = new TestResourceDeleterWithResponse(mockResource, 200, headers);

        TwilioResponse<Resource> response = deleter.deleteWithResponse(client);

        Assert.assertNotNull(response);
        Assert.assertEquals(mockResource, response.getContent());
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json", response.getHeaders().get("Content-Type"));
    }

    @Test
    public void testDeleteWithResponseDefaultClient() {
        Header[] headers = new Header[]{new BasicHeader("X-Request-Id", "abc123")};
        TestResourceDeleterWithResponse deleter = new TestResourceDeleterWithResponse(mockResource, 204, headers);

        TwilioResponse<Resource> response = deleter.deleteWithResponse();

        Assert.assertNotNull(response);
        Assert.assertEquals(mockResource, response.getContent());
        Assert.assertEquals(204, response.getStatusCode());
        Assert.assertEquals("abc123", response.getHeaders().get("X-Request-Id"));
    }

    @Test
    public void testDeleteReturnsNull() {
        TestResourceDeleter deleter = new TestResourceDeleter(null);
        Resource result = deleter.delete(client);
        Assert.assertNull(result);
    }
}
