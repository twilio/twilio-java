package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.wds.QueueFactory;
import com.twilio.sdk.resource.instance.wds.Queue;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * QueueList to work with {@link com.twilio.sdk.resource.instance.wds.Queue}.
 */
public class QueueList extends ListResource<Queue, TwilioWdsClient> implements QueueFactory {

	private String workspaceSid;

	/**
	 * Instantiates a queue list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public QueueList(final TwilioWdsClient client, final String workspaceSid) {
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
	public QueueList(final TwilioWdsClient client, final String workspaceSid, final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	public Queue create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Queue create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected String getListKey() {
		return "task_queues";
	}

	@Override
	protected Queue makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Queue(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/TaskQueues";
	}
}
