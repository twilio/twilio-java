package com.twilio.sdk;

public class TwilioPricingClient extends TwilioClient {

    public static final String DEFAULT_VERSION = "v1";

    public TwilioPricingClient(final String accountSid, final String authToken) {
        super(accountSid, authToken, "https://pricing.twilio.com");
    }
}
