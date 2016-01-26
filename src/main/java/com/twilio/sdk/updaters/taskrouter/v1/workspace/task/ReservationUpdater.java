package com.twilio.sdk.updaters.taskrouter.v1.workspace.task;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.taskrouter.v1.workspace.task.Reservation;
import com.twilio.sdk.updaters.Updater;

public class ReservationUpdater extends Updater<Reservation> {
    private final String workspaceSid;
    private final String taskSid;
    private final String sid;
    private final String reservationStatus;
    private String workerActivitySid;

    /**
     * Construct a new ReservationUpdater
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     * @param sid The sid
     * @param reservationStatus The reservation_status
     */
    public ReservationUpdater(final String workspaceSid, final String taskSid, final String sid, final String reservationStatus) {
        this.workspaceSid = workspaceSid;
        this.taskSid = taskSid;
        this.sid = sid;
        this.reservationStatus = reservationStatus;
    }

    /**
     * The worker_activity_sid
     * 
     * @param workerActivitySid The worker_activity_sid
     * @return this
     */
    public ReservationUpdater setWorkerActivitySid(final String workerActivitySid) {
        this.workerActivitySid = workerActivitySid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Reservation
     */
    @Override
    public Reservation execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.TASKROUTER,
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks/" + this.taskSid + "/Reservations/" + this.sid + "",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Reservation update failed: Unable to connect to server");
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

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (reservationStatus != null) {
            request.addPostParam("ReservationStatus", reservationStatus);
        }
        
        if (workerActivitySid != null) {
            request.addPostParam("WorkerActivitySid", workerActivitySid);
        }
    }
}