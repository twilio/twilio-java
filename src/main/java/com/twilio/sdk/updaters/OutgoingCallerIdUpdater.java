package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.OutgoingCallerId;
import com.twilio.sdk.resources.RestException;

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
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/OutgoingCallerIds/" + sid + ".json",
                                      client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("OutgoingCallerId update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return OutgoingCallerId.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

    }
}
