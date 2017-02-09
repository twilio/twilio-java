package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Body}.
 */
public class BodyTest {

    @Test
    public void testXml() throws TwiMLException {
        Body body = new Body("This body!");
        Assert.assertEquals("<Body>This body!</Body>", body.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Body body = new Body("This body!");
        Assert.assertEquals("%3CBody%3EThis+body%21%3C%2FBody%3E", body.toUrl());
    }
}
