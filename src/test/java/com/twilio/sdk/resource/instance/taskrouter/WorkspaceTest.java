package com.twilio.sdk.resource.instance.taskrouter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.list.taskrouter.ActivityList;
import com.twilio.sdk.resource.list.taskrouter.ReservationList;
import com.twilio.sdk.resource.list.taskrouter.TaskList;
import com.twilio.sdk.resource.list.taskrouter.TaskQueueList;
import com.twilio.sdk.resource.list.taskrouter.WorkerList;
import com.twilio.sdk.resource.list.taskrouter.WorkflowList;

public class WorkspaceTest extends BasicRequestTester {

	@Before
	public void setup() throws Exception {
		setExpectedServerContentType("application/json");
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workspace.json");
	}

	@Test
	public void testCreateWorkspace() throws Exception {
		setExpectedServerReturnCode(201);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("FriendlyName", "workspace");
		Workspace workspace = taskRouterClient.createWorkspace(properties);
		assertNotNull(workspace);
		assertEquals("Test Workspace", workspace.getFriendlyName());
	}

	@Test
	public void testDeleteWorkspace() throws Exception {
		setExpectedServerAnswer(null);
		setExpectedServerReturnCode(204);
		assertTrue(taskRouterClient.deleteWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testGetWorkspace() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		assertEquals("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", workspace.getSid());
		assertEquals("Test Workspace", workspace.getFriendlyName());
	}

	@Test
	public void testGetActivities() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		String listPath = getClass().getPackage().getName().replace(".", "/").replace("instance", "list");
		
		setExpectedServerAnswer("/" + listPath + "/activities.json");
		ActivityList activities = workspace.getActivities();
		assertNotNull(activities);
		assertTrue(activities.getPageData().size() > 0);
		for (Activity activity : activities) {
			assertNotNull(activity.getSid());
		}
	}
	
	@Test
	public void testGetWorkflows() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		String listPath = getClass().getPackage().getName().replace(".", "/").replace("instance", "list");
		
		setExpectedServerAnswer("/" + listPath + "/workflows.json");
		WorkflowList workflows = workspace.getWorkflows();
		assertNotNull(workflows);
		assertTrue(workflows.getPageData().size() > 0);
		for (Workflow workflow : workflows) {
			assertNotNull(workflow.getSid());
		}
	}
	
	@Test
	public void testGetTaskQueues() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		String listPath = getClass().getPackage().getName().replace(".", "/").replace("instance", "list");
		
		setExpectedServerAnswer("/" + listPath + "/task_queues.json");
		TaskQueueList taskQueues = workspace.getTaskQueues();
		assertNotNull(taskQueues);
		assertTrue(taskQueues.getPageData().size() > 0);
		for (TaskQueue taskQueue : taskQueues) {
			assertNotNull(taskQueue.getSid());
		}
	}
	
	@Test
	public void testGetWorkers() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		String listPath = getClass().getPackage().getName().replace(".", "/").replace("instance", "list");
		
		setExpectedServerAnswer("/" + listPath + "/workers.json");
		WorkerList workers = workspace.getWorkers();
		assertNotNull(workers);
		assertTrue(workers.getPageData().size() > 0);
		for (Worker worker : workers) {
			assertNotNull(worker.getSid());
		}
	}
	
	@Test
	public void testGetTasks() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		String listPath = getClass().getPackage().getName().replace(".", "/").replace("instance", "list");
		
		setExpectedServerAnswer("/" + listPath + "/tasks.json");
		TaskList tasks = workspace.getTasks();
		assertNotNull(tasks);
		assertTrue(tasks.getPageData().size() > 0);
		setExpectedServerAnswer("/" + listPath + "/reservations.json");
		for (Task task : tasks) {
			assertNotNull(task.getSid());
			ReservationList reservations = task.getReservations();
			assertNotNull(reservations);
			assertTrue(reservations.getPageData().size() > 0);
		}
	}
	
	@Test
	public void testGetStatistics() throws Exception {
		setExpectedServerReturnCode(200);
		Workspace workspace = taskRouterClient.getWorkspace("WSaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertNotNull(workspace);
		
		setExpectedServerAnswer("/" + getClass().getPackage().getName().replace(".", "/") + "/workspace_statistics.json");
		WorkspaceStatistics stats = workspace.getStatistics();
		assertNotNull(stats);
		assertTrue(stats.getAssignedTasks() == 1);
	}

}
