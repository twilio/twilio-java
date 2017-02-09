/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.taskrouter.v1;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

public class WorkspaceCreator extends Creator<Workspace> {
    private final String friendlyName;
    private URI eventCallbackUrl;
    private String eventsFilter;
    private Boolean multiTaskEnabled;
    private String template;
    private Workspace.QueueOrder prioritizeQueueOrder;

    /**
     * Construct a new WorkspaceCreator.
     * 
     * @param friendlyName The friendly_name
     */
    public WorkspaceCreator(final String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * The event_callback_url.
     * 
     * @param eventCallbackUrl The event_callback_url
     * @return this
     */
    public WorkspaceCreator setEventCallbackUrl(final URI eventCallbackUrl) {
        this.eventCallbackUrl = eventCallbackUrl;
        return this;
    }

    /**
     * The event_callback_url.
     * 
     * @param eventCallbackUrl The event_callback_url
     * @return this
     */
    public WorkspaceCreator setEventCallbackUrl(final String eventCallbackUrl) {
        return setEventCallbackUrl(Promoter.uriFromString(eventCallbackUrl));
    }

    /**
     * The events_filter.
     * 
     * @param eventsFilter The events_filter
     * @return this
     */
    public WorkspaceCreator setEventsFilter(final String eventsFilter) {
        this.eventsFilter = eventsFilter;
        return this;
    }

    /**
     * The multi_task_enabled.
     * 
     * @param multiTaskEnabled The multi_task_enabled
     * @return this
     */
    public WorkspaceCreator setMultiTaskEnabled(final Boolean multiTaskEnabled) {
        this.multiTaskEnabled = multiTaskEnabled;
        return this;
    }

    /**
     * The template.
     * 
     * @param template The template
     * @return this
     */
    public WorkspaceCreator setTemplate(final String template) {
        this.template = template;
        return this;
    }

    /**
     * The prioritize_queue_order.
     * 
     * @param prioritizeQueueOrder The prioritize_queue_order
     * @return this
     */
    public WorkspaceCreator setPrioritizeQueueOrder(final Workspace.QueueOrder prioritizeQueueOrder) {
        this.prioritizeQueueOrder = prioritizeQueueOrder;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Workspace
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Workspace create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces",
            client.getRegion()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Workspace creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Workspace.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (eventCallbackUrl != null) {
            request.addPostParam("EventCallbackUrl", eventCallbackUrl.toString());
        }
        
        if (eventsFilter != null) {
            request.addPostParam("EventsFilter", eventsFilter);
        }
        
        if (multiTaskEnabled != null) {
            request.addPostParam("MultiTaskEnabled", multiTaskEnabled.toString());
        }
        
        if (template != null) {
            request.addPostParam("Template", template);
        }
        
        if (prioritizeQueueOrder != null) {
            request.addPostParam("PrioritizeQueueOrder", prioritizeQueueOrder.toString());
        }
    }
}