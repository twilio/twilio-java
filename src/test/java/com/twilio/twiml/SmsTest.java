package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Sms}.
 */
public class SmsTest {

    @Test
    public void testXml() throws TwiMLException {
        Sms sms = new Sms.Builder("bonjour")
            .from("alice")
            .to("bob")
            .action("/sendsms")
            .method(Method.POST)
            .statusCallback("http://twilio.com")
            .build();

        Assert.assertEquals("<Sms to=\"bob\" from=\"alice\" method=\"POST\" action=\"/sendsms\" statusCallback=\"http://twilio.com\">bonjour</Sms>", sms.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Sms sms = new Sms.Builder("bonjour")
            .from("alice")
            .to("bob")
            .action("/sendsms")
            .method(Method.POST)
            .statusCallback("http://twilio.com")
            .build();

        Assert.assertEquals("%3CSms+to%3D%22bob%22+from%3D%22alice%22+method%3D%22POST%22+action%3D%22%2Fsendsms%22+statusCallback%3D%22http%3A%2F%2Ftwilio.com%22%3Ebonjour%3C%2FSms%3E", sms.toUrl());
    }
}
