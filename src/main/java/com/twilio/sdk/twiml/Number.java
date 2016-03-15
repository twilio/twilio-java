package com.twilio.sdk.twiml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/number}.
 */
@JacksonXmlRootElement
public class Number extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String sendDigits;

    @JacksonXmlProperty(isAttribute = true)
    private final String url;

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JsonIgnore
    private final List<Event> statusCallbackEvents;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallbackEvent;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallback;

    @JacksonXmlProperty(isAttribute = true)
    private final Method statusCallbackMethod;

    @JacksonXmlText
    private final String number;

    private Number(Builder b) {
        this.sendDigits = b.sendDigits;
        this.url = b.url;
        this.method = b.method;
        this.number = b.number;
        this.statusCallbackEvents = b.statusCallbackEvents;
        this.statusCallback = b.statusCallback;
        this.statusCallbackMethod = b.statusCallbackMethod;

        if (this.statusCallbackEvents != null) {
            this.statusCallbackEvent = Joiner.on(" ").join(Lists.transform(this.statusCallbackEvents, Event.TO_STRING));
        } else {
            this.statusCallbackEvent = null;
        }
    }

    public String getSendDigits() {
        return sendDigits;
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public String getNumber() {
        return number;
    }

    public List<Event> getStatusCallbackEvents() {
        return statusCallbackEvents;
    }

    public String getStatusCallback() {
        return statusCallback;
    }

    public Method getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public static class Builder {
        private String sendDigits;
        private String url;
        private Method method;
        private String number;
        private List<Event> statusCallbackEvents;
        private String statusCallback;
        private Method statusCallbackMethod;

        public Builder(String number) {
            this.number = number;
        }

        public Builder sendDigits(String sendDigits) {
            this.sendDigits = sendDigits;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder statusCallbackEvents(List<Event> statusCallbackEvents) {
            this.statusCallbackEvents = statusCallbackEvents;
            return this;
        }

        public Builder statusCallback(String statusCallback) {
            this.statusCallback = statusCallback;
            return this;
        }

        public Builder statusCallbackMethod(Method statusCallbackMethod) {
            this.statusCallbackMethod = statusCallbackMethod;
            return this;
        }

        public Number build() {
            return new Number(this);
        }
    }
}
