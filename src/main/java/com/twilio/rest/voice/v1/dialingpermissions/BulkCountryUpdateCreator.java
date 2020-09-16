/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.voice.v1.dialingpermissions;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
public class BulkCountryUpdateCreator extends Creator<BulkCountryUpdate> {
    private final String updateRequest;

    /**
     * Construct a new BulkCountryUpdateCreator.
     *
     * @param updateRequest URL encoded JSON array of update objects
     */
    public BulkCountryUpdateCreator(final String updateRequest) {
        this.updateRequest = updateRequest;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created BulkCountryUpdate
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public BulkCountryUpdate create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VOICE.toString(),
            "/v1/DialingPermissions/BulkCountryUpdates"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("BulkCountryUpdate creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return BulkCountryUpdate.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (updateRequest != null) {
            request.addPostParam("UpdateRequest", updateRequest);
        }
    }
}