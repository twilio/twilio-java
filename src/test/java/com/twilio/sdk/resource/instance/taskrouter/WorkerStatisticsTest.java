package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkerStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/worker_statistics.json");
	}

	@Test
	public void testGetWorkerStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		WorkerStatistics workerStatistics = taskRouterClient
				.getWorkerStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WKaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workerStatistics);
		assertNotNull(workerStatistics.getActivityDurations());
		assertEquals(4, workerStatistics.getActivityDurations().size());
		assertNotNull(workerStatistics.getEndTime());
		assertTrue(workerStatistics.getReservationsAccepted() == 0);
		assertTrue(workerStatistics.getReservationsRejected() == 0);
		assertTrue(workerStatistics.getReservationsTimedOut() == 0);
		assertNotNull(workerStatistics.getStartTime());
		assertTrue(workerStatistics.getTasksAssigned() == 0);
	}

}
