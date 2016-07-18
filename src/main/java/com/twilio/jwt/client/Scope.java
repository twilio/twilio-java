package com.twilio.jwt.client;

import java.io.UnsupportedEncodingException;

/**
 * Token to access features of Twilio Client.
 */
public interface Scope {

    public String getPayload() throws UnsupportedEncodingException;

}
