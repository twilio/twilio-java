package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.Event;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EventListTest extends BasicRequestTester {

    @Before
    public void setup() throws Exception {
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/events.json");
    }

    @Test
    public void testGetEvents() throws Exception {
        setExpectedServerReturnCode(200);
        EventList events = taskRouterClient.getEvents("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertNotNull(events);
        for (final Event event : events) {
            assertNotNull(event.getSid());
	        assertNotNull(event.getEventDate());
        }
    }
}
