package com.twilio.credential;

import com.twilio.exception.AuthenticationException;

public class EnvironmentVariableCredentialsProvider implements TwilioCredentialsProvider {

    @Override
    public TwilioCredentials getCredentials() {

        String accountSid = System.getenv("TWILIO_ACCOUNT_SID");
        String authToken  = System.getenv("TWILIO_AUTH_TOKEN");

        if (accountSid.isEmpty() || authToken.isEmpty()) {
            throw new AuthenticationException("Unable to set accountSid and/or authToken from Environment Variable!");
        }

        return new BasicTwilioCredentials(accountSid, authToken);
    }

    @Override
    public void refresh() {
    }

}
