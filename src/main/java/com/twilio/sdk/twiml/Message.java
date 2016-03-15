package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Message extends TwiML {

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

    @JacksonXmlProperty(localName = "Body")
    private final Body body;

    @JacksonXmlProperty(localName = "Media")
    private final Media media;

    @JacksonXmlText
    private final String message;

    private Message(Builder b) {
        this.to = b.to;
        this.from = b.from;
        this.method = b.method;
        this.action = b.action;
        this.statusCallback = b.statusCallback;
        this.body = b.body;
        this.media = b.media;
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

    public Body getBody() {
        return body;
    }

    public Media getMedia() {
        return media;
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
        private Body body;
        private Media media;
        private String message;

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

        public Builder body(Body body) {
            this.body = body;
            return this;
        }

        public Builder media(Media media) {
            this.media = media;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
