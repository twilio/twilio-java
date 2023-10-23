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



public class CallSummariesReader extends Reader<CallSummaries> {
    private String from;
    private String to;
    private String fromCarrier;
    private String toCarrier;
    private String fromCountryCode;
    private String toCountryCode;
    private Boolean branded;
    private Boolean verifiedCaller;
    private Boolean hasTag;
    private String startTime;
    private String endTime;
    private String callType;
    private String callState;
    private String direction;
    private CallSummaries.ProcessingStateRequest processingState;
    private CallSummaries.SortBy sortBy;
    private String subaccount;
    private Boolean abnormalSession;
    private CallSummaries.AnsweredBy answeredBy;
    private String answeredByAnnotation;
    private String connectivityIssueAnnotation;
    private String qualityIssueAnnotation;
    private Boolean spamAnnotation;
    private String callScoreAnnotation;
    private Integer pageSize;

    public CallSummariesReader(){
    }

    public CallSummariesReader setFrom(final String from){
        this.from = from;
        return this;
    }
    public CallSummariesReader setTo(final String to){
        this.to = to;
        return this;
    }
    public CallSummariesReader setFromCarrier(final String fromCarrier){
        this.fromCarrier = fromCarrier;
        return this;
    }
    public CallSummariesReader setToCarrier(final String toCarrier){
        this.toCarrier = toCarrier;
        return this;
    }
    public CallSummariesReader setFromCountryCode(final String fromCountryCode){
        this.fromCountryCode = fromCountryCode;
        return this;
    }
    public CallSummariesReader setToCountryCode(final String toCountryCode){
        this.toCountryCode = toCountryCode;
        return this;
    }
    public CallSummariesReader setBranded(final Boolean branded){
        this.branded = branded;
        return this;
    }
    public CallSummariesReader setVerifiedCaller(final Boolean verifiedCaller){
        this.verifiedCaller = verifiedCaller;
        return this;
    }
    public CallSummariesReader setHasTag(final Boolean hasTag){
        this.hasTag = hasTag;
        return this;
    }
    public CallSummariesReader setStartTime(final String startTime){
        this.startTime = startTime;
        return this;
    }
    public CallSummariesReader setEndTime(final String endTime){
        this.endTime = endTime;
        return this;
    }
    public CallSummariesReader setCallType(final String callType){
        this.callType = callType;
        return this;
    }
    public CallSummariesReader setCallState(final String callState){
        this.callState = callState;
        return this;
    }
    public CallSummariesReader setDirection(final String direction){
        this.direction = direction;
        return this;
    }
    public CallSummariesReader setProcessingState(final CallSummaries.ProcessingStateRequest processingState){
        this.processingState = processingState;
        return this;
    }
    public CallSummariesReader setSortBy(final CallSummaries.SortBy sortBy){
        this.sortBy = sortBy;
        return this;
    }
    public CallSummariesReader setSubaccount(final String subaccount){
        this.subaccount = subaccount;
        return this;
    }
    public CallSummariesReader setAbnormalSession(final Boolean abnormalSession){
        this.abnormalSession = abnormalSession;
        return this;
    }
    public CallSummariesReader setAnsweredBy(final CallSummaries.AnsweredBy answeredBy){
        this.answeredBy = answeredBy;
        return this;
    }
    public CallSummariesReader setAnsweredByAnnotation(final String answeredByAnnotation){
        this.answeredByAnnotation = answeredByAnnotation;
        return this;
    }
    public CallSummariesReader setConnectivityIssueAnnotation(final String connectivityIssueAnnotation){
        this.connectivityIssueAnnotation = connectivityIssueAnnotation;
        return this;
    }
    public CallSummariesReader setQualityIssueAnnotation(final String qualityIssueAnnotation){
        this.qualityIssueAnnotation = qualityIssueAnnotation;
        return this;
    }
    public CallSummariesReader setSpamAnnotation(final Boolean spamAnnotation){
        this.spamAnnotation = spamAnnotation;
        return this;
    }
    public CallSummariesReader setCallScoreAnnotation(final String callScoreAnnotation){
        this.callScoreAnnotation = callScoreAnnotation;
        return this;
    }
    public CallSummariesReader setPageSize(final Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public ResourceSet<CallSummaries> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<CallSummaries> firstPage(final TwilioRestClient client) {
        String path = "/v1/Voice/Summaries";

        Request request = new Request(
            HttpMethod.GET,
            Domains.INSIGHTS.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<CallSummaries> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("CallSummaries read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "call_summaries",
            response.getContent(),
            CallSummaries.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<CallSummaries> previousPage(final Page<CallSummaries> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }


    @Override
    public Page<CallSummaries> nextPage(final Page<CallSummaries> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.INSIGHTS.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<CallSummaries> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }
    private void addQueryParams(final Request request) {
        if (from != null) {
    
            request.addQueryParam("From", from);
        }
        if (to != null) {
    
            request.addQueryParam("To", to);
        }
        if (fromCarrier != null) {
    
            request.addQueryParam("FromCarrier", fromCarrier);
        }
        if (toCarrier != null) {
    
            request.addQueryParam("ToCarrier", toCarrier);
        }
        if (fromCountryCode != null) {
    
            request.addQueryParam("FromCountryCode", fromCountryCode);
        }
        if (toCountryCode != null) {
    
            request.addQueryParam("ToCountryCode", toCountryCode);
        }
        if (branded != null) {
    
            request.addQueryParam("Branded", branded.toString());
        }
        if (verifiedCaller != null) {
    
            request.addQueryParam("VerifiedCaller", verifiedCaller.toString());
        }
        if (hasTag != null) {
    
            request.addQueryParam("HasTag", hasTag.toString());
        }
        if (startTime != null) {
    
            request.addQueryParam("StartTime", startTime);
        }
        if (endTime != null) {
    
            request.addQueryParam("EndTime", endTime);
        }
        if (callType != null) {
    
            request.addQueryParam("CallType", callType);
        }
        if (callState != null) {
    
            request.addQueryParam("CallState", callState);
        }
        if (direction != null) {
    
            request.addQueryParam("Direction", direction);
        }
        if (processingState != null) {
    
            request.addQueryParam("ProcessingState", processingState.toString());
        }
        if (sortBy != null) {
    
            request.addQueryParam("SortBy", sortBy.toString());
        }
        if (subaccount != null) {
    
            request.addQueryParam("Subaccount", subaccount);
        }
        if (abnormalSession != null) {
    
            request.addQueryParam("AbnormalSession", abnormalSession.toString());
        }
        if (answeredBy != null) {
    
            request.addQueryParam("AnsweredBy", answeredBy.toString());
        }
        if (answeredByAnnotation != null) {
    
            request.addQueryParam("AnsweredByAnnotation", answeredByAnnotation);
        }
        if (connectivityIssueAnnotation != null) {
    
            request.addQueryParam("ConnectivityIssueAnnotation", connectivityIssueAnnotation);
        }
        if (qualityIssueAnnotation != null) {
    
            request.addQueryParam("QualityIssueAnnotation", qualityIssueAnnotation);
        }
        if (spamAnnotation != null) {
    
            request.addQueryParam("SpamAnnotation", spamAnnotation.toString());
        }
        if (callScoreAnnotation != null) {
    
            request.addQueryParam("CallScoreAnnotation", callScoreAnnotation);
        }
        if (pageSize != null) {
    
            request.addQueryParam("PageSize", pageSize.toString());
        }

        if(getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
