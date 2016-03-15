package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/pause}.
 */
@JacksonXmlRootElement
public class Pause extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final Integer length;

    private Pause(Builder b) {
        this.length = b.length;
    }

    public Integer getLength() {
        return length;
    }

    public static class Builder {
        private Integer length;

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Pause build() {
            return new Pause(this);
        }
    }
}
