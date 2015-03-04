package com.twilio.sdk.verbs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConferenceTest {

    /**
     * Test the Conference noun with beep onEnter
     */
    @Test
    public void testConferenceBeepOnEnter() {
        Conference c = new Conference("foo_room");
        c.setBeep(Conference.BEEP_ONENTER);
        assertEquals("<Conference beep=\"onEnter\">foo_room</Conference>", c.toXML());
    }
    
    /**
     * Test the Conference noun with beep onExit
     */
    @Test
    public void testConferenceBeepOnExit() {
        Conference c = new Conference("foo_room");
        c.setBeep(Conference.BEEP_ONEXIT);
        assertEquals("<Conference beep=\"onExit\">foo_room</Conference>", c.toXML());
    }
    
    /**
     * Test the Conference noun with beep false
     */
    @Test
    public void testConferenceBeepFalse() {
        Conference c = new Conference("foo_room");
        c.setBeep(Conference.BEEP_FALSE);
        assertEquals("<Conference beep=\"false\">foo_room</Conference>", c.toXML());
    }

    /**
     * Test the Conference noun with beep false (legacy behavior)
     */
    @Test
    public void testConferenceBeepFalseLegacy() {
        Conference c = new Conference("foo_room");
        c.setBeep(false);
        assertEquals("<Conference beep=\"false\">foo_room</Conference>", c.toXML());
    }
    
    /**
     * Test the Conference noun with beep true
     */
    @Test
    public void testConferenceBeepTrue() {
        Conference c = new Conference("foo_room");
        c.setBeep(Conference.BEEP_TRUE);
        assertEquals("<Conference beep=\"true\">foo_room</Conference>", c.toXML());
    }

    /**
     * Test the Conference noun with beep true (legacy behavior)
     */
    @Test
    public void testConferenceBeepTrueLegacy() {
        Conference c = new Conference("foo_room");
        c.setBeep(true);
        assertEquals("<Conference beep=\"true\">foo_room</Conference>", c.toXML());
    }

    /**
     * Test the conference record attribute.
     */
    @Test
    public void testConferenceRecord() {
        final Conference conference = new Conference("foo_room");
        conference.setRecord(Conference.Record.RECORD_FROM_START);
        assertEquals("<Conference record=\"record-from-start\">foo_room</Conference>", conference.toXML());
    }

    /**
     * Test the conference trim attribute.
     */
    @Test
    public void testConferenceTrim() {
        final Conference conference = new Conference("foo_room");
        conference.setTrim(Conference.Trim.DO_NOT_TRIM);
        assertEquals("<Conference trim=\"do-not-trim\">foo_room</Conference>", conference.toXML());
    }

    /**
     * Test the conference eventCallbackUrl.
     */
    @Test
    public void testEventCallbackUrl() {
        final Conference conference = new Conference("foo_room");
        conference.setEventCallbackUrl("http://call.me");
        assertEquals("<Conference eventCallbackUrl=\"http://call.me\">foo_room</Conference>", conference.toXML());
    }
}
