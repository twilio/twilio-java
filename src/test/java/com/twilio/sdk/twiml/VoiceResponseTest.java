package com.twilio.sdk.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link VoiceResponse}.
 */
public class VoiceResponseTest {

    @Test
    public void testXml() throws TwiMLException {
        VoiceResponse response = new VoiceResponse.Builder()
            .redirect(new Redirect.Builder().build())
            .dial(new Dial.Builder().build())
            .enqueue(new Enqueue.Builder("enqueue").build())
            .gather(new Gather.Builder().build())
            .hangup(new Hangup())
            .leave(new Leave())
            .pause(new Pause.Builder().build())
            .play(new Play.Builder("hola").build())
            .record(new Record.Builder().build())
            .reject(new Reject.Builder().build())
            .say(new Say.Builder("hello world").build())
            .sms(new Sms.Builder("test sms").build())
            .build();

        Assert.assertEquals(
            "<VoiceResponse>" +
                "<Dial hangupOnStar=\"false\" timeout=\"30\" timeLimit=\"14400\" method=\"POST\" record=\"do-not-record\" trim=\"trim-silence\"/>" +
                "<Enqueue method=\"POST\" waitUrl=\"http://s3.amazonaws.com/com.twilio.sounds.music/index.xml\" waitUrlMethod=\"POST\">enqueue</Enqueue>" +
                "<Gather timeout=\"5\" numDigits=\"2147483647\" method=\"POST\" finishOnKey=\"#\"/>" +
                "<Hangup/>" +
                "<Leave/>" +
                "<Pause length=\"1\"/>" +
                "<Play loop=\"1\" digits=\"0\">hola</Play>" +
                "<Record transcribe=\"false\" playBeep=\"true\" timeout=\"5\" maxLength=\"3600\" method=\"POST\" finishOnKey=\"1234567890*#\" trim=\"trim-silence\"/>" +
                "<Redirect method=\"POST\"/>" +
                "<Reject reason=\"rejected\"/>" +
                "<Say loop=\"1\" language=\"en\" voice=\"man\">hello world</Say>" +
                "<Sms method=\"POST\">test sms</Sms>" +
            "</VoiceResponse>", response.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        VoiceResponse response = new VoiceResponse.Builder()
            .redirect(new Redirect.Builder().build())
            .dial(new Dial.Builder().build())
            .enqueue(new Enqueue.Builder("enqueue").build())
            .gather(new Gather.Builder().build())
            .hangup(new Hangup())
            .leave(new Leave())
            .pause(new Pause.Builder().build())
            .play(new Play.Builder("hola").build())
            .record(new Record.Builder().build())
            .reject(new Reject.Builder().build())
            .say(new Say.Builder("hello world").build())
            .sms(new Sms.Builder("test sms").build())
            .build();

        Assert.assertEquals("%3CVoiceResponse%3E%3CDial+hangupOnStar%3D%22false%22+timeout%3D%2230%22+timeLimit%3D%2214400%22+method%3D%22POST%22+record%3D%22do-not-record%22+trim%3D%22trim-silence%22%2F%3E%3CEnqueue+method%3D%22POST%22+waitUrl%3D%22http%3A%2F%2Fs3.amazonaws.com%2Fcom.twilio.sounds.music%2Findex.xml%22+waitUrlMethod%3D%22POST%22%3Eenqueue%3C%2FEnqueue%3E%3CGather+timeout%3D%225%22+numDigits%3D%222147483647%22+method%3D%22POST%22+finishOnKey%3D%22%23%22%2F%3E%3CHangup%2F%3E%3CLeave%2F%3E%3CPause+length%3D%221%22%2F%3E%3CPlay+loop%3D%221%22+digits%3D%220%22%3Ehola%3C%2FPlay%3E%3CRecord+transcribe%3D%22false%22+playBeep%3D%22true%22+timeout%3D%225%22+maxLength%3D%223600%22+method%3D%22POST%22+finishOnKey%3D%221234567890*%23%22+trim%3D%22trim-silence%22%2F%3E%3CRedirect+method%3D%22POST%22%2F%3E%3CReject+reason%3D%22rejected%22%2F%3E%3CSay+loop%3D%221%22+language%3D%22en%22+voice%3D%22man%22%3Ehello+world%3C%2FSay%3E%3CSms+method%3D%22POST%22%3Etest+sms%3C%2FSms%3E%3C%2FVoiceResponse%3E", response.toUrl());
    }
}
