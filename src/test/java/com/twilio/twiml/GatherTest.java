package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Gather}.
 */
public class GatherTest {

    @Test
    public void testXml() throws TwiMLException {
        Gather gather = new Gather.Builder()
            .numDigits(4)
            .action("/gather")
            .finishOnKey("1")
            .method(Method.GET)
            .pause(new Pause.Builder().build())
            .play(new Play.Builder("Hi!").build())
            .say(new Say.Builder("Hello world!").build())
            .timeout(5)
            .hints("Hi there")
            .input("dtmf")
            .speechTimeout("auto")
            .build();

        Assert.assertEquals(
            "<Gather timeout=\"5\" numDigits=\"4\" action=\"/gather\" method=\"GET\" finishOnKey=\"1\" hints=\"Hi there\" input=\"dtmf\" speechTimeout=\"auto\">" +
                "<Pause/>" +
                "<Play>Hi!</Play>" +
                "<Say>Hello world!</Say>" +
            "</Gather>", gather.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Gather gather = new Gather.Builder()
            .numDigits(4)
            .action("/gather")
            .finishOnKey("1")
            .method(Method.GET)
            .pause(new Pause.Builder().build())
            .play(new Play.Builder("Hi!").build())
            .say(new Say.Builder("Hello world!").build())
            .timeout(5)
            .hints("Hi there")
            .input("dtmf")
            .speechTimeout(5)
            .build();

        Assert.assertEquals("%3CGather+timeout%3D%225%22+numDigits%3D%224%22+action%3D%22%2Fgather%22+method%3D%22GET%22+finishOnKey%3D%221%22+hints%3D%22Hi+there%22+input%3D%22dtmf%22+speechTimeout%3D%225%22%3E%3CPause%2F%3E%3CPlay%3EHi%21%3C%2FPlay%3E%3CSay%3EHello+world%21%3C%2FSay%3E%3C%2FGather%3E", gather.toUrl());
    }
}
