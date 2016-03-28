package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sms.
 */
@XmlRootElement(name = "Sms")
public class Sms extends TwiML {

    @XmlAttribute
    private final String to;

    @XmlAttribute
    private final String from;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String action;

    @XmlAttribute
    private final String statusCallback;

    @XmlValue
    private final String message;

    // For XML Serialization
    private Sms() {
        this(new Builder(null));
    }

    private Sms(Builder b) {
        this.to = b.to;
        this.from = b.from;
        this.method = b.method;
        this.action = b.action;
        this.statusCallback = b.statusCallback;
        this.message = b.message;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public Method getMethod() {
        return method;
    }

    public String getAction() {
        return action;
    }

    public String getStatusCallback() {
        return statusCallback;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private String to;
        private String from;
        private Method method;
        private String action;
        private String statusCallback;
        private String message;

        public Builder(String message) {
            this.message = message;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder statusCallback(String statusCallback) {
            this.statusCallback = statusCallback;
            return this;
        }

        public Sms build() {
            return new Sms(this);
        }
    }
}
