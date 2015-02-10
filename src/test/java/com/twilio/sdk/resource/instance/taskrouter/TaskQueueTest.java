package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskQueueTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/task_queue.json");
	}

	@Test
	public void testCreateTaskQueue() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test TaskQueue");
		properties.put("AssignmentActivitySid", "WAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		properties.put("ReservationActivitySid", "WRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		TaskQueue taskQueue = taskRouterClient.createTaskQueue("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(taskQueue);
		assertEquals("Test TaskQueue", taskQueue.getFriendlyName());
	}

	@Test
	public void testDeleteTaskQueue() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteTaskQueue("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetTaskQueue() throws Exception {
		setExpectedServerReturnCode(200);
		TaskQueue taskQueue = taskRouterClient.getTaskQueue("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
		                                             "WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(taskQueue);
		assertEquals("WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", taskQueue.getSid());
		assertEquals("Test TaskQueue", taskQueue.getFriendlyName());
	}

}
