package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Pause extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final int length;

    private Pause(Builder b) {
        this.length = b.length;
    }

    public int getLength() {
        return length;
    }

    public static class Builder {
        private int length = 1;

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Pause build() {
            return new Pause(this);
        }
    }
}
