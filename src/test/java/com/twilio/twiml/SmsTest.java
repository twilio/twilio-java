package com.twilio.twiml;

import com.twilio.http.HttpMethod;
import com.twilio.type.PhoneNumber;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test class for {@link Sms}.
 */
public class SmsTest {

    @Test
    public void testXml() throws TwiMLException, URISyntaxException {
        Sms sms = new Sms.Builder("bonjour")
            .from(new PhoneNumber("alice"))
            .to(new PhoneNumber("bob"))
            .action(new URI("/sendsms"))
            .method(HttpMethod.POST)
            .statusCallback(new URI("http://twilio.com"))
            .build();

        Assert.assertEquals("<Sms to=\"bob\" from=\"alice\" method=\"POST\" action=\"/sendsms\" statusCallback=\"http://twilio.com\">bonjour</Sms>", sms.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException, URISyntaxException {
        Sms sms = new Sms.Builder("bonjour")
            .from(new PhoneNumber("alice"))
            .to(new PhoneNumber("bob"))
            .action(new URI("/sendsms"))
            .method(HttpMethod.POST)
            .statusCallback(new URI("http://twilio.com"))
            .build();

        Assert.assertEquals("%3CSms+to%3D%22bob%22+from%3D%22alice%22+method%3D%22POST%22+action%3D%22%2Fsendsms%22+statusCallback%3D%22http%3A%2F%2Ftwilio.com%22%3Ebonjour%3C%2FSms%3E", sms.toUrl());
    }
}
