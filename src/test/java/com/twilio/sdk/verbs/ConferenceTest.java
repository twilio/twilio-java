package com.twilio.sdk.verbs;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Test Enqueuing a Task with a json string
     */
    @Test
    public void testConferenceTask() throws TwiMLException {
        Conference c = new Conference("task_room");

        String json = "{\"selected_language\":\"en\"}";
        Task task = new Task(json);
        task.setWorkflowSid("WFxxxx");

        c.append(task);

        assertEquals("<Conference>task_room<Task workflowSid=\"WFxxxx\">{\"selected_language\":\"en\"}</Task></Conference>", c.toXML());
    }

    /**
     * Test Enqueuing a Task with a map that we'll convert to a json string
     */
    @Test
    public void testConferenceTaskWithMap() throws TwiMLException {
        Conference c = new Conference("task_room");

        Map<String, String> params = new HashMap<String, String>();
        params.put("selected_language", "en");

        Task task = new Task(params);
        task.setWorkflowSid("WFxxxx");

        c.append(task);

        assertEquals("<Conference>task_room<Task workflowSid=\"WFxxxx\">{\"selected_language\":\"en\"}</Task></Conference>", c.toXML());
    }

    /**
     * Test Enqueuing a Task with a dynamic priority
     */
    @Test
    public void testConferenceTaskWithPriority() throws TwiMLException {
        Conference c = new Conference("task_room");

        Task task = new Task("");
        task.setWorkflowSid("WFxxxx");
        task.setPriority(10);

        c.append(task);

        assertEquals("<Conference>task_room<Task workflowSid=\"WFxxxx\" priority=\"10\"></Task></Conference>", c.toXML());
    }

    /**
     * Test Enqueuing a Task with a dynamic timeout
     */
    @Test
    public void testConferenceTaskWithTimeout() throws TwiMLException {
        Conference c = new Conference("task_room");

        Task task = new Task("");
        task.setWorkflowSid("WFxxxx");
        task.setTimeout(30);

        c.append(task);

        assertEquals("<Conference>task_room<Task workflowSid=\"WFxxxx\" timeout=\"30\"></Task></Conference>", c.toXML());
    }

    /**
     * Test Enqueuing a Task with json attributes, dynamic priority and timeout
     */
    @Test
    public void testConferenceTaskWithAllAttributes() throws TwiMLException {
        Conference c = new Conference("task_room");

        Map<String, String> params = new HashMap<String, String>();
        params.put("selected_language", "en");

        Task task = new Task(params);
        task.setWorkflowSid("WFxxxx");
        task.setTimeout(30);
        task.setPriority(10);

        c.append(task);

        assertEquals("<Conference>task_room<Task workflowSid=\"WFxxxx\" timeout=\"30\" priority=\"10\">{\"selected_language\":\"en\"}</Task></Conference>", c.toXML());

    }

    /**
     * Test Enqueuing a Task with different constructor with empty queue name and empty json
     */
    @Test
    public void testEnqueueTaskEmptyQueue() throws TwiMLException {
        Conference c = new Conference("task_room");

        String json = "{}";
        Task task = new Task(json);
        task.setWorkflowSid("WFxxxx");

        c.append(task);
        assertEquals("<Conference>task_room<Task workflowSid=\"WFxxxx\">{}</Task></Conference>", c.toXML());
    }

    /**
     * Test the Conference noun with reservationSid
     */
    @Test
    public void testConferenceReservationSid() {
        Conference c = new Conference("task_room");

        String reservationSid = "WRxxxx";
        c.setReservationSid(reservationSid);
        assertEquals("<Conference reservationSid=\"WRxxxx\">task_room</Conference>", c.toXML());
    }

    /**
     * Test the Conference noun with postWorkActivitySid
     */
    @Test
    public void testConferencePostWorkActivitySid() {
        Conference c = new Conference("task_room");

        String postWorkActivitySid = "WAxxxx";
        c.setPostWorkActivitySid(postWorkActivitySid);
        assertEquals("<Conference postWorkActivitySid=\"WAxxxx\">task_room</Conference>", c.toXML());
    }

    /**
     * Test the Conference noun with beep false
     */
    @Test
    public void testConferenceWithResAndPostWork() {
        Conference c = new Conference("task_room");

        String postWorkActivitySid = "WAxxxx";
        c.setPostWorkActivitySid(postWorkActivitySid);
        String reservationSid = "WRxxxx";
        c.setReservationSid(reservationSid);
        assertEquals("<Conference postWorkActivitySid=\"WAxxxx\" reservationSid=\"WRxxxx\">task_room</Conference>", c.toXML());
    }
}
