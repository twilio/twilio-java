package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.AuthorizedConnectApp;

/**
 * The ConnectApps list resource shows all of the Connect Apps that you have
 * authorized within your Twilio account. The instance resource shows you the
 * permissions you have authorized for a single Connect App as well as
 * information about the Connect App itself.
 * 
 * For more information see <a
 * href="http://www.twilio.com/docs/api/rest/authorized-connect-apps"
 * >http://www.twilio.com/docs/api/rest/authorized-connect-apps</a>
 */
public class AuthorizedConnectAppList extends ListResource<AuthorizedConnectApp> {

	/**
	 * Instantiates a new AuthorizedConnectApp list.
	 * 
	 * @param client
	 *            the client
	 */
	public AuthorizedConnectAppList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new AuthorizedConnectApp list.
	 * 
	 * @param client
	 *            the client
	 * @param filters
	 *            the filters
	 */
	public AuthorizedConnectAppList(TwilioRestClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION + "/Accounts/"
				+ this.getRequestAccountSid() + "/AuthorizedConnectApps.json";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient
	 * , java.util.Map)
	 */
	@Override
	protected AuthorizedConnectApp makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new AuthorizedConnectApp(client, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "authorized_connect_apps";
	}

}
