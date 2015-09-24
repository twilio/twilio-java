package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkerTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/worker.json");
	}

	@Test
	public void testCreateWorker() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Worker");
		Worker worker = taskRouterClient.createWorker("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(worker);
		assertEquals("Test Worker", worker.getFriendlyName());
	}
	
	@Test
	public void testCreateWorkerWithMap() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("email", "test@twilio.com");
		attributes.put("phone", "8675309");
		Worker worker = taskRouterClient.createWorker("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Test Worker", attributes, "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(worker);
		assertEquals("Test Worker", worker.getFriendlyName());
		assertEquals("{\"email\": \"test@twilio.com\", \"phone\": \"8675309\"}", worker.getAttributes());
		assertEquals(attributes, worker.parseAttributes());
		assertEquals("test@twilio.com", worker.parseAttributes().get("email"));
		assertEquals("8675309", worker.parseAttributes().get("phone"));
		assertEquals("WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", worker.getActivitySid());
	}

	@Test
	public void testDeleteWorker() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteWorker("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetWorker() throws Exception {
		setExpectedServerReturnCode(200);
		Worker worker = taskRouterClient.getWorker("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(worker);
		assertEquals("WKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", worker.getSid());
		assertEquals("Test Worker", worker.getFriendlyName());
	}
}
