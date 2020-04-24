/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.accounts.v1.credential;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class AwsFetcher extends Fetcher<Aws> {
    private final String pathSid;

    /**
     * Construct a new AwsFetcher.
     *
     * @param pathSid The unique string that identifies the resource
     */
    public AwsFetcher(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Aws
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Aws fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.ACCOUNTS.toString(),
            "/v1/Credentials/AWS/" + this.pathSid + "",
            client.getRegion()
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Aws fetch failed: Unable to connect to server");
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

        return Aws.fromJson(response.getStream(), client.getObjectMapper());
    }
}