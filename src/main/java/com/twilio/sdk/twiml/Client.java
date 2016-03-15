package com.twilio.sdk.twiml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/client}.
 */
@JacksonXmlRootElement
public class Client extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty(isAttribute = true)
    private final String url;

    @JsonIgnore
    private final List<Event> statusCallbackEvents;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallbackEvent;

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
        this.statusCallbackEvents = b.statusCallbackEvents;
        this.statusCallbackMethod = b.statusCallbackMethod;
        this.statusCallback = b.statusCallback;

        if (this.statusCallbackEvents != null) {
            this.statusCallbackEvent = Joiner.on(" ").join(Lists.transform(this.statusCallbackEvents, Event.TO_STRING));
        } else {
            this.statusCallbackEvent = null;
        }
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

    public List<Event> getStatusCallbackEvents() {
        return statusCallbackEvents;
    }

    public Method getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public String getStatusCallback() {
        return statusCallback;
    }

    public static class Builder {
        private Method method;
        private String url;
        private String name;
        private List<Event> statusCallbackEvents;
        private Method statusCallbackMethod;
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

        public Builder statusCallbackEvents(List<Event> statusCallbackEvents) {
            this.statusCallbackEvents = statusCallbackEvents;
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
