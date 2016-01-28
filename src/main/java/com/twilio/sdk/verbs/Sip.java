package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Sip extends Verb {

    /**
     * Instantiates a new Sip.
     */
    public Sip(final String uri) {
        super(V_SIP, uri);
        allowedVerbs = new ArrayList<>();
        allowedVerbs.add(Verb.V_URI);
    }

    /**
     * Instantiates a new Sip Noun with no URI.
     */
    public Sip() {
        this(null);
    }

    public void setUsername(final String username) {
        set("username", username);
    }

    public void setPassword(final String password) {
        set("password", password);
    }

    /**
     * Sets the url.
     *
     * @param url the waiting url
     */
    public void setUrl(final String url) {
        set("url", url);
    }

    /**
     * Sets the method.
     *
     * @param method the new method
     */
    public void setMethod(final String method) {
        set("method", method);
    }
}
