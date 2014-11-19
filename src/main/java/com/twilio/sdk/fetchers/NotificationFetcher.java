package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Notification;

public class NotificationFetcher extends Fetcher<Notification> {

    private final String sid;

    public NotificationFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Notification execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Notifications/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Notification for Sid " + sid);
        }

        return Notification.fromJson(response.getStream(), client.getObjectMapper());
    }
}