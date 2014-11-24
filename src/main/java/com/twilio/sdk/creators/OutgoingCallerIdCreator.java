package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.OutgoingCallerId;
import com.twilio.sdk.resources.RestException;

import java.net.URI;
import java.net.URISyntaxException;

public class OutgoingCallerIdCreator extends Creator<OutgoingCallerId> {

    private final String phoneNumber;
    private Integer callDelay;
    private String friendlyName;
    private HttpMethod statusCallbackMethod;
    private String extension;
    private URI statusCallback;

    public OutgoingCallerIdCreator(final String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public OutgoingCallerIdCreator setCallDelay(final Integer callDelay) {
        this.callDelay = callDelay;
        return this;
    }

    public OutgoingCallerIdCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public OutgoingCallerIdCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public OutgoingCallerIdCreator setExtension(final String extension) {
        this.extension = extension;
        return this;
    }

    public OutgoingCallerIdCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public OutgoingCallerIdCreator setStatusCallback(final String statusCallback) {
        try {
            this.statusCallback = new URI(statusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    @Override
    public OutgoingCallerId execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/OutgoingCallerIds.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("OutgoingCallerId creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return OutgoingCallerId.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (phoneNumber != null) {
            request.addPostParam("PhoneNumber", phoneNumber);
        }
        if (callDelay != null) {
            request.addPostParam("CallDelay", callDelay.toString());
        }
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }
        if (extension != null) {
            request.addPostParam("Extension", extension);
        }
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
    }

}
