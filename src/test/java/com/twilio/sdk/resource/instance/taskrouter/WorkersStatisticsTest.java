package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkersStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workers_statistics.json");
	}

	@Test
	public void testGetWorkersStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		WorkersStatistics workersStatistics = taskRouterClient.getWorkersStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workersStatistics);
		assertNotNull(workersStatistics.getActivityDurations());
		assertEquals(4, workersStatistics.getActivityDurations().size());
		assertNotNull(workersStatistics.getActivityStatistics());
		assertEquals(4, workersStatistics.getActivityStatistics().size());
		assertNotNull(workersStatistics.getEndTime());
		assertTrue(workersStatistics.getReservationsAccepted() == 2);
		assertTrue(workersStatistics.getReservationsRejected() == 1);
		assertTrue(workersStatistics.getReservationsTimedOut() == 1);
		assertNotNull(workersStatistics.getStartTime());
		assertTrue(workersStatistics.getTasksAssigned() == 0);
		assertTrue(workersStatistics.getTotalWorkers() == 3);
	}

}
