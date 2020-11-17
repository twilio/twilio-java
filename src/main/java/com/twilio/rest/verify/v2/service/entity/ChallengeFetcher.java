/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service.entity;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ChallengeFetcher extends Fetcher<Challenge> {
    private final String pathServiceSid;
    private final String pathIdentity;
    private final String pathSid;

    /**
     * Construct a new ChallengeFetcher.
     *
     * @param pathServiceSid Service Sid.
     * @param pathIdentity Unique external identifier of the Entity
     * @param pathSid A string that uniquely identifies this Challenge.
     */
    public ChallengeFetcher(final String pathServiceSid,
                            final String pathIdentity,
                            final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathIdentity = pathIdentity;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Challenge
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Challenge fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VERIFY.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Entities/" + this.pathIdentity + "/Challenges/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Challenge fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Challenge.fromJson(response.getStream(), client.getObjectMapper());
    }
}