package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Application;

import java.net.URI;
import java.net.URISyntaxException;

public class ApplicationUpdater extends Updater<Application> {

    private final String sid;
    private URI smsStatusCallback;
    private HttpMethod smsFallbackMethod;
    private URI messageStatusCallback;
    private Boolean voiceCallerIdLookup;
    private String friendlyName;
    private URI smsFallbackUrl;
    private URI voiceUrl;
    private HttpMethod smsMethod;
    private String apiVersion;
    private HttpMethod statusCallbackMethod;
    private URI smsUrl;
    private URI statusCallback;
    private HttpMethod voiceFallbackMethod;
    private HttpMethod voiceMethod;
    private URI voiceFallbackUrl;

    public ApplicationUpdater(final String sid) {
        this.sid = sid;
    }

    public ApplicationUpdater(final Application target) {
        this(target.getSid());
    }

    public ApplicationUpdater setSmsStatusCallback(final URI smsStatusCallback) {
        this.smsStatusCallback = smsStatusCallback;
        return this;
    }

    public ApplicationUpdater setSmsStatusCallback(final String smsStatusCallback) {
        try {
            this.smsStatusCallback = new URI(smsStatusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ApplicationUpdater setSmsFallbackMethod(final HttpMethod smsFallbackMethod) {
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }

    public ApplicationUpdater setMessageStatusCallback(final URI messageStatusCallback) {
        this.messageStatusCallback = messageStatusCallback;
        return this;
    }

    public ApplicationUpdater setMessageStatusCallback(final String messageStatusCallback) {
        try {
            this.messageStatusCallback = new URI(messageStatusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ApplicationUpdater setVoiceCallerIdLookup(final Boolean voiceCallerIdLookup) {
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        return this;
    }

    public ApplicationUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public ApplicationUpdater setSmsFallbackUrl(final URI smsFallbackUrl) {
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    public ApplicationUpdater setSmsFallbackUrl(final String smsFallbackUrl) {
        try {
            this.smsFallbackUrl = new URI(smsFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ApplicationUpdater setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public ApplicationUpdater setVoiceUrl(final String voiceUrl) {
        try {
            this.voiceUrl = new URI(voiceUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ApplicationUpdater setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    public ApplicationUpdater setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public ApplicationUpdater setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public ApplicationUpdater setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    public ApplicationUpdater setSmsUrl(final String smsUrl) {
        try {
            this.smsUrl = new URI(smsUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ApplicationUpdater setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public ApplicationUpdater setStatusCallback(final String statusCallback) {
        try {
            this.statusCallback = new URI(statusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ApplicationUpdater setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    public ApplicationUpdater setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    public ApplicationUpdater setVoiceFallbackUrl(final URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    public ApplicationUpdater setVoiceFallbackUrl(final String voiceFallbackUrl) {
        try {
            this.voiceFallbackUrl = new URI(voiceFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    @Override
    public Application execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Applications/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Application update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "Application update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return Application.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (smsStatusCallback != null) {
            request.addPostParam("SmsStatusCallback", smsStatusCallback.toString());
        }

        if (smsFallbackMethod != null) {
            request.addPostParam("SmsFallbackMethod", smsFallbackMethod.toString());
        }

        if (messageStatusCallback != null) {
            request.addPostParam("MessageStatusCallback", messageStatusCallback.toString());
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

        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }

        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
        }

        if (apiVersion != null) {
            request.addPostParam("ApiVersion", apiVersion);
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }

        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (voiceFallbackMethod != null) {
            request.addPostParam("VoiceFallbackMethod", voiceFallbackMethod.toString());
        }

        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }

        if (voiceFallbackUrl != null) {
            request.addPostParam("VoiceFallbackUrl", voiceFallbackUrl.toString());
        }

    }
}
