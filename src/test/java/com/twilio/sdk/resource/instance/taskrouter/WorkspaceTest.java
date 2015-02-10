package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkspaceTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workspace.json");
	}

	@Test
	public void testCreateWorkspace() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "workspace");
		Workspace workspace = taskRouterClient.createWorkspace(properties);
		assertNotNull(workspace);
		assertEquals("Test Workspace", workspace.getFriendlyName());
	}

	@Test
	public void testDeleteWorkspace() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetWorkspace() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		assertEquals("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", workspace.getSid());
		assertEquals("Test Workspace", workspace.getFriendlyName());
	}

}
