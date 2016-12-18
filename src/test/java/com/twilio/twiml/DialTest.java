package com.twilio.twiml;

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

        Number number = new Number.Builder("+18885551234").build();

        Dial dial = new Dial.Builder()
            .action("/dial")
            .callerId("+14155550000")
            .hangupOnStar(true)
            .method(Method.POST)
            .trim(Dial.Trim.TRIM_SILENCE)
            .timeout(8)
            .conference(conference)
            .number(number)
            .build();

        Assert.assertEquals(
            "<Dial hangupOnStar=\"true\" timeout=\"8\" action=\"/dial\" method=\"POST\" callerId=\"+14155550000\" trim=\"trim-silence\">" +
                "<Number>+18885551234</Number>" +
                "<Conference>conference</Conference>" +
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

        Assert.assertEquals("%3CDial+hangupOnStar%3D%22true%22+timeout%3D%228%22+action%3D%22%2Fdial%22+method%3D%22POST%22+callerId%3D%22%2B14155550000%22+trim%3D%22trim-silence%22%3E%3CConference%3Econference%3C%2FConference%3E%3C%2FDial%3E", dial.toUrl());
    }

    @Test
    public void testOptionsXML() throws TwiMLException {
        Conference conference = new Conference.Builder("conference")
                .build();

        Number number = new Number.Builder("+18885551234").build();

        Dial dial = new Dial.Builder()
                .action("/dial")
                .callerId("+14155550000")
                .hangupOnStar(true)
                .method(Method.POST)
                .trim(Dial.Trim.TRIM_SILENCE)
                .timeout(8)
                .conference(conference)
                .options("foo", "bar")
                .number(number)
                .build();


        Assert.assertEquals(
                "<Dial hangupOnStar=\"true\" timeout=\"8\" action=\"/dial\" method=\"POST\" callerId=\"+14155550000\" trim=\"trim-silence\" foo=\"bar\">" +
                        "<Number>+18885551234</Number>" +
                        "<Conference>conference</Conference>" +
                        "</Dial>", dial.toXml());

    }

    @Test
    public void testOptionsUrl() throws TwiMLException {
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
                .options("foo", "bar")
                .build();

        Assert.assertEquals("%3CDial+hangupOnStar%3D%22true%22+timeout%3D%228%22+action%3D%22%2Fdial%22+method%3D%22POST%22+callerId%3D%22%2B14155550000%22+trim%3D%22trim-silence%22+foo%3D%22bar%22%3E%3CConference%3Econference%3C%2FConference%3E%3C%2FDial%3E", dial.toUrl());

    }
}
