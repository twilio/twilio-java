package com.twilio.http;

import com.twilio.exception.ApiException;
import com.twilio.rest.Domains;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.twilio.Assert.assertQueryStringsEqual;
import static com.twilio.Assert.assertUrlsEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RequestTest {

    @Test
    public void testConstructorWithDomain() {
        Request request = new Request(HttpMethod.GET, Domains.IPMESSAGING.toString(), "/v1/uri");
        assertNotNull(request);
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals("https://chat.twilio.com/v1/uri", request.getUrl());
    }

    @Test
    public void testConstructURL() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar");
        assertUrlsEqual(expected, url);
    }

    @Test(expected = ApiException.class)
    public void testConstructURLURISyntaxException() {
        Request request = new Request(HttpMethod.DELETE, "http://{");
        request.constructURL();
        fail("ApiException was expected");
    }

    @Test
    public void testConstructURLWithPipe() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foo|bar");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foo%7Cbar");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithMultipleSlashes() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foo|bar/bar|foo");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foo%7Cbar/bar%7Cfoo");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithCredentials() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, "user:pass@" + Domains.API.toString(), "/2010-04-01/foobar");
        URL url = r.constructURL();
        URL expected = new URL("https://user:pass@api.twilio.com/2010-04-01/foobar");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryParam("baz", "quux");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz=quux");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParams() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("garply", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz=quux&garply=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithPlusPrefix() {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryParam("To", "+18888888888");
        URL url = r.constructURL();
        String expected = "https://api.twilio.com/2010-04-01/foobar?To=%2B18888888888";
        assertEquals(expected, url.toString());
    }

    @Test
    public void testConstructURLWithMultivaluedParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("baz", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz=quux&baz=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithInequalityParam() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryParam("baz>", "3");
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=3");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeLowerBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryDateRange("baz", LocalDate.of(2014, 1, 1), null);
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeUpperBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryDateRange("baz", null, LocalDate.of(2014, 1, 1));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz<=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeClosed() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryDateRange("baz", LocalDate.of(2014, 1, 10), LocalDate.of(2014, 6, 1));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-10&baz<=2014-06-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeMismatchedBounds() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        LocalDate wrongLowerBound = LocalDate.of(2020, 6, 1);
        LocalDate wrongUpperBound = LocalDate.of(2014, 1, 10);
        r.addQueryDateRange("baz", wrongLowerBound, wrongUpperBound);
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-10&baz<=2020-06-01");
        assertNotEquals(expected, url);
    }

    @Test
    public void testAddQueryDateTimeRangeLowerBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryDateTimeRange("baz", ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), null);
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-01T00:00:00");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateTimeRangeUpperBound() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryDateTimeRange("baz", null, ZonedDateTime.of(2014, 1, 1, 22, 0, 0, 0, ZoneOffset.UTC));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz<=2014-01-01T22:00:00");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateTimeRangeClosed() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        r.addQueryDateTimeRange("baz", ZonedDateTime.of(2014, 1, 10, 14, 0, 0, 0, ZoneOffset.UTC),
            ZonedDateTime.of(2014, 6, 1, 16, 0, 0, 0, ZoneOffset.UTC));
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-10T14:00:00&baz<=2014-06-01T16:00:00");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateTimeRangeClosedNotUTC() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        ZoneId z = ZoneId.of("America/Chicago");
        ZonedDateTime begin = ZonedDateTime.of(2014, 1, 10, 14, 0, 0, 0, z);
        ZonedDateTime end = ZonedDateTime.of(2014, 6, 1, 16, 0, 0, 0, z);
        r.addQueryDateTimeRange("baz", begin, end);
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-10T20:00:00&baz<=2014-06-01T21:00:00");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateTimeRangeMismatchedBounds() throws MalformedURLException {
        Request r = new Request(HttpMethod.GET, Domains.API.toString(), "/2010-04-01/foobar");
        ZonedDateTime wrongLowerBound = ZonedDateTime.of(2020, 6, 1, 16, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime wrongUpperBound = ZonedDateTime.of(2014, 1, 10, 14, 0, 0, 0, ZoneOffset.UTC);
        r.addQueryDateTimeRange("baz", wrongLowerBound, wrongUpperBound);
        URL url = r.constructURL();
        URL expected = new URL("https://api.twilio.com/2010-04-01/foobar?baz>=2014-01-10T14:00:00&baz<=2020-06-01T16:00:00");
        assertNotEquals(expected, url);
    }

    @Test
    public void testNoEdgeOrRegionInUrl() throws MalformedURLException {
        final Request request = new Request(HttpMethod.GET, "https://api.twilio.com");

        assertUrlsEqual(new URL("https://api.twilio.com"), request.constructURL());

        request.setRegion("region");
        assertUrlsEqual(new URL("https://api.region.twilio.com"), request.constructURL());

        request.setEdge("edge");
        assertUrlsEqual(new URL("https://api.edge.region.twilio.com"), request.constructURL());

        request.setRegion(null);
        assertUrlsEqual(new URL("https://api.edge.us1.twilio.com"), request.constructURL());
    }

    @Test
    public void testRegionInUrl() throws MalformedURLException {
        final Request request = new Request(HttpMethod.GET, "https://api.urlRegion.twilio.com");

        assertUrlsEqual(new URL("https://api.urlRegion.twilio.com"), request.constructURL());

        request.setRegion("region");
        assertUrlsEqual(new URL("https://api.region.twilio.com"), request.constructURL());

        request.setEdge("edge");
        assertUrlsEqual(new URL("https://api.edge.region.twilio.com"), request.constructURL());

        request.setRegion(null);
        assertUrlsEqual(new URL("https://api.edge.urlRegion.twilio.com"), request.constructURL());
    }

    @Test
    public void testRegionAndEdgeInUrl() throws MalformedURLException {
        final Request request = new Request(HttpMethod.GET, "https://api.urlEdge.urlRegion.twilio.com");

        assertUrlsEqual(new URL("https://api.urlEdge.urlRegion.twilio.com"), request.constructURL());

        request.setRegion("region");
        assertUrlsEqual(new URL("https://api.urlEdge.region.twilio.com"), request.constructURL());

        request.setEdge("edge");
        assertUrlsEqual(new URL("https://api.edge.region.twilio.com"), request.constructURL());

        request.setRegion(null);
        assertUrlsEqual(new URL("https://api.edge.urlRegion.twilio.com"), request.constructURL());
    }

    @Test
    public void testRegionInConstructor() {
        final Request request = new Request(HttpMethod.GET, Domains.ACCOUNTS.toString(), "/path/to/something.json?foo=12.34#bar", "region");

        assertUrlsEqual("https://accounts.region.twilio.com/path/to/something.json?foo=12.34#bar", request.constructURL());

        request.setEdge("edge");
        assertUrlsEqual("https://accounts.edge.region.twilio.com/path/to/something.json?foo=12.34#bar", request.constructURL());
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
    public void testEquals() {
        Request request = new Request(HttpMethod.DELETE, "/uri");
        request.setAuth("username", "password");
        assertNotEquals(request, new Object());
        assertNotEquals(null, request);
    }
}
