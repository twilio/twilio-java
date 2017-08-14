package com.twilio.twiml;

import com.twilio.http.HttpMethod;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test class for {@link Redirect}.
 */
public class RedirectTest {

    @Test
    public void testXml() throws TwiMLException, URISyntaxException {
        Redirect redirect = new Redirect.Builder(new URI("http://twilio.ca"))
            .method(HttpMethod.GET)
            .build();

        Assert.assertEquals("<Redirect method=\"GET\">http://twilio.ca</Redirect>", redirect.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException, URISyntaxException {
        Redirect redirect = new Redirect.Builder(new URI("http://twilio.ca"))
            .method(HttpMethod.GET)
            .build();

        Assert.assertEquals("%3CRedirect+method%3D%22GET%22%3Ehttp%3A%2F%2Ftwilio.ca%3C%2FRedirect%3E", redirect.toUrl());
    }
}
