package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

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

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Event statusCallbackEvent;

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
        this.statusCallbackEvent = b.statusCallbackEvent;
        this.statusCallback = b.statusCallback;
        this.statusCallbackMethod = b.statusCallbackMethod;
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

    public Event getStatusCallbackEvent() {
        return statusCallbackEvent;
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
        private Method method = Method.POST;
        private String number;
        private Event statusCallbackEvent;
        private String statusCallback;
        private Method statusCallbackMethod = Method.POST;

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

        public Builder statusCallbackEvent(Event statusCallbackEvent) {
            this.statusCallbackEvent = statusCallbackEvent;
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
