package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.taskrouter.TaskFactory;
import com.twilio.sdk.resource.instance.taskrouter.Task;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * TaskList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Task}.
 */
public class TaskList extends NextGenListResource<Task, TwilioTaskRouterClient> implements TaskFactory {

	private String workspaceSid;

	/**
	 * Instantiates a task list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public TaskList(final TwilioTaskRouterClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a task list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public TaskList(final TwilioTaskRouterClient client, final String workspaceSid, final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	public Task create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Task create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected Task makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Task(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Tasks";
	}
}
