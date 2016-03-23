package com.twilio.sdk.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/conference.
 */
@XmlRootElement(name = "Conference")
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

    @XmlAttribute
    private final Boolean muted;

    @XmlAttribute
    private final Boolean startConferenceOnEnter;

    @XmlAttribute
    private final Boolean endConferenceOnExit;

    @XmlAttribute
    private final Integer maxParticipants;

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Beep beep;

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Record record;

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Trim trim;

    @XmlAttribute
    private final Method waitMethod;

    @XmlAttribute
    private final String waitUrl;

    @XmlAttribute
    private final String eventCallbackUrl;

    @XmlValue
    private final String name;

    // For XML Serialization
    private Conference() {
        this(new Builder(null));
    }

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

    public Boolean isMuted() {
        return muted;
    }

    public Boolean isStartConferenceOnEnter() {
        return startConferenceOnEnter;
    }

    public Boolean isEndConferenceOnExit() {
        return endConferenceOnExit;
    }

    public Integer getMaxParticipants() {
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
        private Boolean muted;
        private Boolean startConferenceOnEnter;
        private Boolean endConferenceOnExit;
        private Integer maxParticipants;
        private Beep beep;
        private Record record;
        private Trim trim;
        private Method waitMethod;
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
