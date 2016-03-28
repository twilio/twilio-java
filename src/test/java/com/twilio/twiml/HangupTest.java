package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Hangup}.
 */
public class HangupTest {

    @Test
    public void testXml() throws TwiMLException {
        Hangup hangup = new Hangup();
        Assert.assertEquals("<Hangup/>", hangup.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Hangup hangup = new Hangup();
        Assert.assertEquals("%3CHangup%2F%3E", hangup.toUrl());
    }
}
