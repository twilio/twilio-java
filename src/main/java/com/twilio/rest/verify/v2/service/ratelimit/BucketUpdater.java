/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Verify
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.verify.v2.service.ratelimit;

import com.twilio.base.Updater;
import com.twilio.constant.EnumConstants;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;




public class BucketUpdater extends Updater<Bucket>{
    private String pathServiceSid;
    private String pathRateLimitSid;
    private String pathSid;
    private Integer max;
    private Integer interval;

    public BucketUpdater(final String pathServiceSid, final String pathRateLimitSid, final String pathSid){
        this.pathServiceSid = pathServiceSid;
        this.pathRateLimitSid = pathRateLimitSid;
        this.pathSid = pathSid;
    }

    public BucketUpdater setMax(final Integer max){
        this.max = max;
        return this;
    }
    public BucketUpdater setInterval(final Integer interval){
        this.interval = interval;
        return this;
    }

    @Override
    public Bucket update(final TwilioRestClient client){
        String path = "/v2/Services/{ServiceSid}/RateLimits/{RateLimitSid}/Buckets/{Sid}";

        path = path.replace("{"+"ServiceSid"+"}", this.pathServiceSid.toString());
        path = path.replace("{"+"RateLimitSid"+"}", this.pathRateLimitSid.toString());
        path = path.replace("{"+"Sid"+"}", this.pathSid.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.VERIFY.toString(),
            path
        );
        request.setContentType(EnumConstants.ContentType.FORM_URLENCODED);
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Bucket update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content", response.getStatusCode());
            }
            throw new ApiException(restException);
        }

        return Bucket.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (max != null) {
            request.addPostParam("Max", max.toString());
    
        }
        if (interval != null) {
            request.addPostParam("Interval", interval.toString());
    
        }
    }
}
