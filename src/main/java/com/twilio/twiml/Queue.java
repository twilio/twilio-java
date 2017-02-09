package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/queue.
 */
@XmlRootElement(name = "Queue")
public class Queue extends TwiML {

    @XmlAttribute
    private final String url;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String reservationSid;

    @XmlAttribute
    private final String postWorkActivitySid;

    @XmlValue
    private final String queueName;

    // For XML Serialization
    private Queue() {
        this(new Builder(null));
    }

    private Queue(Builder b) {
        this.url = b.url;
        this.method = b.method;
        this.queueName = b.queueName;
        this.reservationSid = b.reservationSid;
        this.postWorkActivitySid = b.postWorkActivitySid;
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getReservationSid() {
        return reservationSid;
    }

    public String getPostWorkActivitySid() {
        return postWorkActivitySid;
    }

    public static class Builder {
        private String url;
        private Method method;
        private String queueName;
        private String reservationSid;
        private String postWorkActivitySid;

        public Builder(String queueName) {
            this.queueName = queueName;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder reservationSid(String reservationSid) {
            this.reservationSid = reservationSid;
            return this;
        }

        public Builder postWorkActivitySid(String postWorkActivitySid) {
            this.postWorkActivitySid = postWorkActivitySid;
            return this;
        }

        public Queue build() {
            return new Queue(this);
        }
    }
}
