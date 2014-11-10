package com.twilio.sdk.http;

import com.google.common.collect.Range;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.twilio.sdk.Assert.assertQueryStringsEqual;
import static com.twilio.sdk.Assert.assertUrlsEqual;

public class RequestTest {

    @Test
    public void testConstructURL() throws MalformedURLException, ApiException, InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParam() throws MalformedURLException, ApiException, InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithParams() throws MalformedURLException, ApiException, InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("garply", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux&garply=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithMultivaluedParam() throws MalformedURLException, ApiException,
                                                              InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz", "quux");
        r.addQueryParam("baz", "xyzzy");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz=quux&baz=xyzzy");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testConstructURLWithInequalityParam() throws MalformedURLException, ApiException,
                                                             InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryParam("baz>", "3");
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz>=3");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeLowerBound() throws MalformedURLException, ApiException, InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryDateRange("baz", Range.greaterThan(new LocalDate(2014, 1, 1)));
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz>=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeUpperBound() throws MalformedURLException, ApiException, InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryDateRange("baz", Range.lessThan(new LocalDate(2014, 1, 1)));
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz<=2014-01-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testAddQueryDateRangeClosed() throws MalformedURLException, ApiException, InvalidRequestException {
        Request r = new Request(HttpMethod.GET, "http://example.com/foobar");
        r.addQueryDateRange("baz", Range.closed(new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1)));
        URL url = r.constructURL();
        URL expected = new URL("http://example.com/foobar?baz>=2014-01-01&baz<=2014-06-01");
        assertUrlsEqual(expected, url);
    }

    @Test
    public void testEncodeFormBody() throws InvalidRequestException {
        Request r = new Request(HttpMethod.POST, "http://example.com/foobar");
        r.addPostParam("baz", "quux");
        r.addPostParam("garply", "xyzzy");
        String encoded = r.encodeFormBody();
        assertQueryStringsEqual("baz=quux&garply=xyzzy", encoded);
    }


}

