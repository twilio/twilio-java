package com.twilio.base;

import com.twilio.http.TwilioRestClient;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.when;

class ResponseTestResource extends Resource {
    private final String id;

    public ResponseTestResource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

class ResponseTestReader extends Reader<ResponseTestResource> {
    @Override
    public ResourceSet<ResponseTestResource> read(TwilioRestClient client) {
        return null;
    }

    @Override
    public Page<ResponseTestResource> firstPage(TwilioRestClient client) {
        return null;
    }

    @Override
    public Page<ResponseTestResource> previousPage(Page<ResponseTestResource> page, TwilioRestClient client) {
        return null;
    }

    @Override
    public Page<ResponseTestResource> nextPage(Page<ResponseTestResource> page, TwilioRestClient client) {
        return null;
    }

    @Override
    public Page<ResponseTestResource> getPage(String targetUrl, TwilioRestClient client) {
        return null;
    }
}

public class ResourceSetResponseTest {

    @Mock
    TwilioRestClient client;

    @Mock
    Page<ResponseTestResource> page;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConstructorAndGetters() {
        ResponseTestResource resource1 = new ResponseTestResource("res-1");
        List<ResponseTestResource> resources = Arrays.asList(resource1);

        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(10);
        when(page.hasNextPage()).thenReturn(false);

        Reader<ResponseTestResource> reader = new ResponseTestReader();
        ResourceSet<ResponseTestResource> resourceSet = new ResourceSet<>(reader, client, page);

        Header[] headers = new Header[] {
            new BasicHeader("X-Twilio-Request-Id", "req-456"),
            new BasicHeader("Content-Type", "application/json")
        };
        int statusCode = 200;

        ResourceSetResponse<ResponseTestResource> response = new ResourceSetResponse<>(resourceSet, statusCode, headers);

        Assert.assertEquals(statusCode, response.getStatusCode());
        Assert.assertEquals(2, response.getHeaders().size());
        Assert.assertEquals("req-456", response.getHeader("X-Twilio-Request-Id"));
        Assert.assertEquals("application/json", response.getHeader("Content-Type"));
        Assert.assertNull(response.getHeader("Non-Existent-Header"));
        Assert.assertSame(resourceSet, response.getResourceSet());
    }

    @Test
    public void testNullHeaders() {
        ResponseTestResource resource1 = new ResponseTestResource("res-1");
        List<ResponseTestResource> resources = Arrays.asList(resource1);

        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(10);
        when(page.hasNextPage()).thenReturn(false);

        Reader<ResponseTestResource> reader = new ResponseTestReader();
        ResourceSet<ResponseTestResource> resourceSet = new ResourceSet<>(reader, client, page);

        ResourceSetResponse<ResponseTestResource> response = new ResourceSetResponse<>(resourceSet, 200, null);

        Assert.assertNotNull(response.getHeaders());
        Assert.assertTrue(response.getHeaders().isEmpty());
        Assert.assertNull(response.getHeader("Any-Header"));
    }

    @Test
    public void testDelegationMethods() {
        ResponseTestResource resource1 = new ResponseTestResource("res-1");
        ResponseTestResource resource2 = new ResponseTestResource("res-2");
        List<ResponseTestResource> resources = Arrays.asList(resource1, resource2);

        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(50);
        when(page.hasNextPage()).thenReturn(false);

        Reader<ResponseTestResource> reader = new ResponseTestReader().limit(100);
        ResourceSet<ResponseTestResource> resourceSet = new ResourceSet<>(reader, client, page);

        Header[] headers = new Header[] { new BasicHeader("X-Test", "value") };
        ResourceSetResponse<ResponseTestResource> response = new ResourceSetResponse<>(resourceSet, 200, headers);

        // Test delegation methods
        Assert.assertEquals(50, response.getPageSize().intValue());
        Assert.assertEquals(100L, response.getLimit().longValue());
        Assert.assertTrue(response.isAutoPaging());

        // Test setters return this for chaining
        ResourceSetResponse<ResponseTestResource> chained = response.setAutoPaging(false);
        Assert.assertSame(response, chained);
        Assert.assertFalse(response.isAutoPaging());
    }

    @Test
    public void testIteration() {
        ResponseTestResource resource1 = new ResponseTestResource("res-1");
        ResponseTestResource resource2 = new ResponseTestResource("res-2");
        List<ResponseTestResource> resources = Arrays.asList(resource1, resource2);

        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(10);
        when(page.hasNextPage()).thenReturn(false);

        Reader<ResponseTestResource> reader = new ResponseTestReader();
        ResourceSet<ResponseTestResource> resourceSet = new ResourceSet<>(reader, client, page);

        Header[] headers = new Header[] { new BasicHeader("X-Test", "value") };
        ResourceSetResponse<ResponseTestResource> response = new ResourceSetResponse<>(resourceSet, 200, headers);

        // Test iteration
        int count = 0;
        for (ResponseTestResource resource : response) {
            count++;
            Assert.assertNotNull(resource.getId());
        }
        Assert.assertEquals(2, count);
    }

    @Test
    public void testToString() {
        ResponseTestResource resource1 = new ResponseTestResource("res-1");
        List<ResponseTestResource> resources = Arrays.asList(resource1);

        when(page.getRecords()).thenReturn(resources);
        when(page.getPageSize()).thenReturn(10);
        when(page.hasNextPage()).thenReturn(false);

        Reader<ResponseTestResource> reader = new ResponseTestReader();
        ResourceSet<ResponseTestResource> resourceSet = new ResourceSet<>(reader, client, page);

        Header[] headers = new Header[] { new BasicHeader("X-Test", "value") };
        ResourceSetResponse<ResponseTestResource> response = new ResourceSetResponse<>(resourceSet, 200, headers);

        String str = response.toString();
        Assert.assertTrue(str.contains("statusCode=200"));
        Assert.assertTrue(str.contains("X-Test"));
    }
}
