package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Headers extends Verb {

    /**
     * Instantiates a new Headers.
     */
    public Headers() {
        super(V_HEADERS, null);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_HEADER);
    }
}
