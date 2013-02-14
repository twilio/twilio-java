package com.twilio.sdk.verbs;

import java.util.ArrayList;

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

    /**
     * Sets the action.
     *
     * @param url the new action
     */
    public void setAction(String url){
       this.set("action", url);
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

