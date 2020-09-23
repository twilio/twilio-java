/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class QueueUpdater extends Updater<Queue> {
    private String pathAccountSid;
    private final String pathSid;
    private String friendlyName;
    private Integer maxSize;

    /**
     * Construct a new QueueUpdater.
     *
     * @param pathSid The unique string that identifies this resource
     */
    public QueueUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * Construct a new QueueUpdater.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       update
     * @param pathSid The unique string that identifies this resource
     */
    public QueueUpdater(final String pathAccountSid,
                        final String pathSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathSid = pathSid;
    }

    /**
     * A descriptive string that you created to describe this resource. It can be up
     * to 64 characters long..
     *
     * @param friendlyName A string to describe this resource
     * @return this
     */
    public QueueUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The maximum number of calls allowed to be in the queue. The default is 100.
     * The maximum is 5000..
     *
     * @param maxSize The max number of calls allowed in the queue
     * @return this
     */
    public QueueUpdater setMaxSize(final Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Queue
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Queue update(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Queues/" + this.pathSid + ".json"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Queue update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Queue.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (maxSize != null) {
            request.addPostParam("MaxSize", maxSize.toString());
        }
    }
}