package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/client}.
 */
@JacksonXmlRootElement
public class Client extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty(isAttribute = true)
    private final String url;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Event statusCallbackEvent;

    @JacksonXmlProperty(isAttribute = true)
    private final Method statusCallbackMethod;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallback;

    @JacksonXmlText
    private final String name;

    private Client(Builder b) {
        this.method = b.method;
        this.url = b.url;
        this.name = b.name;
        this.statusCallbackEvent = b.statusCallbackEvent;
        this.statusCallbackMethod = b.statusCallbackMethod;
        this.statusCallback = b.statusCallback;
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Event getStatusCallbackEvent() {
        return statusCallbackEvent;
    }

    public Method getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public String getStatusCallback() {
        return statusCallback;
    }

    public static class Builder {
        private Method method = Method.POST;
        private String url;
        private String name;
        private Event statusCallbackEvent;
        private Method statusCallbackMethod = Method.POST;
        private String statusCallback;

        public Builder(String name) {
            this.name = name;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder statusCallbackEvent(Event statusCallbackEvent) {
            this.statusCallbackEvent = statusCallbackEvent;
            return this;
        }

        public Builder statusCallbackMethod(Method statusCallbackMethod) {
            this.statusCallbackMethod = statusCallbackMethod;
            return this;
        }

        public Builder statusCallback(String statusCallback) {
            this.statusCallback = statusCallback;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
