package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.wds.Workflow;
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
		WorkflowList workflows = wdsClient.getWorkflows("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workflows);
		for (Workflow workflow : workflows) {
			assertNotNull(workflow.getSid());
		}
		assertTrue(workflows.getTotal() == 1);
	}
}
