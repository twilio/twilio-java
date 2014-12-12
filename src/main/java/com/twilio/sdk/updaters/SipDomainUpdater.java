package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.SipDomain;
import com.twilio.sdk.resources.RestException;

import java.net.URI;
import java.net.URISyntaxException;

public class SipDomainUpdater extends Updater<SipDomain> {

    private final String sid;
    private URI voiceStatusCallbackUrl;
    private String friendlyName;
    private HttpMethod voiceStatusCallbackMethod;
    private URI voiceUrl;
    private String apiVersion;
    private HttpMethod voiceFallbackMethod;
    private HttpMethod voiceMethod;
    private URI voiceFallbackUrl;
    
    public SipDomainUpdater(final String sid) {
        this.sid = sid;
    }

    public SipDomainUpdater(final SipDomain target) {
        this(target.getSid());
    }

    
    public SipDomainUpdater setVoiceStatusCallbackUrl(final URI voiceStatusCallbackUrl) {
        this.voiceStatusCallbackUrl = voiceStatusCallbackUrl;
        return this;
    }
    public SipDomainUpdater setVoiceStatusCallbackUrl(final String voiceStatusCallbackUrl) {
        try {
            this.voiceStatusCallbackUrl = new URI(voiceStatusCallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }
    
    public SipDomainUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }
    
    public SipDomainUpdater setVoiceStatusCallbackMethod(final HttpMethod voiceStatusCallbackMethod) {
        this.voiceStatusCallbackMethod = voiceStatusCallbackMethod;
        return this;
    }
    
    public SipDomainUpdater setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }
    public SipDomainUpdater setVoiceUrl(final String voiceUrl) {
        try {
            this.voiceUrl = new URI(voiceUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }
    
    public SipDomainUpdater setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }
    
    public SipDomainUpdater setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }
    
    public SipDomainUpdater setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }
    
    public SipDomainUpdater setVoiceFallbackUrl(final URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }
    public SipDomainUpdater setVoiceFallbackUrl(final String voiceFallbackUrl) {
        try {
            this.voiceFallbackUrl = new URI(voiceFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }
    

    @Override
    public SipDomain execute(final TwilioRestClient client)  {
        Request request = new Request(HttpMethod.POST, "/2010-04-01/Accounts/{AccountSid}/SIP/Domains/" + sid + ".json", client.getAccountSid());
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SipDomain update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return SipDomain.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        
        if (voiceStatusCallbackUrl != null) {
            request.addPostParam("VoiceStatusCallbackUrl", voiceStatusCallbackUrl.toString());
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (voiceStatusCallbackMethod != null) {
            request.addPostParam("VoiceStatusCallbackMethod", voiceStatusCallbackMethod.toString());
        }
        
        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }
        
        if (apiVersion != null) {
            request.addPostParam("ApiVersion", apiVersion);
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