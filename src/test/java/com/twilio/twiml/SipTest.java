package com.twilio.twiml;

import com.google.common.collect.Lists;
import com.twilio.http.HttpMethod;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test class for {@link Sip}.
 */
public class SipTest {

    @Test
    public void testXml() throws TwiMLException, URISyntaxException {
        Sip sip = new Sip.Builder(new URI("sippy"))
            .method(HttpMethod.GET)
            .password("hunter2")
            .statusCallback(new URI("http://twilio.com"))
            .statusCallbackEvent(Lists.newArrayList(Sip.Event.ANSWERED))
            .statusCallbackMethod(HttpMethod.POST)
            .url(new URI("http://twilio.ca"))
            .username("johnny")
            .build();

        Assert.assertEquals("<Sip username=\"johnny\" password=\"hunter2\" url=\"http://twilio.ca\" method=\"GET\" statusCallbackEvent=\"answered\" statusCallback=\"http://twilio.com\" statusCallbackMethod=\"POST\">sippy</Sip>", sip.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException, URISyntaxException {
        Sip sip = new Sip.Builder(new URI("sippy"))
            .method(HttpMethod.GET)
            .password("hunter2")
            .statusCallback(new URI("http://twilio.com"))
            .statusCallbackEvent(Lists.newArrayList(Sip.Event.ANSWERED))
            .statusCallbackMethod(HttpMethod.POST)
            .url(new URI("http://twilio.ca"))
            .username("johnny")
            .build();

        Assert.assertEquals("%3CSip+username%3D%22johnny%22+password%3D%22hunter2%22+url%3D%22http%3A%2F%2Ftwilio.ca%22+method%3D%22GET%22+statusCallbackEvent%3D%22answered%22+statusCallback%3D%22http%3A%2F%2Ftwilio.com%22+statusCallbackMethod%3D%22POST%22%3Esippy%3C%2FSip%3E", sip.toUrl());
    }
}
