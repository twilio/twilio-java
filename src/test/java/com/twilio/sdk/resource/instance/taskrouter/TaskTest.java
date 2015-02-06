package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/task.json");
	}

	@Test
	public void testCreateTask() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("Attributes", "{\"body\": \"hello\"}");
		properties.put("WorkflowSid", "WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		properties.put("Timeout", "60");
		Task task = taskRouterClient.createTask("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(task);
		assertEquals("{\"body\": \"hello\"}", task.getAttributes());
		assertEquals("WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", task.getWorkflowSid());
		assertEquals(60, task.getTimeout());
	}

	@Test
	public void testDeleteTask() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteTask("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetTask() throws Exception {
		setExpectedServerReturnCode(200);
		Task task = taskRouterClient.getTask("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(task);
		assertEquals("WTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", task.getSid());
		assertEquals("{\"body\": \"hello\"}", task.getAttributes());
		assertEquals("WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", task.getWorkflowSid());
	}
}
