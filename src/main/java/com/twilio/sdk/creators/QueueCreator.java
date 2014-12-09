package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Queue;
import com.twilio.sdk.resources.RestException;

public class QueueCreator extends Creator<Queue> {

    private final String friendlyName;
    private Integer maxSize;

    public QueueCreator(final String friendlyName) {

        this.friendlyName = friendlyName;
    }

    public QueueCreator setMaxSize(final Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    @Override
    public Queue execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Queues.json", client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Queue creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
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
