/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ParticipantUpdater extends Updater<Participant> {
    private final String pathRoomSid;
    private final String pathSid;
    private Participant.Status status;

    /**
     * Construct a new ParticipantUpdater.
     *
     * @param pathRoomSid The SID of the room with the participant to update
     * @param pathSid The SID that identifies the resource to update
     */
    public ParticipantUpdater(final String pathRoomSid,
                              final String pathSid) {
        this.pathRoomSid = pathRoomSid;
        this.pathSid = pathSid;
    }

    /**
     * The new status of the resource. Can be: `connected` or `disconnected`. For
     * `in-progress` Rooms the default Status is `connected`, for `completed` Rooms
     * only `disconnected` Participants are returned..
     *
     * @param status The new status of the resource
     * @return this
     */
    public ParticipantUpdater setStatus(final Participant.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Participant
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Participant update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VIDEO.toString(),
            "/v1/Rooms/" + this.pathRoomSid + "/Participants/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant update failed: Unable to connect to server");
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
                restException.getDetails(),
                null
            );
        }

        return Participant.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (status != null) {
            request.addPostParam("Status", status.toString());
        }
    }
}