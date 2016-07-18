package com.twilio.jwt.client;

import com.google.common.base.Joiner;

import java.io.UnsupportedEncodingException;

/**
 * Scope token for Incoming Clients.
 */
public class IncomingClientScope implements Scope {

    private static final String SCOPE = Joiner.on(':').join("scope", "client", "incoming");

    private final String clientName;

    public IncomingClientScope(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String getPayload() throws UnsupportedEncodingException {
        String query = Joiner.on('=').join("clientName", this.clientName);
        return Joiner.on('?').join(SCOPE, query);
    }
}
