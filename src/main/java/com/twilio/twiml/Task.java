package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for TaskRouter Task.
 */
@XmlRootElement(name = "Task")
public class Task extends TwiML {

    @XmlAttribute
    private final Integer priority;

    @XmlAttribute
    private final Integer timeout;

    @XmlValue
    private final String data;

    // For XML Serialization
    private Task() {
        this(new Builder());
    }

    private Task(Builder b) {
        this.priority = b.priority;
        this.timeout = b.timeout;
        this.data = b.data;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getData() {
        return data;
    }

    public static class Builder {
        private Integer priority;
        private Integer timeout;
        private String data;

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder data(String data) {
            this.data = data;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
