package com.twilio.sdk.resource.list.monitor;

import com.twilio.sdk.TwilioMonitorClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.monitor.Event;

import java.util.Map;

public class EventList extends NextGenListResource<Event, TwilioMonitorClient> {

	/**
	 * Instantiates a event list.
	 *
	 * @param client the client
	 */
	public EventList(final TwilioMonitorClient client) {
		super(client);
	}

	/**
	 * Instantiates a event list.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public EventList(final TwilioMonitorClient client, final Map<String, String> filters) {
		super(client, filters);
	}

	@Override
	protected Event makeNew(final TwilioMonitorClient client, final Map<String, Object> params) {
		return new Event(client, params);
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioMonitorClient.DEFAULT_VERSION + "/Events";
	}
}
