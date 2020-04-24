/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service.ratelimit;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class BucketCreator extends Creator<Bucket> {
    private final String pathServiceSid;
    private final String pathRateLimitSid;
    private final Integer max;
    private final Integer interval;

    /**
     * Construct a new BucketCreator.
     *
     * @param pathServiceSid The SID of the Service that the resource is associated
     *                       with
     * @param pathRateLimitSid Rate Limit Sid.
     * @param max Max number of requests.
     * @param interval Number of seconds that the rate limit will be enforced over.
     */
    public BucketCreator(final String pathServiceSid,
                         final String pathRateLimitSid,
                         final Integer max,
                         final Integer interval) {
        this.pathServiceSid = pathServiceSid;
        this.pathRateLimitSid = pathRateLimitSid;
        this.max = max;
        this.interval = interval;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Bucket
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Bucket create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VERIFY.toString(),
            "/v2/Services/" + this.pathServiceSid + "/RateLimits/" + this.pathRateLimitSid + "/Buckets",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Bucket creation failed: Unable to connect to server");
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

        return Bucket.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (max != null) {
            request.addPostParam("Max", max.toString());
        }

        if (interval != null) {
            request.addPostParam("Interval", interval.toString());
        }
    }
}