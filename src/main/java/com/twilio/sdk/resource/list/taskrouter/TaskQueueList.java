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
import com.twilio.sdk.resource.instance.taskrouter.StatisticsQueryBuilder;
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
		return getStatistics(new HashMap<String, String>());
	}
	
	/**
	 * Get list of task queue statistics.
	 *
	 * @param queryBuilder query builder which contains all parameters for the stats query request
	 * @return list of task queue statistics
	 */
	public TaskQueueListStatistics getStatistics(final StatisticsQueryBuilder queryBuilder) {
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
	 * Get list of task queue statistics.
	 *
	 * @param filters the filters
	 * @return taskqueue statistics
	 */
	public TaskQueueListStatistics getStatistics(final Map<String, String> filters) {
		final String startDate = filters.get("StartDate");
		final String endDate = filters.get("EndDate");
		final String minutes = filters.get("Minutes");
		if((startDate != null || endDate != null) && minutes != null) {
			throw new IllegalArgumentException("Cannot provide Minutes in combination with StartDate or EndDate");
		}
		TaskQueueListStatistics statistics = new TaskQueueListStatistics(this.getClient(), workspaceSid, filters);
		return statistics;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/TaskQueues";
	}
}
