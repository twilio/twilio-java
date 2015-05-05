package com.twilio.sdk;

import com.twilio.sdk.resource.instance.monitor.Alert;
import com.twilio.sdk.resource.instance.monitor.Event;
import com.twilio.sdk.resource.list.monitor.AlertList;
import com.twilio.sdk.resource.list.monitor.EventList;

import java.util.HashMap;
import java.util.Map;

public class TwilioMonitorClient extends TwilioClient {

	public static final String DEFAULT_VERSION = "v1";

	public TwilioMonitorClient(final String accountSid, final String authToken) {
		super(accountSid, authToken, "https://monitor.twilio.com");
	}

	public TwilioMonitorClient(final String accountSid, final String authToken, final String endpoint) {
		super(accountSid, authToken, endpoint);
	}

	/**
	 * Get an event instance by sid.
	 *
	 * @param eventSid The 34 character sid starting with AE
	 */
	public Event getEvent(final String eventSid) {
		return new Event(this, eventSid);
	}

	/**
	 * Get the events.
	 *
	 * @return the events
	 */
	public EventList getEvents() {
		return getEvents(new HashMap<String, String>(0));
	}

	/**
	 * Get the events.
	 *
	 * @param filters the filters
	 * @return events matching the filters
	 */
	public EventList getEvents(final Map<String, String> filters) {
		return new EventList(this, filters);
	}

	/**
	 * Get an Alert instance by sid.
	 * @param alertSid Alert identifier
	 * @return Alert instance
	 */
	public Alert getAlert(final String alertSid) {
		return new Alert(this, alertSid);
	}

	public AlertList getAlerts(final Map<String, String> filters) {
		return new AlertList(this, filters);
	}

	/**
	 * Get a list of Alerts for the current account.
	 * @return Alert list.
	 */
	public AlertList getAlerts() {
		return getAlerts(new HashMap<String, String>(0));
	}

}
