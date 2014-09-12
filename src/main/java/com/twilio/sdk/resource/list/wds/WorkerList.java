package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.wds.WorkerFactory;
import com.twilio.sdk.resource.instance.wds.Worker;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * WorkerList to work with {@link com.twilio.sdk.resource.instance.wds.Worker}.
 */
public class WorkerList extends ListResource<Worker, TwilioWdsClient> implements WorkerFactory {

	private String workspaceSid;

	/**
	 * Instantiates a worker list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public WorkerList(final TwilioWdsClient client, final String workspaceSid) {
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
	public WorkerList(final TwilioWdsClient client, final String workspaceSid, final Map<String, String> filters) {
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

	@Override
	protected String getListKey() {
		return "workers";
	}

	@Override
	protected Worker makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Worker(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/Workers";
	}
}
