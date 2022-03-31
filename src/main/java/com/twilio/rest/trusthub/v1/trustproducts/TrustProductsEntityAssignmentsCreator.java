/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.trusthub.v1.trustproducts;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class TrustProductsEntityAssignmentsCreator extends Creator<TrustProductsEntityAssignments> {
    private final String pathTrustProductSid;
    private final String objectSid;

    /**
     * Construct a new TrustProductsEntityAssignmentsCreator.
     *
     * @param pathTrustProductSid The unique string that identifies the resource.
     * @param objectSid The sid of an object bag
     */
    public TrustProductsEntityAssignmentsCreator(final String pathTrustProductSid,
                                                 final String objectSid) {
        this.pathTrustProductSid = pathTrustProductSid;
        this.objectSid = objectSid;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created TrustProductsEntityAssignments
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public TrustProductsEntityAssignments create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TRUSTHUB.toString(),
            "/v1/TrustProducts/" + this.pathTrustProductSid + "/EntityAssignments"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("TrustProductsEntityAssignments creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return TrustProductsEntityAssignments.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (objectSid != null) {
            request.addPostParam("ObjectSid", objectSid);
        }
    }
}