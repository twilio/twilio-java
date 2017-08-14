package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test class for {@link Media}.
 */
public class MediaTest {

    @Test
    public void testXml() throws TwiMLException, URISyntaxException {
        Media media = new Media.Builder(new URI("http://media.url")).build();
        Assert.assertEquals("<Media>http://media.url</Media>", media.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException, URISyntaxException {
        Media media = new Media.Builder(new URI("http://media.url")).build();
        Assert.assertEquals("%3CMedia%3Ehttp%3A%2F%2Fmedia.url%3C%2FMedia%3E", media.toUrl());
    }
}
