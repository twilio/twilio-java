package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.taskrouter.WorkspaceFactory;
import com.twilio.sdk.resource.instance.taskrouter.Workspace;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * WorkspaceList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Workspace}.
 */
public class WorkspaceList extends NextGenListResource<Workspace, TwilioTaskRouterClient> implements WorkspaceFactory {

	/**
	 * Instantiates a workspace list.
	 *
	 * @param client the client
	 */
	public WorkspaceList(final TwilioTaskRouterClient client) {
		super(client);
	}

	/**
	 * Instantiates a workspace list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public WorkspaceList(final TwilioTaskRouterClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	@Override
	public Workspace create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Workspace create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected Workspace makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Workspace(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces";
	}
}
