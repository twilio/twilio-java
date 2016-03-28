package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Leave}.
 */
public class LeaveTest {

    @Test
    public void testXml() throws TwiMLException {
        Leave leave = new Leave();
        Assert.assertEquals("<Leave/>", leave.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Leave leave = new Leave();
        Assert.assertEquals("%3CLeave%2F%3E", leave.toUrl());
    }

}
