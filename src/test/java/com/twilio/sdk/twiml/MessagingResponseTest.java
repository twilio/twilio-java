package com.twilio.sdk.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link MessagingResponse}.
 */
public class MessagingResponseTest {

    @Test
    public void testXml() throws TwiMLException {
        MessagingResponse response = new MessagingResponse.Builder()
            .redirect(new Redirect.Builder().build())
            .message(new Message.Builder().build())
            .build();

        Assert.assertEquals(
            "<MessagingResponse>" +
                "<Message method=\"POST\"/>" +
                "<Redirect method=\"POST\"/>" +
            "</MessagingResponse>", response.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        MessagingResponse response = new MessagingResponse.Builder()
            .redirect(new Redirect.Builder().build())
            .message(new Message.Builder().build())
            .build();

        Assert.assertEquals("%3CMessagingResponse%3E%3CMessage+method%3D%22POST%22%2F%3E%3CRedirect+method%3D%22POST%22%2F%3E%3C%2FMessagingResponse%3E", response.toUrl());
    }
}
