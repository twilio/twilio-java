package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Sip extends Verb {


    /**
     * Instantiates a new dial.
     *
     * @param number the number
     */
    public Sip() {
        super(V_SIP, number);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_NUMBER);
        this.allowedVerbs.add(Verb.V_HEADERS);
        this.allowedVerbs.add(Verb.V_CLIENT);
        this.allowedVerbs.add(Verb.V_QUEUE);
    }

    /**
     * Instantiates a new dial.
     */
    public Dial() {
        this(null);
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

    /**
     * Sets the timeout.
     *
     * @param i the new timeout
     */
    public void setTimeout(int i){
       this.set("timeout", Integer.toString(i));
    }

    /**
     * Sets the hangup on star.
     *
     * @param f the new hangup on star
     */
    public void setHangupOnStar(boolean f){
        if(f)
            this.set("hangupOnStar", "true");
        else
            this.set("hangupOnStar", "false");
    }

    /**
     * Sets the time limit.
     *
     * @param i the new time limit
     */
    public void setTimeLimit(int i){
        this.set("timeLimit", Integer.toString(i));
    }

    /**
     * Sets the caller id.
     *
     * @param callerId the new caller id
     */
    public void setCallerId(String callerId){
       this.set(" callerId ", callerId);
    }

}
