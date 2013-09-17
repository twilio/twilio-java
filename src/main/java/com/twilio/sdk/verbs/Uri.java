package com.twilio.sdk.verbs;

public class Uri extends Verb {

    /**
     * Instantiates a new Uri to dial with Sip
     */
    public Uri(String uri) {
        super(V_URI, uri);
        this.allowedVerbs = null;
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    public void setPassword(String password) {
        this.set("password", password);
    }

}

