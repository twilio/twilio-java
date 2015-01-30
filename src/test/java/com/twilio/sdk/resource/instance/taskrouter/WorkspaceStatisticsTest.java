package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class WorkspaceStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(
				"/" + getClass().getPackage().getName().replace(".", "/") + "/workspace_statistics.json");
	}

	@Test
	public void testGetWorkspaceStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		WorkspaceStatistics workspaceStatistics = taskRouterClient
				.getWorkspaceStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspaceStatistics);
		assertNotNull(workspaceStatistics.getActivityStatistics());
		assertEquals(4, workspaceStatistics.getActivityStatistics().size());
		assertTrue(workspaceStatistics.getAssignedTasks() == 1);
		assertTrue(workspaceStatistics.getAverageTaskAcceptanceTime() == 0.0);
		assertNotNull(workspaceStatistics.getEndTime());
		assertTrue(workspaceStatistics.getLongestTaskWaitingAge() == 0);
		assertNull(workspaceStatistics.getLongestTaskWaitingSid());
		assertTrue(workspaceStatistics.getPendingTasks() == 0);
		assertTrue(workspaceStatistics.getReservationsAccepted() == 0);
		assertTrue(workspaceStatistics.getReservationsRejected() == 0);
		assertTrue(workspaceStatistics.getReservationsTimedOut() == 0);
		assertTrue(workspaceStatistics.getReservedTasks() == 0);
		assertNotNull(workspaceStatistics.getStartTime());
		assertTrue(workspaceStatistics.getTasksCanceled() == 0);
		assertTrue(workspaceStatistics.getTasksCreated() == 0);
		assertTrue(workspaceStatistics.getTasksMoved() == 0);
		assertTrue(workspaceStatistics.getTasksTimedOutInWorkflow() == 0);
		assertTrue(workspaceStatistics.getTotalTasks() == 1);
		assertTrue(workspaceStatistics.getTotalWorkers() == 3);
	}

}
