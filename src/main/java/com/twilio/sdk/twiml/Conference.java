package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/conference}.
 */
@JacksonXmlRootElement
public class Conference extends TwiML {

    public enum Beep {
        TRUE("true"),
        FALSE("false"),
        ON_ENTER("onEnter"),
        ON_EXIT("onExit");

        private final String value;

        Beep(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Record {
        DO_NOT_RECORD("do-not-record"),
        RECORD_FROM_START("record-from-start");

        private final String value;

        private Record(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @JacksonXmlProperty(isAttribute = true)
    private final boolean muted;

    @JacksonXmlProperty(isAttribute = true)
    private final boolean startConferenceOnEnter;

    @JacksonXmlProperty(isAttribute = true)
    private final boolean endConferenceOnExit;

    @JacksonXmlProperty(isAttribute = true)
    private final int maxParticipants;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Beep beep;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Record record;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Trim trim;

    @JacksonXmlProperty(isAttribute = true)
    private final Method waitMethod;

    @JacksonXmlProperty(isAttribute = true)
    private final String waitUrl;

    @JacksonXmlProperty(isAttribute = true)
    private final String eventCallbackUrl;

    @JacksonXmlText
    private final String name;

    private Conference(Builder b) {
        this.muted = b.muted;
        this.startConferenceOnEnter = b.startConferenceOnEnter;
        this.endConferenceOnExit = b.endConferenceOnExit;
        this.maxParticipants = b.maxParticipants;
        this.beep = b.beep;
        this.record = b.record;
        this.trim = b.trim;
        this.waitMethod = b.waitMethod;
        this.waitUrl = b.waitUrl;
        this.eventCallbackUrl = b.eventCallbackUrl;
        this.name = b.name;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isStartConferenceOnEnter() {
        return startConferenceOnEnter;
    }

    public boolean isEndConferenceOnExit() {
        return endConferenceOnExit;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public Beep getBeep() {
        return beep;
    }

    public Record getRecord() {
        return record;
    }

    public Trim getTrim() {
        return trim;
    }

    public Method getWaitMethod() {
        return waitMethod;
    }

    public String getWaitUrl() {
        return waitUrl;
    }

    public String getEventCallbackUrl() {
        return eventCallbackUrl;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private boolean muted = false;
        private boolean startConferenceOnEnter = true;
        private boolean endConferenceOnExit = false;
        private int maxParticipants = 40;
        private Beep beep = Beep.TRUE;
        private Record record = Record.DO_NOT_RECORD;
        private Trim trim = Trim.TRIM_SILENCE;
        private Method waitMethod = Method.POST;
        private String waitUrl;
        private String eventCallbackUrl;
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

        public Builder endConferenceOnExit(boolean endConferenceOnExit) {
            this.endConferenceOnExit = endConferenceOnExit;
            return this;
        }

        public Builder maxParticipants(int maxParticipants) {
            this.maxParticipants = maxParticipants;
            return this;
        }

        public Builder beep(Beep beep) {
            this.beep = beep;
            return this;
        }

        public Builder record(Record record) {
            this.record = record;
            return this;
        }

        public Builder trim(Trim trim) {
            this.trim = trim;
            return this;
        }

        public Builder waitMethod(Method waitMethod) {
            this.waitMethod = waitMethod;
            return this;
        }

        public Builder waitUrl(String waitUrl) {
            this.waitUrl = waitUrl;
            return this;
        }

        public Builder eventCallbackUrl(String eventCallbackUrl) {
            this.eventCallbackUrl = eventCallbackUrl;
            return this;
        }

        public Conference build() {
            return new Conference(this);
        }
    }
}
