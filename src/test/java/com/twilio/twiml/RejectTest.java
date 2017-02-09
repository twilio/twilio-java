package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Reject}.
 */
public class RejectTest {

    @Test
    public void testXml() throws TwiMLException {
        Reject reject = new Reject.Builder()
            .reason(Reject.Reason.BUSY)
            .build();

        Assert.assertEquals("<Reject reason=\"busy\"/>", reject.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Reject reject = new Reject.Builder()
            .reason(Reject.Reason.BUSY)
            .build();

        Assert.assertEquals("%3CReject+reason%3D%22busy%22%2F%3E", reject.toUrl());
    }
}
