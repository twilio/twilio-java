/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.monitor.v1;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class AlertFetcher extends Fetcher<Alert> {
    private final String pathSid;

    /**
     * Construct a new AlertFetcher.
     *
     * @param pathSid The SID that identifies the resource to fetch
     */
    public AlertFetcher(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Alert
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Alert fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.MONITOR.toString(),
            "/v1/Alerts/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Alert fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Alert.fromJson(response.getStream(), client.getObjectMapper());
    }
}