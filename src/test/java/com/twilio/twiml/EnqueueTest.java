package com.twilio.twiml;

import com.twilio.http.HttpMethod;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test class for {@link Enqueue}.
 */
public class EnqueueTest {

    @Test
    public void testXml() throws TwiMLException, URISyntaxException {
        Enqueue enqueue = new Enqueue.Builder()
            .name("enqueue")
            .action(new URI("/enqueue"))
            .method(HttpMethod.GET)
            .waitUrl(new URI("/wait"))
            .waitUrlMethod(HttpMethod.POST)
            .workflowSid("WF123")
            .build();

        Assert.assertEquals("<Enqueue action=\"/enqueue\" method=\"GET\" waitUrl=\"/wait\" waitUrlMethod=\"POST\" workflowSid=\"WF123\">enqueue</Enqueue>", enqueue.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException, URISyntaxException {
        Enqueue enqueue = new Enqueue.Builder()
            .name("enqueue")
            .action(new URI("/enqueue"))
            .method(HttpMethod.GET)
            .waitUrl(new URI("/wait"))
            .waitUrlMethod(HttpMethod.POST)
            .workflowSid("WF123")
            .build();

        Assert.assertEquals("%3CEnqueue+action%3D%22%2Fenqueue%22+method%3D%22GET%22+waitUrl%3D%22%2Fwait%22+waitUrlMethod%3D%22POST%22+workflowSid%3D%22WF123%22%3Eenqueue%3C%2FEnqueue%3E", enqueue.toUrl());
    }
}
