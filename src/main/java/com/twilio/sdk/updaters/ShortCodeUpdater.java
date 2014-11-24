package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.ShortCode;

import java.net.URI;
import java.net.URISyntaxException;

public class ShortCodeUpdater extends Updater<ShortCode> {

    private final String sid;
    private String friendlyName;
    private URI smsFallbackUrl;
    private HttpMethod smsMethod;
    private HttpMethod smsFallbackMethod;
    private URI smsUrl;
    private String apiVersion;

    public ShortCodeUpdater(final String sid) {
        this.sid = sid;
    }

    public ShortCodeUpdater(final ShortCode target) {
        this(target.getSid());
    }

    public ShortCodeUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public ShortCodeUpdater setSmsFallbackUrl(final URI smsFallbackUrl) {
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    public ShortCodeUpdater setSmsFallbackUrl(final String smsFallbackUrl) {
        try {
            this.smsFallbackUrl = new URI(smsFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ShortCodeUpdater setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    public ShortCodeUpdater setSmsFallbackMethod(final HttpMethod smsFallbackMethod) {
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }

    public ShortCodeUpdater setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    public ShortCodeUpdater setSmsUrl(final String smsUrl) {
        try {
            this.smsUrl = new URI(smsUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ShortCodeUpdater setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    @Override
    public ShortCode execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/SMS/ShortCodes/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ShortCode update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return ShortCode.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (smsFallbackUrl != null) {
            request.addPostParam("SmsFallbackUrl", smsFallbackUrl.toString());
        }

        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
        }

        if (smsFallbackMethod != null) {
            request.addPostParam("SmsFallbackMethod", smsFallbackMethod.toString());
        }

        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
        }

        if (apiVersion != null) {
            request.addPostParam("ApiVersion", apiVersion);
        }

    }
}
