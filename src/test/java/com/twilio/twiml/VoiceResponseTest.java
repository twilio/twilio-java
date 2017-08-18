package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link VoiceResponse}.
 */
public class VoiceResponseTest {

    @Test
    public void testXml() throws TwiMLException {
        VoiceResponse response = new VoiceResponse.Builder()
            .redirect(new Redirect.Builder().build())
            .dial(new Dial.Builder().build())
            .enqueue(new Enqueue.Builder().queueName("enqueue").build())
            .gather(new Gather.Builder().build())
            .hangup(new Hangup())
            .leave(new Leave())
            .pause(new Pause.Builder().build())
            .play(new Play.Builder("hola").build())
            .record(new Record.Builder().build())
            .reject(new Reject.Builder().build())
            .say(new Say.Builder("hello world").build())
            .sms(new Sms.Builder("test sms").build())
            .say(new Say.Builder("goodbye world").build())
            .build();

        Assert.assertEquals(
            "<Response>" +
                "<Redirect/>" +
                "<Dial/>" +
                "<Enqueue>enqueue</Enqueue>" +
                "<Gather/>" +
                "<Hangup/>" +
                "<Leave/>" +
                "<Pause/>" +
                "<Play>hola</Play>" +
                "<Record/>" +
                "<Reject/>" +
                "<Say>hello world</Say>" +
                "<Sms>test sms</Sms>" +
                "<Say>goodbye world</Say>" +
            "</Response>", response.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        VoiceResponse response = new VoiceResponse.Builder()
            .redirect(new Redirect.Builder().build())
            .dial(new Dial.Builder().build())
            .enqueue(new Enqueue.Builder().queueName("enqueue").build())
            .gather(new Gather.Builder().build())
            .hangup(new Hangup())
            .leave(new Leave())
            .pause(new Pause.Builder().build())
            .play(new Play.Builder("hola").build())
            .record(new Record.Builder().build())
            .reject(new Reject.Builder().build())
            .say(new Say.Builder("hello world").build())
            .sms(new Sms.Builder("test sms").build())
            .build();

        Assert.assertEquals("%3CResponse%3E%3CRedirect%2F%3E%3CDial%2F%3E%3CEnqueue%3Eenqueue%3C%2FEnqueue%3E%3CGather%2F%3E%3CHangup%2F%3E%3CLeave%2F%3E%3CPause%2F%3E%3CPlay%3Ehola%3C%2FPlay%3E%3CRecord%2F%3E%3CReject%2F%3E%3CSay%3Ehello+world%3C%2FSay%3E%3CSms%3Etest+sms%3C%2FSms%3E%3C%2FResponse%3E", response.toUrl());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(
            new VoiceResponse.Builder().say(new Say.Builder("Transferring to sales").build()).build(),
            new VoiceResponse.Builder().say(new Say.Builder("Transferring to sales").build()).build()
        );
    }
}
