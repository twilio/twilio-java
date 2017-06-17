package com.twilio.twiml;

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
            .language(Language.GB)
            .voice(Say.Voice.MAN)
            .build();

        Assert.assertEquals("<Say loop=\"4\" language=\"en-gb\" voice=\"man\">I &lt;3 Twilio</Say>", say.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Say say = new Say.Builder("I <3 Twilio")
            .loop(4)
            .language(Language.ES)
            .voice(Say.Voice.ALICE)
            .build();

        Assert.assertEquals("%3CSay+loop%3D%224%22+language%3D%22es%22+voice%3D%22alice%22%3EI+%26lt%3B3+Twilio%3C%2FSay%3E", say.toUrl());
    }
}
