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
 * Statistics about workers.
 * See <a href="https://www.twilio.com/docs/taskrouter/worker-statistics">the TaskRouter documentation</a>.
 */
public class WorkersStatistics extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String CUMULATIVE_PROPERTY = "cumulative";

	private static final String REALTIME_PROPERTY = "realtime";

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a worker statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public WorkersStatistics(final TwilioTaskRouterClient client, final String workspaceSid) {
		this(client, workspaceSid, null);
	}

	/**
	 * Instantiates a worker statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public WorkersStatistics(final TwilioTaskRouterClient client, final String workspaceSid,
	                         final Map<String, String> filters) {
		super(client);
		if (workspaceSid == null || "".equals(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for a WorkerStatistics cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		this.filters = filters;
	}

	/**
	 * Get the activity durations.
	 *
	 * @return the activity durations
	 */
	public Set<ActivityDuration> getActivityDurations() {
		try {
			List<Map<String, Object>> props = (List<Map<String, Object>>) getCumulative().get("activity_durations");

			Set<ActivityDuration> activityDurations = new HashSet<ActivityDuration>();

			for (Map<String, Object> prop : props) {
				ActivityDuration activityDuration = mapToActivityDuration(prop);
				activityDurations.add(activityDuration);
			}

			return Collections.unmodifiableSet(activityDurations);
		} catch (IllegalArgumentException e) {
			return null;
		}
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
	 * Get the end time.
	 *
	 * @return the end time
	 */
	public Calendar getEndTime() {
		return parseCalendar((String) getCumulative().get("start_time"));
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
	 * Get the start time.
	 *
	 * @return the start time
	 */
	public Calendar getStartTime() {
		return parseCalendar((String) getCumulative().get("start_time"));
	}

	/**
	 * Get the number of tasks assigned.
	 *
	 * @return the number of tasks assigned
	 */
	public Integer getTasksAssigned() {
		return (Integer) getCumulative().get("tasks_assigned");
	}

	/**
	 * Get the number of workers.
	 *
	 * @return the number of workers
	 */
	public Integer getTotalWorkers() {
		return (Integer) getRealtime().get("total_workers");
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
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Workers/Statistics";
	}

	private Map<String, Object> getCumulative() {
		return (Map<String, Object>) getObject(CUMULATIVE_PROPERTY);
	}

	private Map<String, Object> getRealtime() {
		return (Map<String, Object>) getObject(REALTIME_PROPERTY);
	}

	private ActivityDuration mapToActivityDuration(final Map<String, Object> data) {
		String sid;
		String friendlyName;
		Double average;
		Integer maximum;
		Integer minimum;

		try {
			sid = (String) data.get(SID_PROPERTY);
			friendlyName = (String) data.get(ActivityDuration.FRIENDLY_NAME_PROPERTY);
			maximum = (Integer) data.get(ActivityDuration.MAXIMUM_PROPERTY);
			minimum = (Integer) data.get(ActivityDuration.MINIMUM_PROPERTY);
			try {
				Object prop = getObject(ActivityDuration.AVERAGE_PROPERTY);
				if (prop instanceof Integer) {
					average =  Double.parseDouble(prop.toString());
				} else {
					average = (Double) prop;
				}
			} catch (IllegalArgumentException e) {
				average = null;
			}
		} catch (Exception e) {
			throw new IllegalStateException("An Activity Duration contained improperly formatted data.", e);
		}

		return new ActivityDuration(sid, friendlyName, average, maximum, minimum);
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
