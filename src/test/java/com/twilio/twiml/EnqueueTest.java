package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Enqueue}.
 */
public class EnqueueTest {

    @Test
    public void testXml() throws TwiMLException {
        Enqueue enqueue = new Enqueue.Builder()
            .queueName("enqueue")
            .action("/enqueue")
            .method(Method.GET)
            .waitUrl("/wait")
            .waitUrlMethod(Method.POST)
            .workflowSid("WF123")
            .build();

        Assert.assertEquals("<Enqueue action=\"/enqueue\" method=\"GET\" waitUrl=\"/wait\" waitUrlMethod=\"POST\" workflowSid=\"WF123\">enqueue</Enqueue>", enqueue.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Enqueue enqueue = new Enqueue.Builder()
            .queueName("enqueue")
            .action("/enqueue")
            .method(Method.GET)
            .waitUrl("/wait")
            .waitUrlMethod(Method.POST)
            .workflowSid("WF123")
            .build();

        Assert.assertEquals("%3CEnqueue+action%3D%22%2Fenqueue%22+method%3D%22GET%22+waitUrl%3D%22%2Fwait%22+waitUrlMethod%3D%22POST%22+workflowSid%3D%22WF123%22%3Eenqueue%3C%2FEnqueue%3E", enqueue.toUrl());
    }
}
