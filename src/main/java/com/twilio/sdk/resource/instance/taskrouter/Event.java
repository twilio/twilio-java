package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Calendar;
import java.util.Map;

/**
 * TaskRouter logs Events for each state change in the Workspace for the purpose of historical reporting and auditing.
 *
 * See <a href="https://www.twilio.com/docs/taskrouter/events">the TaskRouter documentation</a>.
 */
public class Event extends NextGenInstanceResource<TwilioTaskRouterClient> {

    private static final String WORKSPACE_SID_PROPERTY = "workspace_sid";

    /**
     * Instantiates an event.
     *
     * @param client the client
     */
    public Event(final TwilioTaskRouterClient client) {
        super(client);
    }

    /**
     * Instantiates an event.
     *
     * @param client the client
     * @param properties the properties
     */
    public Event(final TwilioTaskRouterClient client, final Map<String, Object> properties) {
        super(client, properties);
    }

    /**
     * Instantiates an event.
     *
     * @param client the client
     * @param workspaceSid the workspace sid
     * @param eventSid the event sid
     */
    public Event(final TwilioTaskRouterClient client, final String workspaceSid, final String eventSid) {
        super(client);
        if (workspaceSid == null || "".equals(workspaceSid)) {
            throw new IllegalArgumentException("The workspaceSid for an Worker cannot be null");
        }
        if (eventSid == null || "".equals(eventSid)) {
            throw new IllegalArgumentException("The eventSid for an Event cannot be null");
        }
        setProperty(WORKSPACE_SID_PROPERTY, workspaceSid);
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
        return getProperty("actor_url");
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
     * See <a href="https://www.twilio.com/docs/taskrouter/events#event-types">the Event documentation</a>.
     *
     * @return the event data
     */
    public Map<String, String> getEventData() {
        return (Map<String, String>) getObject("event_data");
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
        return getProperty("resource_url");
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

    /**
     * Gets the workspace sid.
     *
     * @return the workspace sid
     */
    public String getWorkspaceSid() {
        String sid = getProperty(WORKSPACE_SID_PROPERTY);
        if (sid == null || "" == sid) {
            Map<String, String> eventData = getEventData();
            if (eventData == null) {
                return null;
            }
            return eventData.get(WORKSPACE_SID_PROPERTY);
        } else {
            return sid;
        }
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + getWorkspaceSid() + "/Events/" +
               getSid();
    }
}
