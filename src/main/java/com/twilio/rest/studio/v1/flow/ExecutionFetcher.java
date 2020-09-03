/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.studio.v1.flow;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ExecutionFetcher extends Fetcher<Execution> {
    private final String pathFlowSid;
    private final String pathSid;

    /**
     * Construct a new ExecutionFetcher.
     *
     * @param pathFlowSid The SID of the Flow
     * @param pathSid The SID of the Execution resource to fetch
     */
    public ExecutionFetcher(final String pathFlowSid,
                            final String pathSid) {
        this.pathFlowSid = pathFlowSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Execution
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Execution fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.STUDIO.toString(),
            "/v1/Flows/" + this.pathFlowSid + "/Executions/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Execution fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Execution.fromJson(response.getStream(), client.getObjectMapper());
    }
}
