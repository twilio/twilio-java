package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Header extends Verb {


    /**
     * Instantiates a new Header to send with a TwiML request.
     */
    public Header() {
        super(V_HEADER, null);
        this.allowedVerbs = null;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public void setValue(String value) {
        this.set("value", value);
    }
}
