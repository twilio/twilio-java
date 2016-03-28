package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Redirect}.
 */
public class RedirectTest {

    @Test
    public void testXml() throws TwiMLException {
        Redirect redirect = new Redirect.Builder()
            .method(Method.GET)
            .url("http://twilio.ca")
            .build();

        Assert.assertEquals("<Redirect method=\"GET\">http://twilio.ca</Redirect>", redirect.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Redirect redirect = new Redirect.Builder()
            .method(Method.GET)
            .url("http://twilio.ca")
            .build();

        Assert.assertEquals("%3CRedirect+method%3D%22GET%22%3Ehttp%3A%2F%2Ftwilio.ca%3C%2FRedirect%3E", redirect.toUrl());
    }
}
