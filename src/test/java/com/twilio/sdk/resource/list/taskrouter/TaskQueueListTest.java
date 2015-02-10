package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskQueueListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/task_queues.json");
	}

	@Test
	public void testGetTaskQueues() throws Exception {
		setExpectedServerReturnCode(200);
		TaskQueueList queues = taskRouterClient.getTaskQueues("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(queues);
		for (TaskQueue taskQueue : queues) {
			assertNotNull(taskQueue.getSid());
		}
	}
}
