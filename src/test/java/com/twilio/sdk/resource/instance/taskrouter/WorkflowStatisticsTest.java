package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class WorkflowStatisticsTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer(
				"/" + getClass().getPackage().getName().replace(".", "/") + "/workflow_statistics.json");
	}

	@Test
	public void testGetWorkflowStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		WorkflowStatistics workflowStatistics = taskRouterClient
				.getWorkflowStatistics("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "WFaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workflowStatistics);
		assertTrue(workflowStatistics.getAssignedTasks() == 1);
		assertTrue(workflowStatistics.getAverageTaskAcceptanceTime() == 0.0);
		assertNotNull(workflowStatistics.getEndTime());
		assertTrue(workflowStatistics.getLongestTaskWaitingAge() == 0);
		assertNull(workflowStatistics.getLongestTaskWaitingSid());
		assertTrue(workflowStatistics.getPendingTasks() == 0);
		assertTrue(workflowStatistics.getReservationsAccepted() == 0);
		assertTrue(workflowStatistics.getReservationsRejected() == 0);
		assertTrue(workflowStatistics.getReservationsTimedOut() == 0);
		assertTrue(workflowStatistics.getReservedTasks() == 0);
		assertNotNull(workflowStatistics.getStartTime());
		assertTrue(workflowStatistics.getTasksCanceled() == 0);
		assertTrue(workflowStatistics.getTasksEntered() == 0);
		assertTrue(workflowStatistics.getTasksMoved() == 0);
		assertTrue(workflowStatistics.getTasksTimedOutInWorkflow() == 0);
		assertTrue(workflowStatistics.getTotalTasks() == 1);
	}

}
