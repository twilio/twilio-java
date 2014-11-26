package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipDomain;

import java.net.URI;

public class SipDomainUpdater extends Updater<SipDomain> {

    private final String sid;
    private String domainName;
    private String friendlyName;
    private URI voiceUrl;
    private HttpMethod voiceMethod;
    private URI voiceFallbackUrl;
    private HttpMethod voiceFallbackMethod;
    private URI voiceStatusCallbackUrl;
    private HttpMethod voiceStatusCallbackMethod;

    public SipDomainUpdater(final String sid) {
        this.sid = sid;
    }

    public SipDomainUpdater(final SipDomain target) {
        this(target.getSid());
    }

    public SipDomainUpdater setDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    public SipDomainUpdater setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public SipDomainUpdater setVoiceUrl(URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public SipDomainUpdater setVoiceMethod(HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    public SipDomainUpdater setVoiceFallbackUrl(URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    public SipDomainUpdater setVoiceFallbackMethod(HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    public SipDomainUpdater setVoiceStatusCallbackUrl(URI voiceStatusCallbackUrl) {
        this.voiceStatusCallbackUrl = voiceStatusCallbackUrl;
        return this;
    }

    public SipDomainUpdater setVoiceStatusCallbackMethod(HttpMethod voiceStatusCallbackMethod) {
        this.voiceStatusCallbackMethod = voiceStatusCallbackMethod;
        return this;
    }

    @Override
    public SipDomain execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/SIP/Domains/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Account update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException(
                    "SIP Domain update failed: [" + response.getStatusCode() + "] " + response.getContent());
        }

        return SipDomain.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (domainName != null) {
            request.addPostParam("DomainName", domainName);
        }

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

        if (voiceStatusCallbackUrl != null) {
            request.addPostParam("VoiceStatusCallback", voiceStatusCallbackUrl.toString());
        }

        if (voiceStatusCallbackMethod != null) {
            request.addPostParam("VoiceStatusCallbackMethod", voiceStatusCallbackMethod.toString());
        }
    }

}
