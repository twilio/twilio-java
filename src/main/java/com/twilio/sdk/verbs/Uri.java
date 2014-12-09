package com.twilio.sdk.verbs;

public class Uri extends Verb {

    /**
     * Instantiates a new Uri to dial with Sip
     */
    public Uri(final String uri) {
        super(V_URI, uri);
        allowedVerbs = null;
    }

    public void setUsername(final String username) {
        set("username", username);
    }

    public void setPassword(final String password) {
        set("password", password);
    }

}

