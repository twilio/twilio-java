package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/queue.
 */
@JacksonXmlRootElement
public class Queue extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String url;

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty(isAttribute = true)
    private final String reservationSid;

    @JacksonXmlProperty(isAttribute = true)
    private final String postWorkActivitySid;

    @JacksonXmlText
    private final String queueName;

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
