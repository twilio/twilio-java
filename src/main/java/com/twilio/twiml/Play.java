package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/play.
 */
@XmlRootElement(name = "Play")
public class Play extends TwiML {

    @XmlAttribute
    private final Integer loop;

    @XmlAttribute
    private final String digits;

    @XmlValue
    private final String body;

    // For XML Serialization
    private Play() {
        this(new Builder(null));
    }

    private Play(Builder b) {
        this.loop = b.loop;
        this.digits = b.digits;
        this.body = b.body;
    }

    public Integer getLoop() {
        return loop;
    }

    public String getDigits() {
        return digits;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private Integer loop;
        private String digits;
        private String body;

        public Builder() {

        }

        public Builder(String body) {
            this.body = body;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder loop(int loop) {
            this.loop = loop;
            return this;
        }

        public Builder digits(String digits) {
            this.digits = digits;
            return this;
        }

        public Play build() {
            return new Play(this);
        }
    }
}
