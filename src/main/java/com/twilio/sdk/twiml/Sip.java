package com.twilio.sdk.twiml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/sip}.
 */
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

    @JsonIgnore
    private final List<Event> statusCallbackEvents;

    @JacksonXmlProperty(isAttribute = true)
    private final String statusCallbackEvent;

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
        this.statusCallbackEvents = b.statusCallbackEvents;
        this.statusCallback = b.statusCallback;
        this.statusCallbackMethod = b.statusCallbackMethod;
        this.uri = b.uri;

        if (this.statusCallbackEvents != null) {
            this.statusCallbackEvent = Joiner.on(" ").join(Lists.transform(this.statusCallbackEvents, Event.TO_STRING));
        } else {
            this.statusCallbackEvent = null;
        }
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
        private String username;
        private String password;
        private String url;
        private Method method;
        private List<Event> statusCallbackEvents;
        private String statusCallback;
        private Method statusCallbackMethod;
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

        public Sip build() {
            return new Sip(this);
        }
    }
}
