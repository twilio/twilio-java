package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * TaskQueues are the resource you use to categorize Tasks and describe which Workers are eligible to handle those Tasks.
 * As your Workflows process Tasks, those Tasks will pass through one or more TaskQueues until the Task is assigned and accepted by an eligible Worker.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/taskqueues">the TaskRouter documentation</a>.
 */
public class TaskQueue extends NextGenInstanceResource<TwilioTaskRouterClient> {

	private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

	/**
	 * Instantiates a queue.
	 *
	 * @param client the client
	 */
	public TaskQueue(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a queue.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public TaskQueue(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates a queue.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param queueSid the queue sid
	 */
	public TaskQueue(final TwilioTaskRouterClient client, final String workspaceSid, final String queueSid) {
		super(client);
		if (StringUtils.isBlank(workspaceSid)) {
			throw new IllegalArgumentException("The workspaceSid for a TaskQueue cannot be null");
		}
		if (StringUtils.isBlank(queueSid)) {
			throw new IllegalArgumentException("The queueSid for a TaskQueue cannot be null");
		}
		setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
		setProperty(SID_PROPERTY, queueSid);
	}

	/**
	 * The ID of the {@link com.twilio.sdk.resource.instance.Account} that owns this TaskQueue
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The {@link com.twilio.sdk.resource.instance.taskrouter.Activity} to assign a
	 * {@link com.twilio.sdk.resource.instance.taskrouter.Worker} when they accept a
	 * {@link com.twilio.sdk.resource.instance.taskrouter.Task} from this TaskQueue.
	 *
	 * @return the assignment activity sid
	 */
	public String getAssignmentActivitySid() {
		return getProperty("assignment_activity_sid");
	}

	/**
	 * The date and time this TaskQueue was created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return parseIsoDate(getProperty(DATE_CREATED_PROPERTY));
	}

	/**
	 * The date and time this TaskQueue was last updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		return parseIsoDate(getProperty(DATE_UPDATED_PROPERTY));
	}

	/**
	 * Human-readable description of the TaskQueue (for example “Customer Support” or “Sales”)
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return getProperty("friendly_name");
	}

	/**
	 * The FriendlyName of the {@link com.twilio.sdk.resource.instance.taskrouter.Activity} Workers will be assigned
	 * when they are reserved for a {@link com.twilio.sdk.resource.instance.taskrouter.Task} from this queue.
	 *
	 * @return the reservation activity name
	 */
	public String getReservationActivityName() {
		return getProperty("reservation_activity_name");
	}

	/**
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Activity} Workers will be assigned
	 * when they are reserved for a {@link com.twilio.sdk.resource.instance.taskrouter.Task} from this queue.
	 *
	 * @return the reservation activity sid
	 */
	public String getReservationActivitySid() {
		return getProperty("reservation_activity_sid");
	}

	/**
	 * The unique ID of this TaskQueue.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	/**
	 * The worker selection expressions for this TaskQueue.
	 *
	 * @return the target workers
	 */
	public String getTargetWorkers() {
		return getProperty("target_workers");
	}

	/**
	 * The unique ID of the {@link com.twilio.sdk.resource.instance.taskrouter.Workspace} containing this TaskQueue.
	 *
	 * @return the workspace sid
	 */
	public String getWorkspaceSid() {
		return getProperty(WORKSPACE_SID_PROPERTY);
	}
	
	/**
	 * Get taskqueue statistics.
	 * @return worker statistics
	 */
	public TaskQueueStatistics getStatistics() {
		return getStatistics(new HashMap<String, String>());
	}
	
	/**
	 * Get taskqueue statistics.
	 *
	 * @param queryBuilder query builder which contains all parameters for the stats query request
	 * @return task queue statistics
	 */
	public TaskQueueStatistics getStatistics(final StatisticsQueryBuilder queryBuilder) {
		Map<String, String> filters = new HashMap<String, String>();
		Calendar startDate = queryBuilder.getStartDate();
		Calendar endDate = queryBuilder.getEndDate();
		Integer minutes = queryBuilder.getMinutes();
		if(startDate != null) {
			filters.put("StartDate", formatCalendar(startDate));
		}
		if(endDate != null) {
			filters.put("EndDate", formatCalendar(endDate));
		}
		if(minutes != null) {
			filters.put("Minutes", minutes.toString());
		}
		return getStatistics(filters);
	}

	/**
	 * Get taskqueue statistics.
	 *
	 * @param filters the filters
	 * @return taskqueue statistics
	 */
	public TaskQueueStatistics getStatistics(final Map<String, String> filters) {
		final String startDate = filters.get("StartDate");
		final String endDate = filters.get("EndDate");
		final String minutes = filters.get("Minutes");
		if((startDate != null || endDate != null) && minutes != null) {
			throw new IllegalArgumentException("Cannot provide Minutes in combination with StartDate or EndDate");
		}
		TaskQueueStatistics statistics = new TaskQueueStatistics(this.getClient(), this.getWorkspaceSid(), this.getSid(), filters);
		return statistics;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/TaskQueues/" +
		       getSid();
	}
}
