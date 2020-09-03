/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.studio.v1.flow;

import com.twilio.base.Deleter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class EngagementDeleter extends Deleter<Engagement> {
    private final String pathFlowSid;
    private final String pathSid;

    /**
     * Construct a new EngagementDeleter.
     *
     * @param pathFlowSid The SID of the Flow to delete Engagements from
     * @param pathSid The SID of the Engagement resource to delete
     */
    public EngagementDeleter(final String pathFlowSid,
                             final String pathSid) {
        this.pathFlowSid = pathFlowSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the delete.
     *
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public boolean delete(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.DELETE,
            Domains.STUDIO.toString(),
            "/v1/Flows/" + this.pathFlowSid + "/Engagements/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Engagement delete failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return response.getStatusCode() == 204;
    }
}
