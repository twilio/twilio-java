package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.taskrouter.TaskQueueFactory;
import com.twilio.sdk.resource.instance.taskrouter.TaskQueue;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * TaskQueueList to work with {@link com.twilio.sdk.resource.instance.taskrouter.TaskQueue}.
 */
public class TaskQueueList extends NextGenListResource<TaskQueue, TwilioTaskRouterClient> implements TaskQueueFactory {

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

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/TaskQueues";
	}
}
