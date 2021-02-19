/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class SyncStreamUpdater extends Updater<SyncStream> {
    private final String pathServiceSid;
    private final String pathSid;
    private Integer ttl;

    /**
     * Construct a new SyncStreamUpdater.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync Stream
     *                       resource to update
     * @param pathSid The SID of the Stream resource to update
     */
    public SyncStreamUpdater(final String pathServiceSid,
                             final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathSid = pathSid;
    }

    /**
     * How long, in seconds, before the Stream expires and is deleted
     * (time-to-live). Can be an integer from 0 to 31,536,000 (1 year). The default
     * value is `0`, which means the Stream does not expire. The Stream will be
     * deleted automatically after it expires, but there can be a delay between the
     * expiration time and the resources's deletion..
     *
     * @param ttl How long, in seconds, before the Stream expires and is deleted
     * @return this
     */
    public SyncStreamUpdater setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated SyncStream
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SyncStream update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Streams/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncStream update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SyncStream.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }
    }
}