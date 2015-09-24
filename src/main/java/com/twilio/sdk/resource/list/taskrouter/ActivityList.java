package com.twilio.sdk.resource.list.taskrouter;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.factory.Factory;
import com.twilio.sdk.resource.instance.taskrouter.Activity;

/**
 * ActivityList to work with {@link com.twilio.sdk.resource.instance.taskrouter.Activity}.
 */
public class ActivityList extends NextGenListResource<Activity, TwilioTaskRouterClient> implements Factory<Activity> {

	private String workspaceSid;

	/**
	 * Instantiates an activity list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public ActivityList(final TwilioTaskRouterClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates an activity list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public ActivityList(final TwilioTaskRouterClient client, final String workspaceSid,
	                    final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	public Activity create(final Map<String, String> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	public Activity create(final List<NameValuePair> params) throws TwilioRestException {
		TwilioRestResponse response = getClient().safeRequest(getResourceLocation(), "POST", params);
		return makeNew(getClient(), response.toMap());
	}

	@Override
	protected Activity makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
		return new Activity(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Activities";
	}
}
