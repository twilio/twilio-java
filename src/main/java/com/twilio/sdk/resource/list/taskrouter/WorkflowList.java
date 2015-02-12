package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.taskrouter.WorkflowFactory;
import com.twilio.sdk.resource.instance.taskrouter.Workflow;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * WorkflowList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Workflow}.
 */
public class WorkflowList extends NextGenListResource<Workflow, TwilioTaskRouterClient> implements WorkflowFactory {

	private String workspaceSid;

	/**
	 * Instantiates a workflow list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public WorkflowList(final TwilioTaskRouterClient client, final String workspaceSid) {
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
	public WorkflowList(final TwilioTaskRouterClient client, final String workspaceSid,
	                    final Map<String, String> filters) {
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
	protected Workflow makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Workflow(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Workflows/";
	}
}
