package com.twilio.sdk.resource.list.taskrouter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.Factory;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueue;

/**
 * TaskQueueList to work with {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue}.
 */
public class TaskQueueList extends NextGenListResource<TaskQueue, TwilioTaskRouterClient> implements Factory<TaskQueue> {

	private String workspaceSid;

	/**
	 * Instantiates a queue list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public TaskQueueList(final TwilioTaskRouterClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a queue list.
	 *
	 * @param client the client
	 * @param filters the filters
	 * @param workspaceSid the workspace sid
	 */
	public TaskQueueList(final TwilioTaskRouterClient client, final String workspaceSid,
	                     final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	public TaskQueue create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public TaskQueue create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected TaskQueue makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new TaskQueue(client, params);
	}
	
	/**
	 * Get list of task queue statistics.
	 * @return list of task queue statistics
	 */
	public TaskQueueListStatistics getStatistics() {
		return getStatistics(null);
	}
	
	/**
	 * Get list of task queue statistics.
	 *
	 * @param startDate start date to query by
	 * @param endDate end date to query by
	 * @param minutes minutes to query by
	 * @return list of task queue statistics
	 */
	public TaskQueueListStatistics getStatistics(final Calendar startDate, final Calendar endDate, final Integer minutes) {
		Map<String, String> filters = new HashMap<String, String>();
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
	 * Get list of task queue statistics.
	 *
	 * @param filters the filters
	 * @return taskqueue statistics
	 */
	public TaskQueueListStatistics getStatistics(final Map<String, String> filters) {
		TaskQueueListStatistics statistics = new TaskQueueListStatistics(this.getClient(), workspaceSid, filters);
		return statistics;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/TaskQueues";
	}
}
