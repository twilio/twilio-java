package com.twilio.credential;

import com.twilio.exception.AuthenticationException;

public class SystemPropertiesCredentialsProvider implements TwilioCredentialsProvider {


    @Override
    public TwilioCredentials getCredentials() {

        String accountSid = System.getenv("twilio.accountSid");
        String authToken = System.getenv("twilio.authToken");

        if (accountSid.isEmpty() || authToken.isEmpty()) {
            throw new AuthenticationException("Unable to set accountSid and/or authToken from System Properties!");
        }

        return new BasicTwilioCredentials(accountSid, authToken);

    }

    @Override
    public void refresh() {
    }

}
