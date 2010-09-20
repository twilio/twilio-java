package com.twilio.sdk.verbs;

/** 
 * * Created by IntelliJ IDEA. 
 * * User: Wael 
 * * Date: Sep 19, 2010 
 * * Time: 12:32:22 AM 
 * */ 
public class Reject extends Verb { 
    public Reject() { 
        super(V_REJECT,null); 
    }

    public void setReason(String reason) { 
        this.set("reason", reason); 
    } 
}
