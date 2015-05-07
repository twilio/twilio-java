package com.twilio.sdk.resource.list.monitor;

import com.twilio.sdk.TwilioMonitorClient;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.Notification;
import com.twilio.sdk.resource.instance.monitor.Alert;

import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class NotificationList.
 *
 * For more information see <a href="https://www.twilio.com/docs/api/rest/alert">https://www.twilio.com/docs/api/rest/monitor-alerts</a>
 */
public class AlertList extends NextGenListResource<Alert, TwilioMonitorClient> {

	/**
	 * Instantiates a new alert list.
	 *
	 * @param client the client
	 */
	public AlertList(TwilioMonitorClient client) {
		super(client);
	}

	/**
	 * Instantiates a new alert list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public AlertList(TwilioMonitorClient client, Map<String, String> filters) {
		super(client, filters);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioMonitorClient.DEFAULT_VERSION + "/Alerts";
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.ListResource#makeNew(com.twilio.sdk.TwilioRestClient, java.util.Map)
	 */
	@Override
	protected Alert makeNew(TwilioMonitorClient client,
			Map<String, Object> params) {
		return new Alert(client, params);
	}
}
