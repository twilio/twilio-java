package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/reject.
 */
@JacksonXmlRootElement
public class Reject extends TwiML {

    public enum Reason {
        REJECTED("rejected"),
        BUSY("busy");

        private final String value;

        Reason(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Reason reason;

    private Reject(Builder b) {
        this.reason = b.reason;
    }

    public Reason getReason() {
        return reason;
    }

    public static class Builder {
        private Reason reason;

        public Builder reason(Reason reason) {
            this.reason = reason;
            return this;
        }

        public Reject build() {
            return new Reject(this);
        }
    }
}
