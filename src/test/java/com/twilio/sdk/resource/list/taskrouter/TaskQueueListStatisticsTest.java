package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueueStatistics;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskQueueListStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/task_queues_statistics.json");
	}

	@Test
	public void testGetTaskQueuesStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		TaskQueueListStatistics queuesStatistics = taskRouterClient.getQueuesStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(queuesStatistics);
		for (TaskQueueStatistics queueStatistic : queuesStatistics) {
			assertNotNull(queueStatistic.getStartTime());
		}
	}

}
