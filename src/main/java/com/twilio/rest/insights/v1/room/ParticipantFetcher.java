/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Insights
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.insights.v1.room;

import com.twilio.base.Fetcher;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ParticipantFetcher extends Fetcher<Participant> {

    private String pathRoomSid;
    private String pathParticipantSid;

    public ParticipantFetcher(
        final String pathRoomSid,
        final String pathParticipantSid
    ) {
        this.pathRoomSid = pathRoomSid;
        this.pathParticipantSid = pathParticipantSid;
    }

    @Override
    public Participant fetch(final TwilioRestClient client) {
        String path = "/v1/Video/Rooms/{RoomSid}/Participants/{ParticipantSid}";

        path = path.replace("{" + "RoomSid" + "}", this.pathRoomSid.toString());
        path =
            path.replace(
                "{" + "ParticipantSid" + "}",
                this.pathParticipantSid.toString()
            );

        Request request = new Request(
            HttpMethod.GET,
            Domains.INSIGHTS.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException(
                "Participant fetch failed: Unable to connect to server"
            );
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(
                response.getStream(),
                client.getObjectMapper()
            );
            if (restException == null) {
                throw new ApiException(
                    "Server Error, no content",
                    response.getStatusCode()
                );
            }
            throw new ApiException(restException);
        }

        return Participant.fromJson(
            response.getStream(),
            client.getObjectMapper()
        );
    }
}
