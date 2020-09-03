/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.verify.v2.service.ratelimit;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class BucketUpdater extends Updater<Bucket> {
    private final String pathServiceSid;
    private final String pathRateLimitSid;
    private final String pathSid;
    private Integer max;
    private Integer interval;

    /**
     * Construct a new BucketUpdater.
     *
     * @param pathServiceSid The SID of the Service that the resource is associated
     *                       with
     * @param pathRateLimitSid Rate Limit Sid.
     * @param pathSid A string that uniquely identifies this Bucket.
     */
    public BucketUpdater(final String pathServiceSid,
                         final String pathRateLimitSid,
                         final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathRateLimitSid = pathRateLimitSid;
        this.pathSid = pathSid;
    }

    /**
     * Maximum number of requests permitted in during the interval..
     *
     * @param max Max number of requests.
     * @return this
     */
    public BucketUpdater setMax(final Integer max) {
        this.max = max;
        return this;
    }

    /**
     * Number of seconds that the rate limit will be enforced over..
     *
     * @param interval Number of seconds that the rate limit will be enforced over.
     * @return this
     */
    public BucketUpdater setInterval(final Integer interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Bucket
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Bucket update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.VERIFY.toString(),
            "/v2/Services/" + this.pathServiceSid + "/RateLimits/" + this.pathRateLimitSid + "/Buckets/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Bucket update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
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
