package com.twilio.sdk.resource.instance.wds;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class QueueStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/queue_statistics.json");
	}

	@Test
	public void testGetQueueStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		QueueStatistics queueStatistics = wdsClient
				.getQueueStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(queueStatistics);
		assertNull(queueStatistics.getLongestTaskWaitingSid());
		assertNotNull(queueStatistics.getActivityStatistics());
		assertEquals(4, queueStatistics.getActivityStatistics().size());
		assertTrue(queueStatistics.getAssignedTasks() == 1);
		assertTrue(queueStatistics.getAverageTaskAcceptanceTime() == 0d);
		assertNotNull(queueStatistics.getEndTime());
		assertTrue(queueStatistics.getLongestTaskWaitingAge() == 0);
		assertTrue(queueStatistics.getPendingTasks() == 0);
		assertTrue(queueStatistics.getReservationsAccepted() == 0);
		assertTrue(queueStatistics.getReservationsRejected() == 0);
		assertTrue(queueStatistics.getReservationsTimedOut() == 0);
		assertTrue(queueStatistics.getReservedTasks() == 0);
		assertTrue(queueStatistics.getTasksCanceled() == 0);
		assertNotNull(queueStatistics.getStartTime());
		assertTrue(queueStatistics.getTasksEntered() == 0);
		assertTrue(queueStatistics.getTasksMoved() == 0);
		assertTrue(queueStatistics.getTotalAvailableWorkers() == 0);
		assertTrue(queueStatistics.getTotalEligibleWorkers() == 3);
		assertTrue(queueStatistics.getTotalTasks() == 1);
	}

}
