package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Queue}.
 */
public class QueueTest {

    @Test
    public void testXml() throws TwiMLException {
        Queue queue = new Queue.Builder("my queue")
            .method(Method.GET)
            .postWorkActivitySid("WA124")
            .reservationSid("WR123")
            .build();

        Assert.assertEquals("<Queue method=\"GET\" reservationSid=\"WR123\" postWorkActivitySid=\"WA124\">my queue</Queue>", queue.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Queue queue = new Queue.Builder("my queue")
            .method(Method.GET)
            .postWorkActivitySid("WA124")
            .reservationSid("WR123")
            .build();

        Assert.assertEquals("%3CQueue+method%3D%22GET%22+reservationSid%3D%22WR123%22+postWorkActivitySid%3D%22WA124%22%3Emy+queue%3C%2FQueue%3E", queue.toUrl());
    }
}
