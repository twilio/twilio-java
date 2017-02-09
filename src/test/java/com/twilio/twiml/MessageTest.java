package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Message}.
 */
public class MessageTest {

    @Test
    public void testXml() throws TwiMLException {
        Message message = new Message.Builder()
            .media(new Media("http://media.url"))
            .action("/action")
            .from("Dr. Nick")
            .to("Everybody")
            .method(Method.GET)
            .statusCallback("http://twilio.ca")
            .build();

        Assert.assertEquals("" +
            "<Message to=\"Everybody\" from=\"Dr. Nick\" method=\"GET\" action=\"/action\" statusCallback=\"http://twilio.ca\">" +
                "<Media>http://media.url</Media>" +
            "</Message>", message.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Message message = new Message.Builder()
            .media(new Media("http://media.url"))
            .action("/action")
            .from("Dr. Nick")
            .to("Everybody")
            .method(Method.GET)
            .statusCallback("http://twilio.ca")
            .build();

        Assert.assertEquals("%3CMessage+to%3D%22Everybody%22+from%3D%22Dr.+Nick%22+method%3D%22GET%22+action%3D%22%2Faction%22+statusCallback%3D%22http%3A%2F%2Ftwilio.ca%22%3E%3CMedia%3Ehttp%3A%2F%2Fmedia.url%3C%2FMedia%3E%3C%2FMessage%3E", message.toUrl());
    }
}
