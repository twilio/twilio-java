package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.wds.Task;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/tasks.json");
	}

	@Test
	public void testGetTasks() throws Exception {
		setExpectedServerReturnCode(200);
		TaskList tasks = wdsClient.getTasks("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(tasks);
		for (Task task : tasks) {
			assertNotNull(task.getSid());
		}
		assertTrue(tasks.getTotal() == 1);
	}
}
