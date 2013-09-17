package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Sip extends Verb {


    /**
     * Instantiates a new Sip.
     */
    public Sip(final String uri) {
        super(V_SIP, uri);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_URI);
    }

    /**
     * Instantiates a new Sip Noun with no URI.
     */
    public Sip() {
        this(null);
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    public void setPassword(String password) {
        this.set("password", password);
    }

    /**
     * Sets the url.
     *
     * @param url the waiting url
     */
    public void setUrl(String url){
       this.set("url", url);
    }

    /**
     * Sets the method.
     *
     * @param method the new method
     */
    public void setMethod(String method){
       this.set("method", method);
    }
}
