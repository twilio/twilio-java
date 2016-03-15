package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Redirect extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty
    private final String url;

    private Redirect(Builder b) {
        this.method = b.method;
        this.url = b.url;
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {
        private Method method = Method.POST;
        private String url;

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Redirect build() {
            return new Redirect(this);
        }
    }
}
