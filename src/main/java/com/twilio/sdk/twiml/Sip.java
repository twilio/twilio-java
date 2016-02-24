package com.twilio.sdk.twiml;

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
    private final String method;

    @JacksonXmlProperty(localName = "Uri")
    private final Uri obj;

    @JacksonXmlText
    private final String uri;

    private Sip(String username, String password, String url, String method, Uri obj, String uri) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.method = method;
        this.obj = obj;
        this.uri = uri;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Returns the waiting url.
     * @return the waiting url
     */
    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Uri getUriObj() {
        return obj;
    }

    public String getUri() {
        return uri;
    }

    public static class Builder {
        private String username;
        private String password;
        private String url;
        private String method;
        private Uri obj;
        private String uri;

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

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder uriObj(Uri obj) {
            this.obj = obj;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Sip build() {
            return new Sip(username, password, url, method, obj, uri);
        }
    }
}
