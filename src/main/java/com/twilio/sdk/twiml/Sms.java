package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Sms {

    @JacksonXmlProperty(isAttribute = true)
    private final String to;

    @JacksonXmlProperty(isAttribute = true)
    private final String from;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallback;

    @JacksonXmlText
    private final String message;

    public Sms(String to, String from, String method, String action, String statusCallback, String message) {
        this.to = to;
        this.from = from;
        this.method = method;
        this.action = action;
        this.statusCallback = statusCallback;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getMethod() {
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
        private String method;
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

        public Builder method(String method) {
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
            return new Sms(to, from, method, action, statusCallback, message);
        }
    }
}
