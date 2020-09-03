/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.studio.v2.flow.execution;

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
public class ExecutionStepFetcher extends Fetcher<ExecutionStep> {
    private final String pathFlowSid;
    private final String pathExecutionSid;
    private final String pathSid;

    /**
     * Construct a new ExecutionStepFetcher.
     *
     * @param pathFlowSid The SID of the Flow
     * @param pathExecutionSid The SID of the Execution
     * @param pathSid The unique string that identifies the resource
     */
    public ExecutionStepFetcher(final String pathFlowSid,
                                final String pathExecutionSid,
                                final String pathSid) {
        this.pathFlowSid = pathFlowSid;
        this.pathExecutionSid = pathExecutionSid;
        this.pathSid = pathSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched ExecutionStep
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public ExecutionStep fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.STUDIO.toString(),
            "/v2/Flows/" + this.pathFlowSid + "/Executions/" + this.pathExecutionSid + "/Steps/" + this.pathSid + ""
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ExecutionStep fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return ExecutionStep.fromJson(response.getStream(), client.getObjectMapper());
    }
}
