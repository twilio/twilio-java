package com.twilio.sdk.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Dial}.
 */
public class DialTest {

    @Test
    public void testXml() throws TwiMLException {
        Conference conference = new Conference.Builder("conference")
            .build();

        Dial dial = new Dial.Builder()
            .action("/dial")
            .callerId("+14155550000")
            .hangupOnStar(true)
            .method(Method.POST)
            .trim(Dial.Trim.TRIM_SILENCE)
            .timeout(8)
            .conference(conference)
            .build();

        Assert.assertEquals(
            "<Dial hangupOnStar=\"true\" timeout=\"8\" timeLimit=\"14400\" action=\"/dial\" method=\"POST\" callerId=\"+14155550000\" record=\"do-not-record\" trim=\"trim-silence\">" +
                "<Conference muted=\"false\" startConferenceOnEnter=\"true\" endConferenceOnExit=\"false\" maxParticipants=\"40\" beep=\"true\" record=\"do-not-record\" trim=\"trim-silence\" waitMethod=\"POST\">" +
                    "conference" +
                "</Conference>" +
            "</Dial>", dial.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Conference conference = new Conference.Builder("conference")
            .build();

        Dial dial = new Dial.Builder()
            .action("/dial")
            .callerId("+14155550000")
            .hangupOnStar(true)
            .method(Method.POST)
            .trim(Dial.Trim.TRIM_SILENCE)
            .timeout(8)
            .conference(conference)
            .build();

        Assert.assertEquals("%3CDial+hangupOnStar%3D%22true%22+timeout%3D%228%22+timeLimit%3D%2214400%22+action%3D%22%2Fdial%22+method%3D%22POST%22+callerId%3D%22%2B14155550000%22+record%3D%22do-not-record%22+trim%3D%22trim-silence%22%3E%3CConference+muted%3D%22false%22+startConferenceOnEnter%3D%22true%22+endConferenceOnExit%3D%22false%22+maxParticipants%3D%2240%22+beep%3D%22true%22+record%3D%22do-not-record%22+trim%3D%22trim-silence%22+waitMethod%3D%22POST%22%3Econference%3C%2FConference%3E%3C%2FDial%3E", dial.toUrl());
    }
}
