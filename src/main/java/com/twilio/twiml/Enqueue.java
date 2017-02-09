package com.twilio.twiml;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/enqueue.
 */
@XmlRootElement(name = "Enqueue")
public class Enqueue extends TwiML {

    @XmlAttribute
    private final String action;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String waitUrl;

    @XmlAttribute
    private final Method waitUrlMethod;

    @XmlAttribute
    private final String workflowSid;

    @XmlValue
    private final String queueName;

    // For XML Serialization
    private Enqueue() {
        this(new Builder());
    }

    private Enqueue(Builder b) {
        this.action = b.action;
        this.method = b.method;
        this.waitUrl = b.waitUrl;
        this.waitUrlMethod = b.waitUrlMethod;
        this.workflowSid = b.workflowSid;
        this.queueName = b.queueName;
    }

    public String getAction() {
        return action;
    }

    public Method getMethod() {
        return method;
    }

    public String getWaitUrl() {
        return waitUrl;
    }

    public Method getWaitUrlMethod() {
        return waitUrlMethod;
    }

    public String getWorkflowSid() {
        return workflowSid;
    }

    public String getQueueName() {
        return queueName;
    }

    public static class Builder {
        private String action;
        private Method method;
        private String waitUrl;
        private Method waitUrlMethod;
        private String workflowSid;
        private String queueName;

        public Builder queueName(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder waitUrl(String waitUrl) {
            this.waitUrl = waitUrl;
            return this;
        }

        public Builder waitUrlMethod(Method waitUrlMethod) {
            this.waitUrlMethod = waitUrlMethod;
            return this;
        }

        public Builder workflowSid(String workflowSid) {
            this.workflowSid = workflowSid;
            return this;
        }

        public Enqueue build() {
            return new Enqueue(this);
        }
    }
}
