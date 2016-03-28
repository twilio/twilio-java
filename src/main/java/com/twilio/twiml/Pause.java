package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/pause.
 */
@XmlRootElement(name = "Pause")
public class Pause extends TwiML {

    @XmlAttribute
    private final Integer length;

    // For XML Serialization
    private Pause() {
        this(new Builder());
    }

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
