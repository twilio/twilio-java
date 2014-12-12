package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Queue;
import com.twilio.sdk.resources.RestException;

public class QueueDeleter extends Deleter<Queue> {

    private final String sid;

    public QueueDeleter(final String sid) {
        this.sid = sid;
    }

    public QueueDeleter(final Queue target) {
        this(target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.DELETE, "/2010-04-01/Accounts/{AccountSid}/Queues/" + sid + ".json", client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Queue delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }
    }
}