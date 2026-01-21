package com.twilio.base;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TwilioResponseTest {

    @Test
    public void testConstructorAndGetters() {
        String content = "test-content";
        int statusCode = 200;
        Header[] headers = new Header[] {
            new BasicHeader("Content-Type", "application/json"),
            new BasicHeader("X-Request-Id", "req-123")
        };

        TwilioResponse<String> response = new TwilioResponse<>(content, statusCode, headers);

        Assert.assertEquals(content, response.getContent());
        Assert.assertEquals(statusCode, response.getStatusCode());
        Assert.assertNotNull(response.getHeaders());
        Assert.assertEquals(2, response.getHeaders().size());
        Assert.assertEquals("application/json", response.getHeaders().get("Content-Type"));
        Assert.assertEquals("req-123", response.getHeaders().get("X-Request-Id"));
    }

    @Test
    public void testNullHeaders() {
        String content = "test-content";
        int statusCode = 201;

        TwilioResponse<String> response = new TwilioResponse<>(content, statusCode, null);

        Assert.assertEquals(content, response.getContent());
        Assert.assertEquals(statusCode, response.getStatusCode());
        Assert.assertNotNull(response.getHeaders());
        Assert.assertTrue(response.getHeaders().isEmpty());
    }

    @Test
    public void testEmptyHeaders() {
        String content = "test-content";
        int statusCode = 204;
        Header[] headers = new Header[] {};

        TwilioResponse<String> response = new TwilioResponse<>(content, statusCode, headers);

        Assert.assertEquals(content, response.getContent());
        Assert.assertEquals(statusCode, response.getStatusCode());
        Assert.assertNotNull(response.getHeaders());
        Assert.assertTrue(response.getHeaders().isEmpty());
    }

    @Test
    public void testNullContent() {
        int statusCode = 404;
        Header[] headers = new Header[] {
            new BasicHeader("X-Error", "Not Found")
        };

        TwilioResponse<String> response = new TwilioResponse<>(null, statusCode, headers);

        Assert.assertNull(response.getContent());
        Assert.assertEquals(statusCode, response.getStatusCode());
        Assert.assertEquals("Not Found", response.getHeaders().get("X-Error"));
    }
}
