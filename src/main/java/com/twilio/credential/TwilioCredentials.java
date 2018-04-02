package com.twilio.credential;

/**
 * Validator used to check whether given string is
 *
 * @author Jasper Culong
 */

public interface TwilioCredentials {

    public String getAccountSid();
    public String getAuthToken();

}
