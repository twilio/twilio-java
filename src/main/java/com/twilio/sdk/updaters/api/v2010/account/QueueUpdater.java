package com.twilio.sdk.updaters.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.Queue;
import com.twilio.sdk.updaters.Updater;

public class QueueUpdater extends Updater<Queue> {
    private final String accountSid;
    private final String sid;
    private String friendlyName;
    private Integer maxSize;

    /**
     * Construct a new QueueUpdater
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     */
    public QueueUpdater(final String accountSid, final String sid) {
        this.accountSid = accountSid;
        this.sid = sid;
    }

    /**
     * A human readable description of the queue
     * 
     * @param friendlyName A human readable description of the queue
     * @return this
     */
    public QueueUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The maximum number of members that can be in the queue at a time
     * 
     * @param maxSize The max number of members allowed in the queue
     * @return this
     */
    public QueueUpdater setMaxSize(final Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Queue
     */
    @Override
    public Queue execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Queues/" + this.sid + ".json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Queue update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Queue.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
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