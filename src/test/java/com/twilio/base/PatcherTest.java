package com.twilio.base;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import org.junit.Assert;
import org.junit.Before;
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
        // Initialize Twilio with test credentials to set executor service
        Twilio.init("test-username", "test-password");

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
        } finally {
            Twilio.destroy();
        }
    }
}
