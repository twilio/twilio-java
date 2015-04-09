package com.twilio.sdk.resource.instance.monitor;

import com.twilio.sdk.TwilioMonitorClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Map;

/**
 * Monitor Events for each state change.
 * <p/>
 * See <a href="https://www.twilio.com/docs/api/rest/monitor">the Monitor documentation</a>.
 */
public class Event extends NextGenInstanceResource<TwilioMonitorClient> {

	/**
	 * Instantiates an event.
	 *
	 * @param client the client
	 */
	public Event(final TwilioMonitorClient client) {
		super(client);
	}

	/**
	 * Instantiates an event.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public Event(final TwilioMonitorClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	/**
	 * Instantiates an event.
	 *
	 * @param client the client
	 * @param eventSid the event sid
	 */
	public Event(final TwilioMonitorClient client, final String eventSid) {
		super(client);
		if (eventSid == null || "".equals(eventSid)) {
			throw new IllegalArgumentException("The eventSid for an Event cannot be null");
		}
		setProperty(SID_PROPERTY, eventSid);
	}

	/**
	 * The {@link com.twilio.sdk.resource.instance.Account} owning this Event.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return getProperty("account_sid");
	}

	/**
	 * The unique ID of the actor that generated this Event.
	 *
	 * @return the actor sid
	 */
	public String getActorSid() {
		return getProperty("actor_sid");
	}

	/**
	 * The type of the actor that generated this Event.
	 *
	 * @return the actor type
	 */
	public String getActorType() {
		return getProperty("actor_type");
	}

	/**
	 * The URL for the resource representing the actor that generated this Event.
	 *
	 * @return the actor url
	 */
	public String getActorUrl() {
		Map<String, String> links = (Map<String, String>) getObject("links");
		return links.get("actor");
	}

	/**
	 * A description of this event.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return getProperty("description");
	}

	/**
	 * Data about this specific event.
	 *
	 * @return the event data
	 */
	public Map<String, Map<String, Object>> getEventData() {
		return (Map<String, Map<String, Object>>) getObject("event_data");
	}

	/**
	 * The date and time this Event occurred.
	 *
	 * @return the event date
	 */
	public Calendar getEventDate() {
		return parseCalendar(getProperty("event_date"));
	}

	/**
	 * An identifier for this type of Event.
	 *
	 * @return the event type
	 */
	public String getEventType() {
		return getProperty("event_type");
	}

	/**
	 * The sid of the object this event is most relevant to (TaskSid, ReservationSid, WorkerSid)
	 *
	 * @return the resource sid
	 */
	public String getResourceSid() {
		return getProperty("resource_sid");
	}

	/**
	 * The type of object this event is most relevant to (Task, Reservation, Worker)
	 *
	 * @return the resource type
	 */
	public String getResourceType() {
		return getProperty("resource_type");
	}

	/**
	 * Gets the resource url.
	 *
	 * @return the resource url
	 */
	public String getResourceUrl() {
		Map<String, String> links = (Map<String, String>) getObject("links");
		return links.get("resource");
	}

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return getProperty(SID_PROPERTY);
	}

	public String getSource() {
		return getProperty("source");
	}

	public String getSourceIpAddress() {
		return getProperty("source_ip_address");
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioMonitorClient.DEFAULT_VERSION + "/Events/" + getSid();
	}
}
