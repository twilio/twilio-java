package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/event.json");
    }

    @Test
    public void testGetAuditEvent() throws Exception {
        setExpectedServerReturnCode(200);
        Event event = taskRouterClient.getEvent("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "EVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertNotNull(event);
        assertEquals("EVaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", event.getSid());
        assertEquals("Worker JustinWorker updated to Idle Activity", event.getDescription());
	    assertNotNull(event.getEventDate());
    }
}
