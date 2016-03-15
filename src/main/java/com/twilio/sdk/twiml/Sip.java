package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Sip extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String username;

    @JacksonXmlProperty(isAttribute = true)
    private final String password;

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
    private final String uri;

    private Sip(Builder b) {
        this.username = b.username;
        this.password = b.password;
        this.url = b.url;
        this.method = b.method;
        this.statusCallbackEvent = b.statusCallbackEvent;
        this.statusCallback = b.statusCallback;
        this.statusCallbackMethod = b.statusCallbackMethod;
        this.uri = b.uri;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public static class Builder {
        private String username;
        private String password;
        private String url;
        private Method method = Method.POST;
        private Event statusCallbackEvent;
        private String statusCallback;
        private Method statusCallbackMethod = Method.POST;
        private String uri;

        public Builder(String uri) {
            this.uri = uri;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

        public Sip build() {
            return new Sip(this);
        }
    }
}
