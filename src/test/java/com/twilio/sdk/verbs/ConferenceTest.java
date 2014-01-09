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
     * Test the Conference noun with beep true
     */
    @Test
    public void testConferenceBeepTrue() {
        Conference c = new Conference("foo_room");
        c.setBeep(Conference.BEEP_TRUE);
        assertEquals("<Conference beep=\"true\">foo_room</Conference>", c.toXML());
    }
}
