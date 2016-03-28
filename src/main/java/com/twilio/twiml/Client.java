package com.twilio.twiml;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/client.
 */
@XmlRootElement(name = "Client")
public class Client extends TwiML {

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String url;

    @XmlAttribute
    private final String statusCallbackEvent;

    @XmlAttribute
    private final Method statusCallbackMethod;

    @XmlAttribute
    private final String statusCallback;

    @XmlValue
    private final String name;

    private final List<Event> statusCallbackEvents;

    // For XML Serialization
    private Client() {
        this(new Builder(null));
    }

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
