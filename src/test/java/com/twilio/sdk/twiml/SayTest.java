package com.twilio.sdk.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Say}.
 */
public class SayTest {

    @Test
    public void testXml() throws TwiMLException {
        Say say = new Say.Builder("I <3 Twilio")
            .loop(4)
            .language("English")
            .voice("Morgan Freeman")
            .build();

        Assert.assertEquals("<Say loop=\"4\" language=\"English\" voice=\"Morgan Freeman\">I &lt;3 Twilio</Say>", say.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Say say = new Say.Builder("I <3 Twilio")
            .loop(4)
            .language("English")
            .voice("Morgan Freeman")
            .build();

        Assert.assertEquals("%3CSay+loop%3D%224%22+language%3D%22English%22+voice%3D%22Morgan+Freeman%22%3EI+%26lt%3B3+Twilio%3C%2FSay%3E", say.toUrl());
    }
}
