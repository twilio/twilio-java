package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "Uri")
public class Uri extends TwiML {

    @JacksonXmlProperty(localName = "username", isAttribute = true)
    private final String username;

    @JacksonXmlProperty(localName = "password", isAttribute = true)
    private final String password;

    @JacksonXmlText
    private final String uri;

    private Uri(String username, String password, String uri) {
        this.username = username;
        this.password = password;
        this.uri = uri;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUri() {
        return uri;
    }

    public static class Builder {
        private String username;
        private String password;
        private String uri;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Uri build() {
            return new Uri(username, password, uri);
        }
    }
}
