package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.wds.WorkspaceFactory;
import com.twilio.sdk.resource.instance.wds.Workspace;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * WorkspaceList to work with {@link com.twilio.sdk.resource.instance.wds.Workspace}.
 */
public class WorkspaceList extends ListResource<Workspace, TwilioWdsClient> implements WorkspaceFactory {

	/**
	 * Instantiates a workspace list.
	 *
	 * @param client the client
	 */
	public WorkspaceList(final TwilioWdsClient client) {
		super(client);
	}

	/**
	 * Instantiates a workspace list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public WorkspaceList(final TwilioWdsClient client, final Map<String, String> filters) {
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
	protected String getListKey() {
		return "workspaces";
	}

	@Override
	protected Workspace makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Workspace(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces";
	}
}
