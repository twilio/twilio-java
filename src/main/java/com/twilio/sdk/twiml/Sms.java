package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/sms}.
 */
@JacksonXmlRootElement
public class Sms extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String to;

    @JacksonXmlProperty(isAttribute = true)
    private final String from;

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallback;

    @JacksonXmlText
    private final String message;

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
        private Method method = Method.POST;
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
