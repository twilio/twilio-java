package com.twilio.twiml;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Client}.
 */
public class ClientTest {

    @Test
    public void testXml() throws TwiMLException {
        Client client = new Client.Builder("name")
            .method(Method.POST)
            .url("http://twilio.ca")
            .statusCallbackEvents(Lists.newArrayList(Event.ANSWERED, Event.INITIATED))
            .build();

        Assert.assertEquals("<Client method=\"POST\" url=\"http://twilio.ca\" statusCallbackEvent=\"answered initiated\">name</Client>", client.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Client client = new Client.Builder("name")
            .method(Method.POST)
            .url("http://twilio.ca")
            .build();

        Assert.assertEquals("%3CClient+method%3D%22POST%22+url%3D%22http%3A%2F%2Ftwilio.ca%22%3Ename%3C%2FClient%3E", client.toUrl());
    }
}
