package com.twilio.credential;

/**
 * @author Jasper Culong
 */

public interface TwilioCredentialsProvider {

    public TwilioCredentials getCredentials();
    void refresh();

}
