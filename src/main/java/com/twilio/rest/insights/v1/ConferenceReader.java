/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Insights
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.insights.v1;

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



public class ConferenceReader extends Reader<Conference> {
    private String conferenceSid;
    private String friendlyName;
    private String status;
    private String createdAfter;
    private String createdBefore;
    private String mixerRegion;
    private String tags;
    private String subaccount;
    private String detectedIssues;
    private String endReason;
    private Integer pageSize;

    public ConferenceReader(){
    }

    public ConferenceReader setConferenceSid(final String conferenceSid){
        this.conferenceSid = conferenceSid;
        return this;
    }
    public ConferenceReader setFriendlyName(final String friendlyName){
        this.friendlyName = friendlyName;
        return this;
    }
    public ConferenceReader setStatus(final String status){
        this.status = status;
        return this;
    }
    public ConferenceReader setCreatedAfter(final String createdAfter){
        this.createdAfter = createdAfter;
        return this;
    }
    public ConferenceReader setCreatedBefore(final String createdBefore){
        this.createdBefore = createdBefore;
        return this;
    }
    public ConferenceReader setMixerRegion(final String mixerRegion){
        this.mixerRegion = mixerRegion;
        return this;
    }
    public ConferenceReader setTags(final String tags){
        this.tags = tags;
        return this;
    }
    public ConferenceReader setSubaccount(final String subaccount){
        this.subaccount = subaccount;
        return this;
    }
    public ConferenceReader setDetectedIssues(final String detectedIssues){
        this.detectedIssues = detectedIssues;
        return this;
    }
    public ConferenceReader setEndReason(final String endReason){
        this.endReason = endReason;
        return this;
    }
    public ConferenceReader setPageSize(final Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<Conference> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Conference> firstPage(final TwilioRestClient client) {
        String path = "/v1/Conferences";

        Request request = new Request(
            HttpMethod.GET,
            Domains.INSIGHTS.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<Conference> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Conference read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "conferences",
            response.getContent(),
            Conference.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Conference> previousPage(final Page<Conference> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }


    @Override
    public Page<Conference> nextPage(final Page<Conference> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Conference> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }
    private void addQueryParams(final Request request) {
        if (conferenceSid != null) {
    
            request.addQueryParam("ConferenceSid", conferenceSid);
        }
        if (friendlyName != null) {
    
            request.addQueryParam("FriendlyName", friendlyName);
        }
        if (status != null) {
    
            request.addQueryParam("Status", status);
        }
        if (createdAfter != null) {
    
            request.addQueryParam("CreatedAfter", createdAfter);
        }
        if (createdBefore != null) {
    
            request.addQueryParam("CreatedBefore", createdBefore);
        }
        if (mixerRegion != null) {
    
            request.addQueryParam("MixerRegion", mixerRegion);
        }
        if (tags != null) {
    
            request.addQueryParam("Tags", tags);
        }
        if (subaccount != null) {
    
            request.addQueryParam("Subaccount", subaccount);
        }
        if (detectedIssues != null) {
    
            request.addQueryParam("DetectedIssues", detectedIssues);
        }
        if (endReason != null) {
    
            request.addQueryParam("EndReason", endReason);
        }
        if (pageSize != null) {
    
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if(getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
