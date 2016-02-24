package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Say extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final int loop;

    @JacksonXmlProperty(isAttribute = true)
    private final String language;

    @JacksonXmlProperty(isAttribute = true)
    private final String voice;

    @JacksonXmlText
    private final String body;

    private Say(int loop, String language, String voice, String body) {
        this.loop = loop;
        this.language = language;
        this.voice = voice;
        this.body = body;
    }

    public int getLoop() {
        return loop;
    }

    public String getLanguage() {
        return language;
    }

    public String getVoice() {
        return voice;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private int loop;
        private String language;
        private String voice;
        private String body;

        public Builder(String body) {
            this.body = body;
        }

        public Builder loop(int loop) {
            this.loop = loop;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder voice(String voice) {
            this.voice = voice;
            return this;
        }

        public Say build() {
            return new Say(loop, language, voice, body);
        }
    }
}
