package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.IncomingPhoneNumber;
import com.twilio.sdk.resources.RestException;

import java.net.URI;
import java.net.URISyntaxException;

public class IncomingPhoneNumberUpdater extends Updater<IncomingPhoneNumber> {

    private final String sid;
    private String voiceApplicationSid;
    private Boolean voiceCallerIdLookup;
    private String friendlyName;
    private URI smsFallbackUrl;
    private String accountSid;
    private HttpMethod voiceMethod;
    private URI voiceUrl;
    private String smsApplicationSid;
    private HttpMethod smsMethod;
    private HttpMethod statusCallbackMethod;
    private HttpMethod smsFallbackMethod;
    private URI smsUrl;
    private URI voiceFallbackUrl;
    private HttpMethod voiceFallbackMethod;
    private String apiVersion;
    private URI statusCallback;

    public IncomingPhoneNumberUpdater(final String sid) {
        this.sid = sid;
    }

    public IncomingPhoneNumberUpdater(final IncomingPhoneNumber target) {
        this(target.getSid());
    }

    public IncomingPhoneNumberUpdater setVoiceApplicationSid(final String voiceApplicationSid) {
        this.voiceApplicationSid = voiceApplicationSid;
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceCallerIdLookup(final Boolean voiceCallerIdLookup) {
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        return this;
    }

    public IncomingPhoneNumberUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsFallbackUrl(final URI smsFallbackUrl) {
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsFallbackUrl(final String smsFallbackUrl) {
        try {
            this.smsFallbackUrl = new URI(smsFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberUpdater setAccountSid(final String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceUrl(final String voiceUrl) {
        try {
            this.voiceUrl = new URI(voiceUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsApplicationSid(final String smsApplicationSid) {
        this.smsApplicationSid = smsApplicationSid;
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    public IncomingPhoneNumberUpdater setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsFallbackMethod(final HttpMethod smsFallbackMethod) {
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    public IncomingPhoneNumberUpdater setSmsUrl(final String smsUrl) {
        try {
            this.smsUrl = new URI(smsUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceFallbackUrl(final URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceFallbackUrl(final String voiceFallbackUrl) {
        try {
            this.voiceFallbackUrl = new URI(voiceFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberUpdater setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    public IncomingPhoneNumberUpdater setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public IncomingPhoneNumberUpdater setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public IncomingPhoneNumberUpdater setStatusCallback(final String statusCallback) {
        try {
            this.statusCallback = new URI(statusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
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

        if (voiceApplicationSid != null) {
            request.addPostParam("VoiceApplicationSid", voiceApplicationSid);
        }

        if (voiceCallerIdLookup != null) {
            request.addPostParam("VoiceCallerIdLookup", voiceCallerIdLookup.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (smsFallbackUrl != null) {
            request.addPostParam("SmsFallbackUrl", smsFallbackUrl.toString());
        }

        if (accountSid != null) {
            request.addPostParam("AccountSid", accountSid);
        }

        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }

        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }

        if (smsApplicationSid != null) {
            request.addPostParam("SmsApplicationSid", smsApplicationSid);
        }

        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }

        if (smsFallbackMethod != null) {
            request.addPostParam("SmsFallbackMethod", smsFallbackMethod.toString());
        }

        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
        }

        if (voiceFallbackUrl != null) {
            request.addPostParam("VoiceFallbackUrl", voiceFallbackUrl.toString());
        }

        if (voiceFallbackMethod != null) {
            request.addPostParam("VoiceFallbackMethod", voiceFallbackMethod.toString());
        }

        if (apiVersion != null) {
            request.addPostParam("ApiVersion", apiVersion);
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

    }
}
