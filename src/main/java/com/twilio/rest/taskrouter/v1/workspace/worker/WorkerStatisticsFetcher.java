/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Taskrouter
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.taskrouter.v1.workspace.worker;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.time.ZonedDateTime;




/*
    * Twilio - Taskrouter
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class WorkerStatisticsFetcher extends Fetcher<WorkerStatistics> {
    private String workspaceSid;
    private String workerSid;
    private Integer minutes;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String taskChannel;

    public WorkerStatisticsFetcher(final String workspaceSid, final String workerSid){
        this.workspaceSid = workspaceSid;
        this.workerSid = workerSid;
    }

    public WorkerStatisticsFetcher setMinutes(final Integer minutes){
    this.minutes = minutes;
    return this;
    }
    public WorkerStatisticsFetcher setStartDate(final ZonedDateTime startDate){
    this.startDate = startDate;
    return this;
    }
    public WorkerStatisticsFetcher setEndDate(final ZonedDateTime endDate){
    this.endDate = endDate;
    return this;
    }
    public WorkerStatisticsFetcher setTaskChannel(final String taskChannel){
    this.taskChannel = taskChannel;
    return this;
    }

    @Override
    public WorkerStatistics fetch(final TwilioRestClient client) {
        String path = "/v1/Workspaces/{WorkspaceSid}/Workers/{WorkerSid}/Statistics";

        path = path.replace("{"+"WorkspaceSid"+"}", this.workspaceSid.toString());
        path = path.replace("{"+"WorkerSid"+"}", this.workerSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.TASKROUTER.toString(),
            path
        );
        addQueryParams(request);
        Response response = client.request(request);

        if (response == null) {
        throw new ApiConnectionException("WorkerStatistics fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return WorkerStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addQueryParams(final Request request) {
        if (minutes != null) {
    
            request.addQueryParam("Minutes", minutes.toString());
        }
        if (startDate != null) {
            request.addQueryParam("StartDate", startDate.toInstant().toString());
        }

        if (endDate != null) {
            request.addQueryParam("EndDate", endDate.toInstant().toString());
        }

        if (taskChannel != null) {
    
            request.addQueryParam("TaskChannel", taskChannel);
        }
    }
}

