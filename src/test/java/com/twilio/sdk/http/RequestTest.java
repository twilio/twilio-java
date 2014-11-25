package com.twilio.sdk.http;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import org.joda.time.DateTime;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.twilio.sdk.Assert.assertQueryStringsEqual;
import static com.twilio.sdk.Assert.assertUrlsEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RequestTest {

    @Test
    public void testConstructorWithDomain() {
        Request request = new Request(HttpMethod.GET, TwilioRestClient.Domains.API, "/uri");
        assertNotNull(request);
        assertEquals(TwilioRestClient.Domains.API, request.getDomain());
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals("/uri", request.getUri());
    }

    @Test
    public void testConstructURL() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar");
        assertUrlsEqual(expected, url);
    }

    @Test(expected = ApiException.class)
    public void testConstructURLMalformedURLException() {
        Request request = new Request(HttpMethod.DELETE, "abc://www.example.com");
        request.constructURL();
        fail("ApiException was expected");
    }

    @Test(expected = ApiException.class)
    public void testConstructURLURISyntaxException() {
        Request request = new Request(HttpMethod.DELETE, "http://{");
        request.constructURL();
        fail("ApiException was expected");
    }

    @Test
    public void testConstructURLWithParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParams() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("garply", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux&garply=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithMultivaluedParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("baz", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux&baz=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithInequalityParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz>", "3");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz>=3");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeLowerBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryDateRange("baz", Range.greaterThan(new DateTime(2014, 1, 1, 0, 0)));
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz>=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeUpperBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryDateRange("baz", Range.lessThan(new DateTime(2014, 1, 1, 0, 0)));
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz<=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeClosed() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryDateRange("baz", Range.closed(new DateTime(2014, 1, 1, 0, 0), new DateTime(2014, 6, 1, 0, 0)));
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz>=2014-01-01&baz<=2014-06-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testEncodeFormBody() {
        Request r = new Request(HttpMethod.POST, "http://example.com/foobar");
        r.addPostParam("baz", "quux");
        r.addPostParam("garply", "xyzzy");
        String encoded = r.encodeFormBody();
        assertQueryStringsEqual("baz=quux&garply=xyzzy", encoded);
    }

    @Test
    public void testGetPassword() {
        Request request = new Request(HttpMethod.DELETE, "/uri");
        request.setAuth("username", "password");
        assertEquals("password", request.getPassword());
    }

    @Test
    public void testGetUsername() {
        Request request = new Request(HttpMethod.DELETE, "/uri");
        request.setAuth("username", "password");
        assertEquals("username", request.getUsername());
    }

    @Test
    public void testRequiresAuthentication() {
        Request request = new Request(HttpMethod.DELETE, "/uri");
        assertFalse(request.requiresAuthentication());
        request.setAuth("username", "password");
        assertTrue(request.requiresAuthentication());
    }

    @Test
    public void testSetMethod() {
        Request request = new Request(HttpMethod.GET, "/uri");
        request.setMethod(HttpMethod.DELETE);
        assertEquals(HttpMethod.DELETE, request.getMethod());
    }

}

