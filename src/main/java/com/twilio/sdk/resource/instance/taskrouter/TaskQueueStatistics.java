package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Statistics about task distribution.
 * See <a href="https://www.twilio.com/docs/taskrouter/taskqueue-statistics">the TaskRouter documentation</a>.
 */
public class TaskQueueStatistics extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String CUMULATIVE_PROPERTY = "cumulative";

	private static final String REALTIME_PROPERTY = "realtime";

	private static final String TASKS_BY_STATUS_PROPERTY = "tasks_by_status";

	private static final String TASK_QUEUE_SID_PROPERTY = "task_queue_sid";

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a queue statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param queueSid the queue sid
	 */
	public TaskQueueStatistics(final TwilioTaskRouterClient client, final String workspaceSid, final String queueSid) {
		this(client, workspaceSid, queueSid, null);
	}

	/**
	 * Instantiates a queue statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param queueSid the queue sid
	 * @param filters the filters
	 */
	public TaskQueueStatistics(final TwilioTaskRouterClient client, final String workspaceSid, final String queueSid,
	                           final Map<String, String> filters) {
		this(client, workspaceSid, queueSid, filters, null);
	}

	/**
	 * Instantiates a queue statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param queueSid the queue sid
	 * @param filters the filters
	 * @param properties the properties
	 */
	public TaskQueueStatistics(final TwilioTaskRouterClient client, final String workspaceSid, final String queueSid,
	                           final Map<String, String> filters, final Map<String, Object> properties) {
		super(client, properties);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for a TaskQueueStatistics cannot be null");
		}
		if (queueSid == null || "".equals(queueSid)) {
			throw new IllegalArgumentException("The queueSid for a TaskQueueStatistics cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(TASK_QUEUE_SID_PROPERTY, queueSid);
		this.filters = filters;
	}

	/**
	 * Get the activity statistics.
	 *
	 * @return the activity statistics
	 */
	public Set<ActivityStatistic> getActivityStatistics() {
		try {
			List<Map<String, Object>> props = (List<Map<String, Object>>) getRealtime().get("activity_statistics");

			Set<ActivityStatistic> activityStatistics = new HashSet<ActivityStatistic>();

			for (Map<String, Object> prop : props) {
				ActivityStatistic activityStatistic = mapToActivityStatistic(prop);
				activityStatistics.add(activityStatistic);
			}

			return Collections.unmodifiableSet(activityStatistics);
		} catch (IllegalArgumentException e) {
			return null;
		}
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
	 * Gets the queue's sid.
	 *
	 * @return the queue's sid
	 */
	public String getQueueSid() {
		return getProperty(TASK_QUEUE_SID_PROPERTY);
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
	 * Get the total number of available workers.
	 *
	 * @return the total number of available workers
	 */
	public Integer getTotalAvailableWorkers() {
		return (Integer) getRealtime().get("total_available_workers");
	}

	/**
	 * Get the total number of eligible workers.
	 *
	 * @return the total number of eligible workers
	 */
	public Integer getTotalEligibleWorkers() {
		return (Integer) getRealtime().get("total_eligible_workers");
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
	 * Gets the workspace sid.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/TaskQueues/" +
		       getQueueSid() + "/Statistics";
	}

	private Map<String, Object> getCumulative() {
		return (Map<String, Object>) getObject(CUMULATIVE_PROPERTY);
	}

	private Map<String, Object> getRealtime() {
		return (Map<String, Object>) getObject(REALTIME_PROPERTY);
	}

	private ActivityStatistic mapToActivityStatistic(final Map<String, Object> data) {
		String sid;
		String friendlyName;
		Integer workers;

		try {
			sid = (String) data.get(SID_PROPERTY);
			friendlyName = (String) data.get(ActivityStatistic.FRIENDLY_NAME_PROPERTY);
			workers = (Integer) data.get(ActivityStatistic.WORKERS_PROPERTY);
		} catch (Exception e) {
			throw new IllegalStateException("An Activity Statistic contained improperly formatted data.", e);
		}

		return new ActivityStatistic(sid, friendlyName, workers);
	}
}
