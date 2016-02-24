package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Conference extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final boolean muted;

    @JacksonXmlProperty(isAttribute = true)
    private final boolean startConferenceOnEnter;

    @JacksonXmlProperty(isAttribute = true)
    private final boolean endConferenceonExit;

    @JacksonXmlProperty(isAttribute = true)
    private final int maxParticipants;

    @JacksonXmlProperty(isAttribute = true)
    private final String beep;

    @JacksonXmlProperty(isAttribute = true)
    private final String waitMethod;

    @JacksonXmlProperty(isAttribute = true)
    private final String waitUrl;

    @JacksonXmlText
    private final String name;

    private Conference(
        boolean muted,
        boolean startConferenceOnEnter,
        boolean endConferenceonExit,
        int maxParticipants,
        String beep,
        String waitMethod,
        String waitUrl,
        String name
    ) {
        this.muted = muted;
        this.startConferenceOnEnter = startConferenceOnEnter;
        this.endConferenceonExit = endConferenceonExit;
        this.maxParticipants = maxParticipants;
        this.beep = beep;
        this.waitMethod = waitMethod;
        this.waitUrl = waitUrl;
        this.name = name;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isStartConferenceOnEnter() {
        return startConferenceOnEnter;
    }

    public boolean isEndConferenceonExit() {
        return endConferenceonExit;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public String getBeep() {
        return beep;
    }

    public String getWaitMethod() {
        return waitMethod;
    }

    public String getWaitUrl() {
        return waitUrl;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private boolean muted;
        private boolean startConferenceOnEnter;
        private boolean endConferenceonExit;
        private int maxParticipants;
        private String beep;
        private String waitMethod;
        private String waitUrl;
        private String name;

        public Builder(String name) {
            this.name = name;
        }

        public Builder muted(boolean muted) {
            this.muted = muted;
            return this;
        }

        public Builder startConferenceOnEnter(boolean startConferenceOnEnter) {
            this.startConferenceOnEnter = startConferenceOnEnter;
            return this;
        }

        public Builder endConferenceonExit(boolean endConferenceonExit) {
            this.endConferenceonExit = endConferenceonExit;
            return this;
        }

        public Builder maxParticipants(int maxParticipants) {
            this.maxParticipants = maxParticipants;
            return this;
        }

        public Builder beep(String beep) {
            this.beep = beep;
            return this;
        }

        public Builder waitMethod(String waitMethod) {
            this.waitMethod = waitMethod;
            return this;
        }

        public Builder waitUrl(String waitUrl) {
            this.waitUrl = waitUrl;
            return this;
        }

        public Conference build() {
            return new Conference(
                muted,
                startConferenceOnEnter,
                endConferenceonExit,
                maxParticipants,
                beep,
                waitMethod,
                waitUrl,
                name
            );
        }
    }
}
