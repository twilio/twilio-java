package com.twilio.sdk.resource.instance.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.util.Date;
import java.util.Map;

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
     * Gets the account sid
     *
     * @return the account sid
     */
    public String getAccountSid() {
        return getProperty("account_sid");
    }

    /**
     * Gets the actor sid.
     *
     * @return the actor sid
     */
    public String getActorSid() {
        return getProperty("actor_sid");
    }

    /**
     * Gets the actor type.
     *
     * @return the actor type
     */
    public String getActorType() {
        return getProperty("actor_type");
    }

    /**
     * Gets the actor url.
     *
     * @return the actor url
     */
    public String getActorUrl() {
        return getProperty("actor_url");
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return getProperty("description");
    }

    /**
     * Gets the event data.
     *
     * @return the event data
     */
    public Map<String, String> getEventData() {
        return (Map<String, String>) getObject("event_data");
    }

    /**
     * Gets the event date.
     *
     * @return the event date
     */
    public Date getEventDate() {
        return parseIso8601Date(getProperty("event_date"));
    }

    /**
     * Gets the event type.
     *
     * @return the event type
     */
    public String getEventType() {
        return getProperty("event_type");
    }

    /**
     * Gets the resource sid.
     *
     * @return the resource sid
     */
    public String getResourceSid() {
        return getProperty("resource_sid");
    }

    /**
     * Gets the resource type.
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
