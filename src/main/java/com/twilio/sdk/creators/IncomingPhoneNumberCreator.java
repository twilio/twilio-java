package com.twilio.sdk.creators;

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


public class IncomingPhoneNumberCreator extends Creator<IncomingPhoneNumber> {
    private String phoneNumber;
    private URI voiceUrl;
    private String friendlyName;
    private Boolean voiceCallerIdLookup;
    private String areaCode;
    private URI smsFallbackUrl;
    private HttpMethod voiceMethod;
    private String voiceApplicationSid;
    private String smsApplicationSid;
    private HttpMethod smsMethod;
    private HttpMethod statusCallbackMethod;
    private HttpMethod smsFallbackMethod;
    private URI smsUrl;
    private URI voiceFallbackUrl;
    private HttpMethod voiceFallbackMethod;
    private String apiVersion;
    private URI statusCallback;

    public IncomingPhoneNumberCreator() {
        
    }

    public IncomingPhoneNumberCreator setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceUrl(final String voiceUrl) {
        try {
            this.voiceUrl = new URI(voiceUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceCallerIdLookup(final Boolean voiceCallerIdLookup) {
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        return this;
    }
    public IncomingPhoneNumberCreator setAreaCode(final String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsFallbackUrl(final URI smsFallbackUrl) {
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsFallbackUrl(final String smsFallbackUrl) {
        try {
            this.smsFallbackUrl = new URI(smsFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceApplicationSid(final String voiceApplicationSid) {
        this.voiceApplicationSid = voiceApplicationSid;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsApplicationSid(final String smsApplicationSid) {
        this.smsApplicationSid = smsApplicationSid;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    public IncomingPhoneNumberCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsFallbackMethod(final HttpMethod smsFallbackMethod) {
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    public IncomingPhoneNumberCreator setSmsUrl(final String smsUrl) {
        try {
            this.smsUrl = new URI(smsUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceFallbackUrl(final URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceFallbackUrl(final String voiceFallbackUrl) {
        try {
            this.voiceFallbackUrl = new URI(voiceFallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public IncomingPhoneNumberCreator setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    public IncomingPhoneNumberCreator setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public IncomingPhoneNumberCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    public IncomingPhoneNumberCreator setStatusCallback(final String statusCallback) {
        try {
            this.statusCallback = new URI(statusCallback);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    @Override
    public IncomingPhoneNumber execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/IncomingPhoneNumbers.json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("IncomingPhoneNumber creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return IncomingPhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {
        if (phoneNumber != null) {
            request.addPostParam("PhoneNumber", phoneNumber);
        }
        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        if (voiceCallerIdLookup != null) {
            request.addPostParam("VoiceCallerIdLookup", voiceCallerIdLookup.toString());
        }
        if (areaCode != null) {
            request.addPostParam("AreaCode", areaCode);
        }
        if (smsFallbackUrl != null) {
            request.addPostParam("SmsFallbackUrl", smsFallbackUrl.toString());
        }
        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }
        if (voiceApplicationSid != null) {
            request.addPostParam("VoiceApplicationSid", voiceApplicationSid);
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