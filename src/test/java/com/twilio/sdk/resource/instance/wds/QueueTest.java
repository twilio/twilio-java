package com.twilio.sdk.resource.instance.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueueTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/queue.json");
	}

	@Test
	public void testCreateQueue() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Queue");
		properties.put("AssignmentActivitySid", "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		properties.put("ReservationActivitySid", "WRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Queue queue = wdsClient.createQueue("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(queue);
		assertEquals("Test Queue", queue.getFriendlyName());
	}

	@Test
	public void testDeleteQueue() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(wdsClient.deleteQueue("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetQueue() throws Exception {
		setExpectedServerReturnCode(200);
		Queue queue = wdsClient.getQueue("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(queue);
		assertEquals("WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", queue.getSid());
		assertEquals("Test Queue", queue.getFriendlyName());
	}

}
