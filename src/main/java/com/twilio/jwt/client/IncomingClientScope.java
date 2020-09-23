package com.twilio.jwt.client;

import java.io.UnsupportedEncodingException;

/**
 * Scope token for Incoming Clients.
 */
public class IncomingClientScope implements Scope {

    private static final String SCOPE = String.join(":", "scope", "client", "incoming");

    private final String clientName;

    public IncomingClientScope(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String getPayload() throws UnsupportedEncodingException {
        String query = String.join("=", "clientName", this.clientName);
        return String.join("?", SCOPE, query);
    }
}
