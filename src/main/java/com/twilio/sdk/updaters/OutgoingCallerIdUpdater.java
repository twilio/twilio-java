package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.OutgoingCallerId;

public class OutgoingCallerIdUpdater extends Updater<OutgoingCallerId> {

    private final String sid;
    private String friendlyName;

    public OutgoingCallerIdUpdater(final String sid) {
        this.sid = sid;
    }

    public OutgoingCallerIdUpdater(final OutgoingCallerId target) {
        this(target.getSid());
    }

    public OutgoingCallerIdUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public OutgoingCallerId execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/OutgoingCallerIds/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("OutgoingCallerId update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "OutgoingCallerId update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return OutgoingCallerId.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

    }
}
