package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.Workflow;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkflowListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workflows.json");
	}

	@Test
	public void testGetWorkflows() throws Exception {
		setExpectedServerReturnCode(200);
		WorkflowList workflows = taskRouterClient.getWorkflows("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workflows);
		for (Workflow workflow : workflows) {
			assertNotNull(workflow.getSid());
		}
	}
}
