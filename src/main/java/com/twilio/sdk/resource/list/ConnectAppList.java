package com.twilio.sdk.resource.list;

import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.ConnectApp;

/**
 * The ConnectApps list resource shows all of the Connect Apps that you have
 * created within your Twilio account. The instance resource shows you the
 * permissions you are requesting for a single Connect App as well as
 * information about the Connect App itself.
 * 
 * For more information see <a
 * href="http://www.twilio.com/docs/api/rest/connect-apps"
 * >http://www.twilio.com/docs/api/rest/connect-apps</a>
 */
public class ConnectAppList extends ListResource<ConnectApp> {

	/**
	 * Instantiates a new ConnectApp list.
	 * 
	 * @param client
	 *            the client
	 */
	public ConnectAppList(TwilioRestClient client) {
		super(client);
	}

	/**
	 * Instantiates a new ConnectApp list.
	 * 
	 * @param client
	 *            the client
	 * @param filters
	 *            the filters
	 */
	public ConnectAppList(TwilioRestClient client, Map<String, String> filters) {
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
				+ this.getRequestAccountSid() + "/ConnectApps.json";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient
	 * , java.util.Map)
	 */
	@Override
	protected ConnectApp makeNew(TwilioRestClient client,
			Map<String, Object> params) {
		return new ConnectApp(client, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.twilio.sdk.resource.ListResource#getListKey()
	 */
	@Override
	protected String getListKey() {
		return "connect_apps";
	}

}
