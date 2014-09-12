package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.factory.wds.ActivityFactory;
import com.twilio.sdk.resource.instance.wds.Activity;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * ActivityList to work with {@link com.twilio.sdk.resource.instance.wds.Activity}.
 */
public class ActivityList extends ListResource<Activity, TwilioWdsClient> implements ActivityFactory {

	private String workspaceSid;

	/**
	 * Instantiates an activity list.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public ActivityList(final TwilioWdsClient client, final String workspaceSid) {
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
	public ActivityList(final TwilioWdsClient client, final String workspaceSid, final Map<String, String> filters) {
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
	protected String getListKey() {
		return "activities";
	}

	@Override
	protected Activity makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		return new Activity(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/Activities";
	}
}
