package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.wds.TaskFactory;
import com.twilio.sdk.resource.instance.wds.Task;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * TaskList to work with {@link com.twilio.sdk.resource.instance.wds.Task}.
 */
public class TaskList extends ListResource<Task, TwilioWdsClient> implements TaskFactory {

	private String workspaceSid;

	/**
	 * Instantiates a task list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public TaskList(final TwilioWdsClient client, final String workspaceSid) {
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
	public TaskList(final TwilioWdsClient client, final String workspaceSid, final Map<String, String> filters) {
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
	protected String getListKey() {
		return "tasks";
	}

	@Override
	protected Task makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Task(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/Tasks";
	}
}
