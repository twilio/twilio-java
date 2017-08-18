package com.twilio.twiml;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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

    @SuppressWarnings("checkstyle:indentation")
    @XmlElements({
        @XmlElement(name = "Media", type = Media.class)
    })
    private final List<Media> media;

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
        this.media = Lists.newArrayList(b.media);
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

    public List<Media> getMedia() {
        return media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;
        return Objects.equal(to, message.to) &&
            Objects.equal(from, message.from) &&
            method == message.method &&
            Objects.equal(action, message.action) &&
            Objects.equal(statusCallback, message.statusCallback) &&
            Objects.equal(body, message.body) &&
            Objects.equal(media, message.media);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(to, from, method, action, statusCallback, body, media);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("to", to)
            .add("from", from)
            .add("method", method)
            .add("action", action)
            .add("statusCallback", statusCallback)
            .add("body", body)
            .add("media", media)
            .toString();
    }

    public static class Builder {
        private String to;
        private String from;
        private Method method;
        private String action;
        private String statusCallback;
        private Body body;
        private List<Media> media = Lists.newArrayList();

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
            this.media.add(media);
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
