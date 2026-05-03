package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PatcherTest {

    @Mock
    TwilioRestClient client;

    @Mock
    Resource mockResource;

    @BeforeClass
    public static void setUpClass() {
        // Initialize Twilio once for all tests
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
     * Test implementation of Patcher for testing purposes
     */
    private static class TestPatcher extends Patcher<Resource> {
        private final Resource resource;

        public TestPatcher(Resource resource) {
            this.resource = resource;
        }

        @Override
        public Resource patch(TwilioRestClient client) {
            return resource;
        }
    }

    @Test
    public void testPatchWithClient() {
        TestPatcher patcher = new TestPatcher(mockResource);
        Resource result = patcher.patch(client);
        Assert.assertEquals(mockResource, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testPatcherReturnsCorrectResource() {
        Resource customResource = mockResource;
        TestPatcher patcher = new TestPatcher(customResource);
        Resource result = patcher.patch(client);
        Assert.assertSame(customResource, result);
    }

    @Test
    public void testPatchAsyncWithClientReturnsCompletableFuture() {
        try {
            TestPatcher patcher = new TestPatcher(mockResource);
            CompletableFuture<Resource> future = patcher.patchAsync(client);

            Assert.assertNotNull(future);
            Assert.assertTrue(future instanceof CompletableFuture);

            // Verify the future completes with the expected resource
            Resource result = future.get();
            Assert.assertEquals(mockResource, result);
        } catch (InterruptedException | ExecutionException e) {
            Assert.fail("Async patch operation failed: " + e.getMessage());
        }
    }

    @Test
    public void testPatchWithDefaultClient() {
        TestPatcher patcher = new TestPatcher(mockResource);
        Resource result = patcher.patch();

        Assert.assertNotNull(result);
        Assert.assertEquals(mockResource, result);
    }

    @Test
    public void testPatchAsyncWithDefaultClient() {
        try {
            TestPatcher patcher = new TestPatcher(mockResource);
            CompletableFuture<Resource> future = patcher.patchAsync();

            Assert.assertNotNull(future);
            Assert.assertTrue(future instanceof CompletableFuture);

            // Verify the future completes with the expected resource
            Resource result = future.get();
            Assert.assertEquals(mockResource, result);
        } catch (InterruptedException | ExecutionException e) {
            Assert.fail("Async patch operation with default client failed: " + e.getMessage());
        }
    }
}
