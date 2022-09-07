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

package com.twilio.rest.studio.v1.flow;

import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import com.twilio.base.Page;
import java.time.ZonedDateTime;





/*
    * Twilio - Studio
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.35.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class ExecutionReader extends Reader<Execution> {
    private String flowSid;
    private ZonedDateTime dateCreatedFrom;
    private ZonedDateTime dateCreatedTo;
    private Integer pageSize;

    public ExecutionReader(final String flowSid){
        this.flowSid = flowSid;
    }

    public ExecutionReader setDateCreatedFrom(final ZonedDateTime dateCreatedFrom){
        this.dateCreatedFrom = dateCreatedFrom;
        return this;
    }
    public ExecutionReader setDateCreatedTo(final ZonedDateTime dateCreatedTo){
        this.dateCreatedTo = dateCreatedTo;
        return this;
    }
    public ExecutionReader setPageSize(final Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Execution> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Execution> firstPage(final TwilioRestClient client) {
        String path = "/v1/Flows/{FlowSid}/Executions";
        path = path.replace("{"+"FlowSid"+"}", this.flowSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.STUDIO.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<Execution> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Execution read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "executions",
            response.getContent(),
            Execution.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Execution> previousPage(final Page<Execution> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.STUDIO.toString())
        );
        return pageForRequest(client, request);
    }


    @Override
    public Page<Execution> nextPage(final Page<Execution> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.STUDIO.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Execution> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }
    private void addQueryParams(final Request request) {
        if (dateCreatedFrom != null) {
            request.addQueryParam("DateCreatedFrom", dateCreatedFrom.toInstant().toString());
        }

        if (dateCreatedTo != null) {
            request.addQueryParam("DateCreatedTo", dateCreatedTo.toInstant().toString());
        }

        if (pageSize != null) {
    
            request.addQueryParam("PageSize", pageSize.toString());
        }
    }
}

