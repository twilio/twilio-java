package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Pause extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final int length;

    public Pause(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
