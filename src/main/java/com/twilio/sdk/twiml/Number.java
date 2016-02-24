package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Number extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final String sendDigits;

    @JacksonXmlProperty(isAttribute = true)
    private final String url;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlText
    private final String number;

    private Number(String sendDigits, String url, String method, String number) {
        this.sendDigits = sendDigits;
        this.url = url;
        this.method = method;
        this.number = number;
    }

    public String getSendDigits() {
        return sendDigits;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getNumber() {
        return number;
    }

    public static class Builder {
        private String sendDigits;
        private String url;
        private String method;
        private String number;

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

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Number build() {
            return new Number(sendDigits, url, method, number);
        }
    }
}
