package com.twilio.sdk.resource.list.taskrouter;

import com.twilio.sdk.TwilioTaskRouterClient;
import com.twilio.sdk.resource.NextGenListResource;
import com.twilio.sdk.resource.instance.taskrouter.Event;

import java.util.Map;

public class EventList extends NextGenListResource<Event, TwilioTaskRouterClient> {

    private String workspaceSid;

    /**
     * Instantiates a event list.
     *
     * @param client the client
     * @param workspaceSid the workspace sid
     */
    public EventList(final TwilioTaskRouterClient client, final String workspaceSid) {
        super(client);
        this.workspaceSid = workspaceSid;
    }

    /**
     * Instantiates a event list.
     *
     * @param client the client
     * @param workspaceSid the workspace sid
     * @param filters the filters
     */
    public EventList(final TwilioTaskRouterClient client, final String workspaceSid, final Map<String, String> filters) {
        super(client, filters);
        this.workspaceSid = workspaceSid;
    }

    @Override
    protected Event makeNew(final TwilioTaskRouterClient client, final Map<String, Object> params) {
        return new Event(client, params);
    }

    @Override
    protected String getResourceLocation() {
        return "/" + TwilioTaskRouterClient.DEFAULT_VERSION + "/Workspaces/" + workspaceSid + "/Events";
    }
}
