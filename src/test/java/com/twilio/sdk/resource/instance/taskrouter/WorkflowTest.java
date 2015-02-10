package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkflowTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workflow.json");
	}

	@Test
	public void testCreateWorkflow() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "Test Workflow");
		properties.put("Configuration",
		               "task-routing:\\n  - filter: \\n      - 1 == 1\\n    target:\\n      - queue: WQ6cea7d9a599fadac8511e9fd2d1b7d10\\n        set-priority: 0\\n");
		properties.put("AssignmentCallbackUrl", "http://example.com");
		Workflow workflow = taskRouterClient.createWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", properties);
		assertNotNull(workflow);
		assertEquals("Default Fifo Workflow", workflow.getFriendlyName());
		assertEquals(
				"task-routing:\\n  - filter: \\n      - 1 == 1\\n    target:\\n      - queue: WQ6cea7d9a599fadac8511e9fd2d1b7d10\\n        set-priority: 0\\n",
				workflow.getConfiguration());
		assertEquals("http://example.com", workflow.getAssignmentCallbackUrl());
	}

	@Test
	public void testDeleteWorkflow() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetWorkflow() throws Exception {
		setExpectedServerReturnCode(200);
		Workflow workflow                 = taskRouterClient.getWorkflow("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workflow);
		assertEquals("WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", workflow.getSid());
		assertEquals("Default Fifo Workflow", workflow.getFriendlyName());
	}
}
