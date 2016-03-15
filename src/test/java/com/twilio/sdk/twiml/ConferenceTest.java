package com.twilio.sdk.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Conference}.
 */
public class ConferenceTest {

    @Test
    public void testXml() throws TwiMLException {
        Conference conference = new Conference.Builder("my conference")
            .muted(true)
            .startConferenceOnEnter(true)
            .endConferenceOnExit(false)
            .maxParticipants(10)
            .beep(Conference.Beep.FALSE)
            .record(Conference.Record.DO_NOT_RECORD)
            .trim(Conference.Trim.DO_NOT_TRIM)
            .waitMethod("POST")
            .waitMethod("http://twilio.ca")
            .build();

        Assert.assertEquals("<Conference muted=\"true\" startConferenceOnEnter=\"true\" endConferenceOnExit=\"false\" maxParticipants=\"10\" beep=\"beep!\" waitMethod=\"http://twilio.ca\">my conference</Conference>", conference.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Conference conference = new Conference.Builder("my conference")
            .muted(true)
            .startConferenceOnEnter(true)
            .endConferenceOnExit(false)
            .maxParticipants(10)
            .beep(Conference.Beep.FALSE)
            .record(Conference.Record.DO_NOT_RECORD)
            .trim(Conference.Trim.DO_NOT_TRIM)
            .waitMethod("POST")
            .waitMethod("http://twilio.ca")
            .build();

        Assert.assertEquals("%3CConference+muted%3D%22true%22+startConferenceOnEnter%3D%22true%22+endConferenceOnExit%3D%22false%22+maxParticipants%3D%2210%22+beep%3D%22FALSE%22+record%3D%22DO_NOT_RECORD%22+trim%3D%22DO_NOT_TRIM%22+waitMethod%3D%22http%3A%2F%2Ftwilio.ca%22%3Emy+conference%3C%2FConference%3E\n", conference.toUrl());
    }
}
