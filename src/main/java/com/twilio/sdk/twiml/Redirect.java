package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Redirect extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlProperty
    private final String url;

    private Redirect(String method, String url) {
        this.method = method;
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {
        private String method;
        private String url;

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Redirect build() {
            return new Redirect(method, url);
        }
    }
}
