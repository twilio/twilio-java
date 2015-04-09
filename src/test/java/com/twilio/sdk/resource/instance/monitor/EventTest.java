package com.twilio.sdk.resource.instance.monitor;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EventTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage()
		                                        .getName()
		                                        .replace(".", "/") + "/event.json");
	}

	@Test
	public void testGetAuditEvent() throws Exception {
		setExpectedServerReturnCode(200);
		Event event = monitorClient.getEvent("AEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(event);
		assertEquals("AEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", event.getSid());
		assertNull(event.getDescription());
		assertNotNull(event.getEventDate());
		assertNotNull(event.getEventData());
		assertNotNull(event.getActorUrl());
		assertNotNull(event.getResourceUrl());
	}
}
