package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sms/message.
 */
@XmlRootElement(name = "Message")
public class Message extends TwiML {

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

    @XmlElement(name = "Body")
    private final Body body;

    @XmlElement(name = "Media")
    private final Media media;

    // For XML Serialization
    private Message() {
        this(new Builder());
    }

    private Message(Builder b) {
        this.to = b.to;
        this.from = b.from;
        this.method = b.method;
        this.action = b.action;
        this.statusCallback = b.statusCallback;
        this.body = b.body;
        this.media = b.media;
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

    public static class Builder {
        private String to;
        private String from;
        private Method method;
        private String action;
        private String statusCallback;
        private Body body;
        private Media media;

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

        public Message build() {
            return new Message(this);
        }
    }
}
