package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.wds.Queue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueueListTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/queues.json");
	}

	@Test
	public void testGetQueues() throws Exception {
		setExpectedServerReturnCode(200);
		QueueList queues = wdsClient.getQueues("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(queues);
		for (Queue queue : queues) {
			assertNotNull(queue.getSid());
		}
		assertTrue(queues.getTotal() == 1);
	}
}
