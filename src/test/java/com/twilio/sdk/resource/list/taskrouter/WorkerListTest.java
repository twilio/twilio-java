package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.Worker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkerListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workers.json");
	}

	@Test
	public void testGetWorkers() throws Exception {
		setExpectedServerReturnCode(200);
		WorkerList workers = taskRouterClient.getWorkers("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workers);
		for (Worker worker : workers) {
			assertNotNull(worker.getSid());
		}
	}
}
