package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Map;

/**
 * Statistics about {@link com.twilio.sdk.resource.instance.taskrouter.Workflow}
 * See <a href="https://www.twilio.com/docs/taskrouter/workflow-statistics">the TaskRouter documentation</a>.
 */
public class WorkflowStatistics extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String CUMULATIVE_PROPERTY = "cumulative";

	private static final String REALTIME_PROPERTY = "realtime";

	private static final String TASKS_BY_STATUS_PROPERTY = "tasks_by_status";

	private static final String WORKFLOW_SID_PROPERTY = "workflow_sid";

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a workflow statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param workflowSid the workflow sid
	 */
	public WorkflowStatistics(final TwilioTaskRouterClient client, final String workspaceSid,
	                          final String workflowSid) {
		this(client, workspaceSid, workflowSid, null);
	}

	/**
	 * Instantiates a workflow statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param workflowSid the workflow sid
	 * @param filters the filters
	 */
	public WorkflowStatistics(final TwilioTaskRouterClient client, final String workspaceSid, final String workflowSid,
	                          final Map<String, String> filters) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for a WorkflowStatistics cannot be null");
		}
		if (workflowSid == null || "".equals(workflowSid)) {
			throw new IllegalArgumentException("The workflowSid for a WorkflowStatistics cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(WORKFLOW_SID_PROPERTY, workflowSid);
		this.filters = filters;
	}

	/**
	 * Get the number of assigned tasks.
	 *
	 * @return number of assigned tasks
	 */
	public Integer getAssignedTasks() {
		Map<String, Object> tasksByStatus = (Map<String, Object>) getRealtime().get(TASKS_BY_STATUS_PROPERTY);
		return (Integer) tasksByStatus.get("assigned");
	}

	/**
	 * Get the average time of task acceptance in seconds.
	 *
	 * @return the average time of task acceptance in seconds
	 */
	public Double getAverageTaskAcceptanceTime() {
		try {
			Object prop = getCumulative().get("avg_task_acceptance_time");
			if (prop instanceof Integer) {
				return Double.parseDouble(prop.toString());
			} else {
				return (Double) prop;
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Get the end time.
	 *
	 * @return the end time
	 */
	public Calendar getEndTime() {
		return parseCalendar((String) getCumulative().get("start_time"));
	}

	/**
	 * Get the longest task waiting age in seconds.
	 *
	 * @return the longest task waiting age in seconds
	 */
	public Integer getLongestTaskWaitingAge() {
		return (Integer) getRealtime().get("longest_task_waiting_age");
	}

	/**
	 * Get the sid of the longest waiting task.
	 *
	 * @return the sid of the longest waiting task
	 */
	public String getLongestTaskWaitingSid() {
		return (String) getRealtime().get("longest_task_waiting_sid");
	}

	/**
	 * Get the number of pending tasks.
	 *
	 * @return the number of pending tasks
	 */
	public Integer getPendingTasks() {
		Map<String, Object> tasksByStatus = (Map<String, Object>) getRealtime().get(TASKS_BY_STATUS_PROPERTY);
		return (Integer) tasksByStatus.get("pending");
	}

	/**
	 * Get the number of accepted reservations.
	 *
	 * @return the number of accepted reservations
	 */
	public Integer getReservationsAccepted() {
		return (Integer) getCumulative().get("reservations_accepted");
	}

	/**
	 * Get the number of rejected reservations.
	 *
	 * @return the number of rejected reservations
	 */
	public Integer getReservationsRejected() {
		return (Integer) getCumulative().get("reservations_rejected");
	}

	/**
	 * Get the number of timed out reservations.
	 *
	 * @return the number of timed out reservations
	 */
	public Integer getReservationsTimedOut() {
		return (Integer) getCumulative().get("reservations_timed_out");
	}

	/**
	 * Get the number of reserved tasks.
	 *
	 * @return the number of reserved tasks
	 */
	public Integer getReservedTasks() {
		Map<String, Object> tasksByStatus = (Map<String, Object>) getRealtime().get(TASKS_BY_STATUS_PROPERTY);
		return (Integer) tasksByStatus.get("reserved");
	}

	/**
	 * Get the start time.
	 *
	 * @return the start time
	 */
	public Calendar getStartTime() {
		return parseCalendar((String) getCumulative().get("start_time"));
	}

	/**
	 * Get the number of tasks canceled.
	 *
	 * @return the number of tasks canceled
	 */
	public Integer getTasksCanceled() {
		return (Integer) getCumulative().get("tasks_canceled");
	}

	/**
	 * Get the number of tasks entered.
	 *
	 * @return the number of tasks entered
	 */
	public Integer getTasksEntered() {
		return (Integer) getCumulative().get("tasks_entered");
	}

	/**
	 * Get the number of tasks moved.
	 *
	 * @return the number of tasks moved
	 */
	public Integer getTasksMoved() {
		return (Integer) getCumulative().get("tasks_moved");
	}

	/**
	 * Get the number of tasks that timed out in the workflow.
	 *
	 * @return the number of tasks that timed out in the workflow
	 */
	public Integer getTasksTimedOutInWorkflow() {
		return (Integer) getCumulative().get("tasks_timed_out_in_workflow");
	}

	/**
	 * Get the total number of tasks.
	 *
	 * @return the total number of tasks
	 */
	public Integer getTotalTasks() {
		return (Integer) getRealtime().get("total_tasks");
	}

	/**
	 * Gets the workflow's sid.
	 *
	 * @return the workflow's sid
	 */
	public String getWorkflowSid() {
		return getProperty(WORKFLOW_SID_PROPERTY);
	}

	/**
	 * Gets the workspace sid.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Workflows/" +
		       getWorkflowSid() + "/Statistics";
	}

	private Map<String, Object> getCumulative() {
		return (Map<String, Object>) getObject(CUMULATIVE_PROPERTY);
	}

	private Map<String, Object> getRealtime() {
		return (Map<String, Object>) getObject(REALTIME_PROPERTY);
	}
}
