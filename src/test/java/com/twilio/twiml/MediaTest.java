package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Media}.
 */
public class MediaTest {

    @Test
    public void testXml() throws TwiMLException {
        Media media = new Media("http://media.url");
        Assert.assertEquals("<Media>http://media.url</Media>", media.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Media media = new Media("http://media.url");
        Assert.assertEquals("%3CMedia%3Ehttp%3A%2F%2Fmedia.url%3C%2FMedia%3E", media.toUrl());
    }
}
