package com.twilio.sdk.readers.monitor.v1;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.monitor.v1.Event;

public class EventReader extends Reader<Event> {
    private String actorSid;
    private String endDate;
    private String eventType;
    private String resourceSid;
    private String sourceIpAddress;
    private String startDate;

    /**
     * The actor_sid
     * 
     * @param actorSid The actor_sid
     * @return this
     */
    public EventReader byActorSid(final String actorSid) {
        this.actorSid = actorSid;
        return this;
    }

    /**
     * The end_date
     * 
     * @param endDate The end_date
     * @return this
     */
    public EventReader byEndDate(final String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * The event_type
     * 
     * @param eventType The event_type
     * @return this
     */
    public EventReader byEventType(final String eventType) {
        this.eventType = eventType;
        return this;
    }

    /**
     * The resource_sid
     * 
     * @param resourceSid The resource_sid
     * @return this
     */
    public EventReader byResourceSid(final String resourceSid) {
        this.resourceSid = resourceSid;
        return this;
    }

    /**
     * The source_ip_address
     * 
     * @param sourceIpAddress The source_ip_address
     * @return this
     */
    public EventReader bySourceIpAddress(final String sourceIpAddress) {
        this.sourceIpAddress = sourceIpAddress;
        return this;
    }

    /**
     * The start_date
     * 
     * @param startDate The start_date
     * @return this
     */
    public EventReader byStartDate(final String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Event ResourceSet
     */
    @Override
    public ResourceSet<Event> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.MONITOR,
            "/v1/Events",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Event> page = pageForRequest(client, request);
        
        return new ResourceSet<>(this, client, page);
    }

    /**
     * Retrieve the next page from the Twilio API
     * 
     * @param nextPageUri URI from which to retrieve the next page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Event> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Event Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Event> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Event read failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        Page<Event> result = new Page<>();
        result.deserialize("events", response.getContent(), Event.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (actorSid != null) {
            request.addQueryParam("ActorSid", actorSid);
        }
        
        if (endDate != null) {
            request.addQueryParam("EndDate", endDate);
        }
        
        if (eventType != null) {
            request.addQueryParam("EventType", eventType);
        }
        
        if (resourceSid != null) {
            request.addQueryParam("ResourceSid", resourceSid);
        }
        
        if (sourceIpAddress != null) {
            request.addQueryParam("SourceIpAddress", sourceIpAddress);
        }
        
        if (startDate != null) {
            request.addQueryParam("StartDate", startDate);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}