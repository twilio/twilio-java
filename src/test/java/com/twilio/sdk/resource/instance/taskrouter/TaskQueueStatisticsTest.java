package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TaskQueueStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/task_queue_statistics.json");
	}

	@Test
	public void testGetTaskQueueStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		TaskQueueStatistics taskQueueStatistics = taskRouterClient
				.getQueueStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WQaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(taskQueueStatistics);
		assertNull(taskQueueStatistics.getLongestTaskWaitingSid());
		assertNotNull(taskQueueStatistics.getActivityStatistics());
		assertEquals(4, taskQueueStatistics.getActivityStatistics().size());
		assertTrue(taskQueueStatistics.getAssignedTasks() == 1);
		assertTrue(taskQueueStatistics.getAverageTaskAcceptanceTime() == 0d);
		assertNotNull(taskQueueStatistics.getEndTime());
		assertTrue(taskQueueStatistics.getLongestTaskWaitingAge() == 0);
		assertTrue(taskQueueStatistics.getPendingTasks() == 0);
		assertTrue(taskQueueStatistics.getReservationsAccepted() == 0);
		assertTrue(taskQueueStatistics.getReservationsRejected() == 0);
		assertTrue(taskQueueStatistics.getReservationsTimedOut() == 0);
		assertTrue(taskQueueStatistics.getReservedTasks() == 0);
		assertTrue(taskQueueStatistics.getTasksCanceled() == 0);
		assertNotNull(taskQueueStatistics.getStartTime());
		assertTrue(taskQueueStatistics.getTasksEntered() == 0);
		assertTrue(taskQueueStatistics.getTasksMoved() == 0);
		assertTrue(taskQueueStatistics.getTotalAvailableWorkers() == 0);
		assertTrue(taskQueueStatistics.getTotalEligibleWorkers() == 3);
		assertTrue(taskQueueStatistics.getTotalTasks() == 1);
	}

}
