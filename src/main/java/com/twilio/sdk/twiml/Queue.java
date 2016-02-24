package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Queue extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String url;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlText
    private final String queueName;

    private Queue(String url, String method, String queueName) {
        this.url = url;
        this.method = method;
        this.queueName = queueName;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getQueueName() {
        return queueName;
    }

    public static class Builder {
        private String url;
        private String method;
        private String queueName;

        public Builder(String queueName) {
            this.queueName = queueName;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Queue build() {
            return new Queue(url, method, queueName);
        }
    }
}
