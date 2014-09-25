package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.wds.QueueStatistics;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueueListStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/queues_statistics.json");
	}

	@Test
	public void testGetQueuesStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		QueueListStatistics queuesStatistics = wdsClient.getQueuesStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(queuesStatistics);
		for (QueueStatistics queueStatistic : queuesStatistics) {
			assertNotNull(queueStatistic.getStartTime());
		}
		assertTrue(queuesStatistics.getTotal() == 2);
	}

}
