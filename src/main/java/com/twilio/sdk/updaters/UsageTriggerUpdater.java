package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.UsageTrigger;

import java.net.URI;
import java.net.URISyntaxException;

public class UsageTriggerUpdater extends Updater<UsageTrigger> {

    private final String sid;
    private HttpMethod callbackMethod;
    private URI callbackUrl;
    private String friendlyName;

    public UsageTriggerUpdater(final String sid) {
        this.sid = sid;

    }

    public UsageTriggerUpdater(final UsageTrigger target) {
        this(target.getSid());
    }

    public UsageTriggerUpdater setCallbackMethod(final HttpMethod callbackMethod) {
        this.callbackMethod = callbackMethod;
        return this;
    }

    public UsageTriggerUpdater setCallbackUrl(final URI callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    public UsageTriggerUpdater setCallbackUrl(final String callbackUrl) {
        try {
            this.callbackUrl = new URI(callbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public UsageTriggerUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public UsageTrigger execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/Usage/Triggers/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UsageTrigger update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return UsageTrigger.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (callbackMethod != null) {
            request.addPostParam("CallbackMethod", callbackMethod.toString());
        }

        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

    }
}
