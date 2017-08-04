package com.twilio.twiml;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Queue queue = (Queue) o;
        return Objects.equal(url, queue.url) &&
            method == queue.method &&
            Objects.equal(reservationSid, queue.reservationSid) &&
            Objects.equal(postWorkActivitySid, queue.postWorkActivitySid) &&
            Objects.equal(queueName, queue.queueName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, method, reservationSid, postWorkActivitySid, queueName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("url", url)
            .add("method", method)
            .add("reservationSid", reservationSid)
            .add("postWorkActivitySid", postWorkActivitySid)
            .add("queueName", queueName)
            .toString();
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
