package com.twilio.sdk.fetchers.taskrouter.v1.workspace.task;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.task.Reservation;

public class ReservationFetcher extends Fetcher<Reservation> {
    private final String workspaceSid;
    private final String taskSid;
    private final String sid;

    /**
     * Construct a new ReservationFetcher
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     * @param sid The sid
     */
    public ReservationFetcher(final String workspaceSid, final String taskSid, final String sid) {
        this.workspaceSid = workspaceSid;
        this.taskSid = taskSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Reservation
     */
    @Override
    public Reservation execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks/" + this.taskSid + "/Reservations/" + this.sid + "",
            client.getAccountSid()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Reservation fetch failed: Unable to connect to server");
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
        
        return Reservation.fromJson(response.getStream(), client.getObjectMapper());
    }
}