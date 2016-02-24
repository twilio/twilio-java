package com.twilio.sdk.twiml;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Enqueue extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlProperty(isAttribute = true)
    private final String waitUrl;

    @JacksonXmlProperty(isAttribute = true)
    private final String waitUrlMethod;

    @JacksonXmlText
    private final String queueName;

    private Enqueue(String action, String method, String waitUrl, String waitUrlMethod, String queueName) {
        this.action = action;
        this.method = method;
        this.waitUrl = waitUrl;
        this.waitUrlMethod = waitUrlMethod;
        this.queueName = queueName;
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }

    public String getWaitUrl() {
        return waitUrl;
    }

    public String getWaitUrlMethod() {
        return waitUrlMethod;
    }

    public String getQueueName() {
        return queueName;
    }

    public static class Builder {
        private String action;
        private String method;
        private String waitUrl;
        private String waitUrlMethod;
        private String queueName;

        public Builder(String queueName) {
            this.queueName = queueName;
        }

        public void action(String action) {
            this.action = action;
        }

        public void method(String method) {
            this.method = method;
        }

        public void waitUrl(String waitUrl) {
            this.waitUrl = waitUrl;
        }

        public void waitUrlMethod(String waitUrlMethod) {
            this.waitUrlMethod = waitUrlMethod;
        }

        public Enqueue build() {
            return new Enqueue(action, method, waitUrl, waitUrlMethod, queueName);
        }
    }
}
