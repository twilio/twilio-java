package com.twilio.sdk.twiml;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/enqueue}.
 */
@JacksonXmlRootElement
public class Enqueue extends TwiML {

    private static final String DEFAULT_WAIT_URL = "http://s3.amazonaws.com/com.twilio.sounds.music/index.xml";

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty(isAttribute = true)
    private final String waitUrl;

    @JacksonXmlProperty(isAttribute = true)
    private final Method waitUrlMethod;

    @JacksonXmlProperty(isAttribute = true)
    private final String workflowSid;

    @JacksonXmlText
    private final String queueName;

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

        public Builder(String queueName) {
            this.queueName = queueName;
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
