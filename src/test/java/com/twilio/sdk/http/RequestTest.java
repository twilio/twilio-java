package com.twilio.sdk.http;

import com.google.common.collect.Range;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import org.joda.time.DateTime;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.twilio.Assert.assertQueryStringsEqual;
import static com.twilio.Assert.assertUrlsEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RequestTest {

    @Test
    public void testConstructorWithDomain() {
        Request request = new Request(HttpMethod.GET, TwilioRestClient.Domains.CONVERSATIONS, "/v1/uri", "AC123");
        assertNotNull(request);
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals("https://conversations.twilio.com/v1/uri", request.getUri());
    }

    @Test
    public void testConstructURL() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar");
        assertUrlsEqual(expected, url);
    }

    @Test(expected = ApiException.class)
    public void testConstructURLURISyntaxException() {
        Request request = new Request(HttpMethod.DELETE, "http://{", "AC123");
        request.constructURL();
        fail("ApiException was expected");
    }

    @Test
    public void testConstructURLWithParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryParam("baz", "quux");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz=quux");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParams() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("garply", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz=quux&garply=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithMultivaluedParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("baz", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz=quux&baz=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithInequalityParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryParam("baz>", "3");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=3");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeLowerBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryDateRange("baz", Range.greaterThan(new DateTime(2014, 1, 1, 0, 0)));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeUpperBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryDateRange("baz", Range.lessThan(new DateTime(2014, 1, 1, 0, 0)));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz<=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeClosed() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "/2010-04-01/foobar", "AC123");
        r.addQueryDateRange("baz", Range.closed(new DateTime(2014, 1, 1, 0, 0), new DateTime(2014, 6, 1, 0, 0)));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-01&baz<=2014-06-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testEncodeFormBody() {
        Request r = new Request(HttpMethod.POST, "http://example.com/foobar", "AC123");
        r.addPostParam("baz", "quux");
        r.addPostParam("garply", "xyzzy");
        String encoded = r.encodeFormBody();
        assertQueryStringsEqual("baz=quux&garply=xyzzy", encoded);
    }

    @Test
    public void testGetPassword() {
        Request request = new Request(HttpMethod.DELETE, "/uri", "AC123");
        request.setAuth("username", "password");
        assertEquals("password", request.getPassword());
    }

    @Test
    public void testGetUsername() {
        Request request = new Request(HttpMethod.DELETE, "/uri", "AC123");
        request.setAuth("username", "password");
        assertEquals("username", request.getUsername());
    }

    @Test
    public void testRequiresAuthentication() {
        Request request = new Request(HttpMethod.DELETE, "/uri", "AC123");
        assertFalse(request.requiresAuthentication());
        request.setAuth("username", "password");
        assertTrue(request.requiresAuthentication());
    }

}

