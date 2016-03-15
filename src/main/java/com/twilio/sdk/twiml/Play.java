package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/play}.
 */
@JacksonXmlRootElement
public class Play extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final int loop;

    @JacksonXmlProperty(isAttribute = true)
    private final int digits;

    @JacksonXmlText
    private final String body;

    private Play(Builder b) {
        this.loop = b.loop;
        this.digits = b.digits;
        this.body = b.body;
    }

    public int getLoop() {
        return loop;
    }

    public int getDigits() {
        return digits;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private int loop = 1;
        private int digits;
        private String body;

        public Builder(String body) {
            this.body = body;
        }

        public Builder loop(int loop) {
            this.loop = loop;
            return this;
        }

        public Builder digits(int digits) {
            this.digits = digits;
            return this;
        }

        public Play build() {
            return new Play(this);
        }
    }
}
