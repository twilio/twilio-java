package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Sip extends Verb {


    /**
     * Instantiates a new Sip.
     */
    public Sip() {
        super(V_SIP, null);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_URI);
        this.allowedVerbs.add(Verb.V_HEADERS);
    }
}
