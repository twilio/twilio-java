package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.IncomingPhoneNumber;
import com.twilio.sdk.resources.RestException;

public class IncomingPhoneNumberUpdater extends Updater<IncomingPhoneNumber> {

    private final String sid;
    private IncomingPhoneNumber.Status status;
    private String friendlyName;

    public IncomingPhoneNumberUpdater(final String sid) {
        this.sid = sid;
    }

    public IncomingPhoneNumberUpdater(final IncomingPhoneNumber target) {
        this(target.getSid());
    }

    public IncomingPhoneNumberUpdater setStatus(final IncomingPhoneNumber.Status status) {
        this.status = status;
        return this;
    }

    public IncomingPhoneNumberUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public IncomingPhoneNumber execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/IncomingPhoneNumbers/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("IncomingPhoneNumber update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return IncomingPhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

    }
}
