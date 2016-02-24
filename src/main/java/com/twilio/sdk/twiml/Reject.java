package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Reject extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String reason;

    public Reject(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
