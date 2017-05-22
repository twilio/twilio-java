package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Play}.
 */
public class PlayTest {

    @Test
    public void testXml() throws TwiMLException {
        Play play = new Play.Builder("play this!")
            .digits("5w")
            .loop(3)
            .build();

        Assert.assertEquals("<Play loop=\"3\" digits=\"5w\">play this!</Play>", play.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Play play = new Play.Builder("play this!")
            .digits("5w")
            .loop(3)
            .build();

        Assert.assertEquals("%3CPlay+loop%3D%223%22+digits%3D%225w%22%3Eplay+this%21%3C%2FPlay%3E", play.toUrl());
    }
}
