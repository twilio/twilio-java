package com.twilio.sdk.deleters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Notification;
import com.twilio.sdk.resources.RestException;

public class NotificationDeleter extends Deleter<Notification> {

    private final String sid;

    public NotificationDeleter(final String sid) {
        this.sid = sid;
    }

    public NotificationDeleter(final Notification target) {
        this(target.getSid());
    }

    @Override
    public void execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.DELETE, "/Accounts/{AccountSid}/Notifications/" + sid + ".json",
                                      client.getAccountSid());
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Notification delete failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_NO_CONTENT) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }
    }
}
