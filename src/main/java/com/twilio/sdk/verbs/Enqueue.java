package com.twilio.sdk.verbs;

public class Enqueue extends Verb {

    public Enqueue(final String queueName) {
        super(Verb.V_ENQUEUE, queueName);
    }

    public void setAction(final String action) {
        set("action", action);
    }

    public void setMethod(final String method) {
        set("method", method);
    }

    public void setWaitUrl(final String waitUrl) {
        set("waitUrl", waitUrl);
    }

    public void setWaitUrlMethod(final String waitUrlMethod) {
        set("waitUrlMethod", waitUrlMethod);
    }
}
