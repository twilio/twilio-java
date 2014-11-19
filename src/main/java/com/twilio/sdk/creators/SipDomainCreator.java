package com.twilio.sdk.creators;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipDomain;

import java.net.URI;

public class SipDomainCreator extends Creator<SipDomain> {
    private final String domainName;
    private String friendlyName;
    private URI voiceUrl;
    private HttpMethod voiceMethod;
    private URI voiceFallbackUrl;
    private HttpMethod voiceFallbackMethod;
    private URI voiceStatusCallback;
    private HttpMethod voiceStatusCallbackMethod;

    public SipDomainCreator(final String domainName) {
        this.domainName = domainName;
    }

    public SipDomainCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public SipDomainCreator setVoiceUrl(URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public SipDomainCreator setVoiceMethod(HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    public SipDomainCreator setVoiceFallbackUrl(URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    public SipDomainCreator setVoiceFallbackMethod(HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    public SipDomainCreator setVoiceStatusCallback(URI voiceStatusCallback) {
        this.voiceStatusCallback = voiceStatusCallback;
        return this;
    }

    public SipDomainCreator setVoiceStatusCallbackMethod(HttpMethod voiceStatusCallbackMethod) {
        this.voiceStatusCallbackMethod = voiceStatusCallbackMethod;
        return this;
    }

    @Override
    public SipDomain execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/SIP/Domains.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SIP Domain creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            throw new ApiException("SIP Domain creation failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipDomain.fromJson(response.getStream(), client.getObjectMapper());
    }


    private void addPostParams(final Request request) {
        request.addPostParam("DomainName", domainName);

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }

        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }

        if (voiceFallbackUrl != null) {
            request.addPostParam("VoiceFallbackUrl", voiceFallbackUrl.toString());
        }

        if (voiceFallbackMethod != null) {
            request.addPostParam("VoiceFallbackMethod", voiceFallbackMethod.toString());
        }

        if (voiceStatusCallback != null) {
            request.addPostParam("VoiceStatusCallback", voiceStatusCallback.toString());
        }

        if (voiceStatusCallbackMethod != null) {
            request.addPostParam("VoiceStatusCallbackMethod", voiceStatusCallbackMethod.toString());
        }

    }

}
