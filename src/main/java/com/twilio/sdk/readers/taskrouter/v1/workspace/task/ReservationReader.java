package com.twilio.sdk.readers.taskrouter.v1.workspace.task;

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
import com.twilio.sdk.resources.taskrouter.Reservation;

public class ReservationReader extends Reader<Reservation> {
    private final String workspaceSid;
    private final String taskSid;

    /**
     * Construct a new ReservationReader
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     */
    public ReservationReader(final String workspaceSid, final String taskSid) {
        this.workspaceSid = workspaceSid;
        this.taskSid = taskSid;
    }

    /**
     * Make the request to the Twilio API to perform the read
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Reservation ResourceSet
     */
    @Override
    public ResourceSet<Reservation> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks/" + this.taskSid + "/Reservations",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Reservation> page = pageForRequest(client, request);
        
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
    public Page<Reservation> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Reservation Resources for a given request
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Reservation> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Reservation read failed: Unable to connect to server");
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
        
        Page<Reservation> result = new Page<>();
        result.deserialize("reservations", response.getContent(), Reservation.class, client.getObjectMapper());
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}