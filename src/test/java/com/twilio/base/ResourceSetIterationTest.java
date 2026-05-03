package com.twilio.base;

import com.twilio.http.TwilioRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.when;

// Simple test resource for testing ResourceSet iteration
class TestResource extends Resource {
    private final String id;
    
    public TestResource(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}

// Simple test reader for testing ResourceSet iteration
class TestReader extends Reader<TestResource> {
    @Override
    public ResourceSet<TestResource> read(TwilioRestClient client) {
        return null; // Not needed for our test
    }
    
    @Override
    public Page<TestResource> firstPage(TwilioRestClient client) {
        return null; // Not needed for our test
    }
    
    @Override
    public Page<TestResource> previousPage(Page<TestResource> page, TwilioRestClient client) {
        return null; // Not needed for our test
    }
    
    @Override
    public Page<TestResource> nextPage(Page<TestResource> page, TwilioRestClient client) {
        return null; // Not needed for our test
    }
    
    @Override
    public Page<TestResource> getPage(String targetUrl, TwilioRestClient client) {
        return null; // Not needed for our test
    }
}

public class ResourceSetIterationTest {

    @Mock
    TwilioRestClient client;

    @Mock
    Page<TestResource> page;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testResourceSetCanBeIteratedMultipleTimes() {
        // Setup mock data with concrete objects
        TestResource resource1 = new TestResource("resource1");
        TestResource resource2 = new TestResource("resource2");
        List<TestResource> resources = Arrays.asList(resource1, resource2);
        
        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(2);
        when(page.hasNextPage()).thenReturn(false);

        Reader<TestResource> reader = new TestReader().limit(2);
        ResourceSet<TestResource> resourceSet = new ResourceSet<>(reader, client, page);

        // First iteration - should work
        List<String> firstIterationResults = new ArrayList<>();
        for (TestResource resource : resourceSet) {
            firstIterationResults.add(resource.getId());
        }
        Assert.assertEquals(2, firstIterationResults.size());
        Assert.assertEquals("resource1", firstIterationResults.get(0));
        Assert.assertEquals("resource2", firstIterationResults.get(1));

        // Second iteration - should also work and return the same results
        List<String> secondIterationResults = new ArrayList<>();
        for (TestResource resource : resourceSet) {
            secondIterationResults.add(resource.getId());
        }
        Assert.assertEquals("Second iteration should return same number of elements", 2, secondIterationResults.size());
        Assert.assertEquals("resource1", secondIterationResults.get(0));
        Assert.assertEquals("resource2", secondIterationResults.get(1));

        // Third iteration using explicit iterator - should also work
        List<String> thirdIterationResults = new ArrayList<>();
        for (Iterator<TestResource> it = resourceSet.iterator(); it.hasNext(); ) {
            TestResource resource = it.next();
            thirdIterationResults.add(resource.getId());
        }
        Assert.assertEquals("Third iteration should return same number of elements", 2, thirdIterationResults.size());
        Assert.assertEquals("resource1", thirdIterationResults.get(0));
        Assert.assertEquals("resource2", thirdIterationResults.get(1));
    }

    @Test
    public void testResourceSetWithLimitCanBeIteratedMultipleTimes() {
        // Setup mock data with more resources than the limit
        TestResource resource1 = new TestResource("resource1");
        TestResource resource2 = new TestResource("resource2");
        TestResource resource3 = new TestResource("resource3");
        List<TestResource> resources = Arrays.asList(resource1, resource2, resource3);
        
        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(3);
        when(page.hasNextPage()).thenReturn(false);

        // Set limit to 2, so only first 2 should be returned
        Reader<TestResource> reader = new TestReader().limit(2);
        ResourceSet<TestResource> resourceSet = new ResourceSet<>(reader, client, page);

        // First iteration - should only return 2 elements due to limit
        List<String> firstIterationResults = new ArrayList<>();
        for (TestResource resource : resourceSet) {
            firstIterationResults.add(resource.getId());
        }
        Assert.assertEquals("First iteration should respect limit", 2, firstIterationResults.size());
        Assert.assertEquals("resource1", firstIterationResults.get(0));
        Assert.assertEquals("resource2", firstIterationResults.get(1));

        // Second iteration - should also respect limit and return same results
        List<String> secondIterationResults = new ArrayList<>();
        for (TestResource resource : resourceSet) {
            secondIterationResults.add(resource.getId());
        }
        Assert.assertEquals("Second iteration should respect limit", 2, secondIterationResults.size());
        Assert.assertEquals("resource1", secondIterationResults.get(0));
        Assert.assertEquals("resource2", secondIterationResults.get(1));
    }
}