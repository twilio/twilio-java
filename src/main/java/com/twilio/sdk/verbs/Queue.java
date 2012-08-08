package com.twilio.sdk.verbs;

public class Queue extends Verb {

    public Queue(final String queueName) {
        super(Verb.V_QUEUE, queueName);
    }

    public void setUrl(final String url) {
        this.set("url", url);
    }
    
    public void setMethod(final String method) {
        this.set("method", method);
    }
}
