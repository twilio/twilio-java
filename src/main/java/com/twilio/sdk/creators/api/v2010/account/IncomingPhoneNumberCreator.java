package com.twilio.sdk.creators.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.Promoter;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.IncomingPhoneNumber;

import java.net.URI;

public class IncomingPhoneNumberCreator extends Creator<IncomingPhoneNumber> {
    private final String ownerAccountSid;
    private com.twilio.types.PhoneNumber phoneNumber;
    private String areaCode;
    private String apiVersion;
    private String friendlyName;
    private String smsApplicationSid;
    private HttpMethod smsFallbackMethod;
    private URI smsFallbackUrl;
    private HttpMethod smsMethod;
    private URI smsUrl;
    private URI statusCallback;
    private HttpMethod statusCallbackMethod;
    private String voiceApplicationSid;
    private Boolean voiceCallerIdLookup;
    private HttpMethod voiceFallbackMethod;
    private URI voiceFallbackUrl;
    private HttpMethod voiceMethod;
    private URI voiceUrl;

    /**
     * Construct a new IncomingPhoneNumberCreator
     * 
     * @param ownerAccountSid The owner_account_sid
     * @param phoneNumber The phone number
     */
    public IncomingPhoneNumberCreator(final String ownerAccountSid, final com.twilio.types.PhoneNumber phoneNumber) {
        this.ownerAccountSid = ownerAccountSid;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Construct a new IncomingPhoneNumberCreator
     * 
     * @param ownerAccountSid The owner_account_sid
     * @param areaCode The desired area code for the new number
     */
    public IncomingPhoneNumberCreator(final String ownerAccountSid, final String areaCode) {
        this.ownerAccountSid = ownerAccountSid;
        this.areaCode = areaCode;
    }

    /**
     * Calls to this phone number will start a new TwiML session with this API
     * version.
     * 
     * @param apiVersion The Twilio Rest API version to use
     * @return this
     */
    public IncomingPhoneNumberCreator setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * A human readable descriptive text for this resource, up to 64 characters
     * long. By default, the `FriendlyName` is a nicely formatted version of the
     * phone number.
     * 
     * @param friendlyName A human readable description of this resource
     * @return this
     */
    public IncomingPhoneNumberCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The 34 character sid of the application Twilio should use to handle SMSs sent
     * to this number. If a `SmsApplicationSid` is present, Twilio will ignore all
     * of the SMS urls above and use those set on the application.
     * 
     * @param smsApplicationSid Unique string that identifies the application
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsApplicationSid(final String smsApplicationSid) {
        this.smsApplicationSid = smsApplicationSid;
        return this;
    }

    /**
     * The HTTP method Twilio will use when requesting the above URL. Either `GET`
     * or `POST`.
     * 
     * @param smsFallbackMethod HTTP method used with sms fallback url
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsFallbackMethod(final HttpMethod smsFallbackMethod) {
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }

    /**
     * The URL that Twilio will request if an error occurs retrieving or executing
     * the TwiML from `SmsUrl`.
     * 
     * @param smsFallbackUrl URL Twilio will request if an error occurs in
     *                       executing TwiML
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsFallbackUrl(final URI smsFallbackUrl) {
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    /**
     * The URL that Twilio will request if an error occurs retrieving or executing
     * the TwiML from `SmsUrl`.
     * 
     * @param smsFallbackUrl URL Twilio will request if an error occurs in
     *                       executing TwiML
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsFallbackUrl(final String smsFallbackUrl) {
        return setSmsFallbackUrl(Promoter.uriFromString(smsFallbackUrl));
    }

    /**
     * The HTTP method Twilio will use when making requests to the `SmsUrl`. Either
     * `GET` or `POST`.
     * 
     * @param smsMethod HTTP method to use with sms url
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    /**
     * The URL Twilio will request when receiving an incoming SMS message to this
     * number.
     * 
     * @param smsUrl URL Twilio will request when receiving an SMS
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    /**
     * The URL Twilio will request when receiving an incoming SMS message to this
     * number.
     * 
     * @param smsUrl URL Twilio will request when receiving an SMS
     * @return this
     */
    public IncomingPhoneNumberCreator setSmsUrl(final String smsUrl) {
        return setSmsUrl(Promoter.uriFromString(smsUrl));
    }

    /**
     * The URL that Twilio will request to pass status parameters (such as call
     * ended) to your application.
     * 
     * @param statusCallback URL Twilio will use to pass status parameters
     * @return this
     */
    public IncomingPhoneNumberCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL that Twilio will request to pass status parameters (such as call
     * ended) to your application.
     * 
     * @param statusCallback URL Twilio will use to pass status parameters
     * @return this
     */
    public IncomingPhoneNumberCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The HTTP method Twilio will use to make requests to the `StatusCallback` URL.
     * Either `GET` or `POST`.
     * 
     * @param statusCallbackMethod HTTP method twilio will use with status callback
     * @return this
     */
    public IncomingPhoneNumberCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    /**
     * The 34 character sid of the application Twilio should use to handle phone
     * calls to this number. If a `VoiceApplicationSid` is present, Twilio will
     * ignore all of the voice urls above and use those set on the application.
     * Setting a `VoiceApplicationSid` will automatically delete your `TrunkSid` and
     * vice versa.
     * 
     * @param voiceApplicationSid The unique sid of the application to handle this
     *                            number
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceApplicationSid(final String voiceApplicationSid) {
        this.voiceApplicationSid = voiceApplicationSid;
        return this;
    }

    /**
     * Look up the caller's caller-ID name from the CNAM database ($0.01 per look
     * up). Either `true` or `false`.
     * 
     * @param voiceCallerIdLookup Look up the caller's caller-ID
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceCallerIdLookup(final Boolean voiceCallerIdLookup) {
        this.voiceCallerIdLookup = voiceCallerIdLookup;
        return this;
    }

    /**
     * The HTTP method Twilio will use when requesting the `VoiceFallbackUrl`.
     * Either `GET` or `POST`.
     * 
     * @param voiceFallbackMethod HTTP method used with fallback_url
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    /**
     * The URL that Twilio will request if an error occurs retrieving or executing
     * the TwiML requested by `Url`.
     * 
     * @param voiceFallbackUrl URL Twilio will request when an error occurs in TwiML
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceFallbackUrl(final URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    /**
     * The URL that Twilio will request if an error occurs retrieving or executing
     * the TwiML requested by `Url`.
     * 
     * @param voiceFallbackUrl URL Twilio will request when an error occurs in TwiML
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceFallbackUrl(final String voiceFallbackUrl) {
        return setVoiceFallbackUrl(Promoter.uriFromString(voiceFallbackUrl));
    }

    /**
     * The HTTP method Twilio will use when requesting the above `Url`. Either `GET`
     * or `POST`.
     * 
     * @param voiceMethod HTTP method used with the voice url
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    /**
     * The URL Twilio will request when this phone number receives a call. The
     * VoiceURL will  no longer be used if a `VoiceApplicationSid` or a `TrunkSid`
     * is set.
     * 
     * @param voiceUrl URL Twilio will request when receiving a call
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    /**
     * The URL Twilio will request when this phone number receives a call. The
     * VoiceURL will  no longer be used if a `VoiceApplicationSid` or a `TrunkSid`
     * is set.
     * 
     * @param voiceUrl URL Twilio will request when receiving a call
     * @return this
     */
    public IncomingPhoneNumberCreator setVoiceUrl(final String voiceUrl) {
        return setVoiceUrl(Promoter.uriFromString(voiceUrl));
    }

    /**
     * Make the request to the Twilio API to perform the create
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created IncomingPhoneNumber
     */
    @Override
    public IncomingPhoneNumber execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.ownerAccountSid + "/IncomingPhoneNumbers.json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("IncomingPhoneNumber creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return IncomingPhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (phoneNumber != null) {
            request.addPostParam("PhoneNumber", phoneNumber.toString());
        }
        
        if (areaCode != null) {
            request.addPostParam("AreaCode", areaCode);
        }
        
        if (apiVersion != null) {
            request.addPostParam("ApiVersion", apiVersion);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (smsApplicationSid != null) {
            request.addPostParam("SmsApplicationSid", smsApplicationSid);
        }
        
        if (smsFallbackMethod != null) {
            request.addPostParam("SmsFallbackMethod", smsFallbackMethod.toString());
        }
        
        if (smsFallbackUrl != null) {
            request.addPostParam("SmsFallbackUrl", smsFallbackUrl.toString());
        }
        
        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
        }
        
        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
        }
        
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
        
        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }
        
        if (voiceApplicationSid != null) {
            request.addPostParam("VoiceApplicationSid", voiceApplicationSid);
        }
        
        if (voiceCallerIdLookup != null) {
            request.addPostParam("VoiceCallerIdLookup", voiceCallerIdLookup.toString());
        }
        
        if (voiceFallbackMethod != null) {
            request.addPostParam("VoiceFallbackMethod", voiceFallbackMethod.toString());
        }
        
        if (voiceFallbackUrl != null) {
            request.addPostParam("VoiceFallbackUrl", voiceFallbackUrl.toString());
        }
        
        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }
        
        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }
    }
}