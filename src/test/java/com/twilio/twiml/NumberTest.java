package com.twilio.twiml;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Number}.
 */
public class NumberTest {

    @Test
    public void testXml() throws TwiMLException {
        Number number = new Number.Builder("number")
            .method(Method.GET)
            .sendDigits("1234")
            .statusCallback("http://twilio.com")
            .statusCallbackEvents(Lists.newArrayList(Event.INITIATED))
            .statusCallbackMethod(Method.POST)
            .build();

        Assert.assertEquals("<Number sendDigits=\"1234\" method=\"GET\" statusCallbackEvent=\"initiated\" statusCallback=\"http://twilio.com\" statusCallbackMethod=\"POST\">number</Number>", number.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Number number = new Number.Builder("number")
            .method(Method.GET)
            .sendDigits("1234")
            .statusCallback("http://twilio.com")
            .statusCallbackEvents(Lists.newArrayList(Event.INITIATED))
            .statusCallbackMethod(Method.POST)
            .build();

        Assert.assertEquals("%3CNumber+sendDigits%3D%221234%22+method%3D%22GET%22+statusCallbackEvent%3D%22initiated%22+statusCallback%3D%22http%3A%2F%2Ftwilio.com%22+statusCallbackMethod%3D%22POST%22%3Enumber%3C%2FNumber%3E", number.toUrl());
    }
}
