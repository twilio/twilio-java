package com.twilio.twiml;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sip.
 */
@XmlRootElement(name = "Sip")
public class Sip extends TwiML {

    @XmlAttribute
    private final String username;

    @XmlAttribute
    private final String password;

    @XmlAttribute
    private final String url;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String statusCallbackEvent;

    @XmlAttribute
    private final String statusCallback;

    @XmlAttribute
    private final Method statusCallbackMethod;

    @XmlValue
    private final String uri;

    private final List<Event> statusCallbackEvents;

    // For XML Serialization
    private Sip() {
        this(new Builder(null));
    }

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
