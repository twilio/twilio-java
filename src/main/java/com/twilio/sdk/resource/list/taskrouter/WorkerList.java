package com.twilio.sdk.resource.list.taskrouter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.simple.JSONObject;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.Factory;
import com.twilio.sdk.resource.instance.taskrouter.StatisticsQueryBuilder;
import com.twilio.sdk.resource.instance.taskrouter.Worker;
import com.twilio.sdk.resource.instance.taskrouter.WorkersStatistics;

/**
 * WorkerList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Worker}.
 */
public class WorkerList extends NextGenListResource<Worker, TwilioTaskRouterClient> implements Factory<Worker> {

	private String workspaceSid;

	/**
	 * Instantiates a worker list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public WorkerList(final TwilioTaskRouterClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a worker list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public WorkerList(final TwilioTaskRouterClient client, final String workspaceSid,
			final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	public Worker create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Worker create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	public Worker create(final String friendlyName, final Map<String, String> attributes, final String activitySid) throws TwilioRestException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("FriendlyName", friendlyName);

		if(attributes != null) {
			params.put("Attributes", JSONObject.toJSONString(attributes));
		}else {
			params.put("Attributes", "{}");
		}
		if(activitySid != null) {
			params.put("ActivitySid", activitySid);
		}

		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected Worker makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Worker(client, params);
	}
	
	/**
	 * Get workers statistics
	 * @return workers statistics
	 */
	public WorkersStatistics getStatistics() {
		return getStatistics(new HashMap<String, String>());
	}

	/**
	 * Get workers statistics.
	 *
	 * @param queryBuilder query builder which contains all parameters for the stats query request
	 * @return list of task queue statistics
	 */
	public WorkersStatistics getStatistics(final StatisticsQueryBuilder queryBuilder) {
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
	 * Get the worker statistics
	 *
	 * @param filters the filters
	 * @return workers statistics
	 */
	public WorkersStatistics getStatistics(final Map<String, String> filters) {
		final String startDate = filters.get("StartDate");
		final String endDate = filters.get("EndDate");
		final String minutes = filters.get("Minutes");
		if((startDate != null || endDate != null) && minutes != null) {
			throw new IllegalArgumentException("Cannot provide Minutes in combination with StartDate or EndDate");
		}
		WorkersStatistics statistics = new WorkersStatistics(this.getClient(), workspaceSid, filters);
		return statistics;
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Workers";
	}
}
