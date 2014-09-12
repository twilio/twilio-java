package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.wds.WorkflowFactory;
import com.twilio.sdk.resource.instance.wds.Workflow;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * WorkflowList to work with {@link com.twilio.sdk.resource.instance.wds.Workflow}.
 */
public class WorkflowList extends ListResource<Workflow, TwilioWdsClient> implements WorkflowFactory {

	private String workspaceSid;

	/**
	 * Instantiates a workflow list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public WorkflowList(final TwilioWdsClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a workflow list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public WorkflowList(final TwilioWdsClient client, final String workspaceSid, final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	public Workflow create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Workflow create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected String getListKey() {
		return "workflows";
	}

	@Override
	protected Workflow makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Workflow(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/Workflows/";
	}
}
