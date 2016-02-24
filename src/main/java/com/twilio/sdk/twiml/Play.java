package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Play extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final int loop;

    @JacksonXmlText
    private final String body;

    private Play(int loop, String body) {
        this.loop = loop;
        this.body = body;
    }

    public int getLoop() {
        return loop;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private int loop;
        private String body;

        public Builder(String body) {
            this.body = body;
        }

        public Builder loop(int loop) {
            this.loop = loop;
            return this;
        }

        public Play build() {
            return new Play(loop, body);
        }
    }
}
