package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/reject.
 */
@XmlRootElement(name = "Reject")
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

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Reason reason;

    // For XML Serialization
    private Reject() {
        this(new Builder());
    }

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
