package com.twilio.credential;

/**
 * Validator used to check whether given string is
 *
 * @author Jasper Culong
 */

public interface TwilioCredentialsProvider {

    public TwilioCredentials getCredentials();
    void refresh();

}
