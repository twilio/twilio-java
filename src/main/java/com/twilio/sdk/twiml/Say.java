package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/say}.
 */
@JacksonXmlRootElement
public class Say extends TwiML {

    public enum Voice {
        MAN("man"),
        WOMAN("woman"),
        ALICE("alice");

        private final String value;

        Voice(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Language {
        EN("en"),
        EN_GB("en-gb"),
        ES("es"),
        FR("fr"),
        DE("de");

        private final String value;

        Language(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @JacksonXmlProperty(isAttribute = true)
    private final int loop;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Language language;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Voice voice;

    @JacksonXmlText
    private final String body;

    private Say(Builder b) {
        this.loop = b.loop;
        this.language = b.language;
        this.voice = b.voice;
        this.body = b.body;
    }

    public int getLoop() {
        return loop;
    }

    public Language getLanguage() {
        return language;
    }

    public Voice getVoice() {
        return voice;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private int loop = 1;
        private Language language = Language.EN;
        private Voice voice = Voice.MAN;
        private String body;

        public Builder(String body) {
            this.body = body;
        }

        public Builder loop(int loop) {
            this.loop = loop;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder voice(Voice voice) {
            this.voice = voice;
            return this;
        }

        public Say build() {
            return new Say(this);
        }
    }
}
