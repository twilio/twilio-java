/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.supersim.v1;

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
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer
 * preview access, please contact help@twilio.com.
 */
public class SimFetcher extends Fetcher<Sim> {
    private final String pathSid;

    /**
     * Construct a new SimFetcher.
     *
     * @param pathSid The SID that identifies the resource to fetch
     */
    public SimFetcher(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Sim
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Sim fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.SUPERSIM.toString(),
            "/v1/Sims/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Sim fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Sim.fromJson(response.getStream(), client.getObjectMapper());
    }
}