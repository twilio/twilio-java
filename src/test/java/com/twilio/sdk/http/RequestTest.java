package com.twilio.sdk.http;

import org.junit.Test;

import static com.twilio.sdk.Assert.assertUrlsEqual;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

public class RequestTest {

    @Test
    public void testConstructURL() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParams() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux");
        assertUrlsEqual(expected, url);
    }

}

