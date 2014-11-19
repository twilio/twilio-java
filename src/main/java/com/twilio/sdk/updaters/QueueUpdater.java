package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Queue;

public class QueueUpdater extends Updater<Queue> {

    private final String sid;
    private String friendlyName;
    private Integer maxSize;

    public QueueUpdater(final String sid) {
        this.sid = sid;
    }

    public QueueUpdater(final Queue target) {
        this(target.getSid());
    }

    public QueueUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public QueueUpdater setMaxSize(final Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    @Override
    public Queue execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Queues/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Queue update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Queue update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Queue.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (maxSize != null) {
            request.addPostParam("MaxSize", maxSize.toString());
        }

    }
}
