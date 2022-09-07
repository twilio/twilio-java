/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Studio
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.studio.v1.flow.execution.executionstep;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;





/*
    * Twilio - Studio
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class ExecutionStepContextFetcher extends Fetcher<ExecutionStepContext> {
    private String flowSid;
    private String executionSid;
    private String stepSid;

    public ExecutionStepContextFetcher(final String flowSid, final String executionSid, final String stepSid){
        this.flowSid = flowSid;
        this.executionSid = executionSid;
        this.stepSid = stepSid;
    }


    @Override
    public ExecutionStepContext fetch(final TwilioRestClient client) {
        String path = "/v1/Flows/{FlowSid}/Executions/{ExecutionSid}/Steps/{StepSid}/Context";

        path = path.replace("{"+"FlowSid"+"}", this.flowSid.toString());
        path = path.replace("{"+"ExecutionSid"+"}", this.executionSid.toString());
        path = path.replace("{"+"StepSid"+"}", this.stepSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.STUDIO.toString(),
            path
        );
        Response response = client.request(request);

        if (response == null) {
        throw new ApiConnectionException("ExecutionStepContext fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return ExecutionStepContext.fromJson(response.getStream(), client.getObjectMapper());
    }
}

