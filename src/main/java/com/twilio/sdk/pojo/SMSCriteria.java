package com.twilio.sdk.pojo;

public class SMSCriteria {
    public final String fullUrl;
    public final String toPhoneNumber;
    public final String fromNumber;
    public final String body;
    public String callbackUrl;//not mandatory

    public SMSCriteria(String fullUrl, String toPhoneNumber, String fromNumber, String body) {
        this.fullUrl = fullUrl;
        this.toPhoneNumber = toPhoneNumber;
        this.fromNumber = fromNumber;
        this.body = body;

    }

    public SMSCriteria(String fullUrl, String toPhoneNumber, String fromNumber, String body, String callbackUrl) {
        this.fullUrl = fullUrl;
        this.toPhoneNumber = toPhoneNumber;
        this.fromNumber = fromNumber;
        this.body = body;
        this.callbackUrl = callbackUrl;
    }


}
