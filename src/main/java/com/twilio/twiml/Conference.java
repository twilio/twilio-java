package com.twilio.twiml;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public enum ConferenceEvent {
        START("start"),
        END("end"),
        JOIN("join"),
        LEAVE("leave"),
        MUTE("mute"),
        HOLD("hold");

        private final String value;

        ConferenceEvent(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static final Function<ConferenceEvent, String> TO_STRING = new Function<ConferenceEvent, String>() {
            @Override
            public String apply(ConferenceEvent event) {
                return event.toString();
            }
        };
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

    @XmlAttribute
    private final String statusCallbackEvent;

    @XmlAttribute
    private final Method statusCallbackMethod;

    @XmlAttribute
    private final String statusCallback;

    @XmlAttribute
    private final String recordingStatusCallback;

    @XmlAttribute
    private final Method recordingStatusCallbackMethod;

    @XmlValue
    private final String name;

    @XmlAnyAttribute
    private Map<QName, String> options;

    private final List<ConferenceEvent> statusCallbackEvents;

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
        this.statusCallbackEvents = b.statusCallbackEvents;
        this.statusCallbackMethod = b.statusCallbackMethod;
        this.statusCallback = b.statusCallback;
        this.recordingStatusCallback = b.recordingStatusCallback;
        this.recordingStatusCallbackMethod = b.recordingStatusCallbackMethod;
        this.options = Maps.newHashMap(b.options);

        if (this.statusCallbackEvents != null) {
            this.statusCallbackEvent = Joiner.on(" ").join(Lists.transform(this.statusCallbackEvents, ConferenceEvent.TO_STRING));
        } else {
            this.statusCallbackEvent = null;
        }
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

    public Method getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    public String getStatusCallback() {
        return statusCallback;
    }

    public List<ConferenceEvent> getStatusCallbackEvents() {
        return statusCallbackEvents;
    }

    public String getRecordingStatusCallback() {
        return recordingStatusCallback;
    }

    public Method getRecordingStatusCallbackMethod() {
        return recordingStatusCallbackMethod;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getOptions() {
        Map<String, String> convertedMap = new HashMap<>();

        Set<QName> keys = options.keySet();
        for (QName key : keys) {
            convertedMap.put(key.getNamespaceURI(), options.get(key));
        }
        return convertedMap;
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
        private List<ConferenceEvent> statusCallbackEvents;
        private Method statusCallbackMethod;
        private String statusCallback;
        private String recordingStatusCallback;
        private Method recordingStatusCallbackMethod;
        private String name;
        private Map<QName, String> options = Maps.newHashMap();

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

        public Builder statusCallbackEvents(List<ConferenceEvent> statusCallbackEvents) {
            this.statusCallbackEvents = statusCallbackEvents;
            return this;
        }

        public Builder statusCallback(String statusCallback) {
            this.statusCallback = statusCallback;
            return this;
        }

        public Builder statusCallbackMethod(Method statusCallbackMethod) {
            this.statusCallbackMethod = statusCallbackMethod;
            return this;
        }

        public Builder eventCallbackUrl(String eventCallbackUrl) {
            this.eventCallbackUrl = eventCallbackUrl;
            return this;
        }

        public Builder recordingStatusCallback(String recordingStatusCallback) {
            this.recordingStatusCallback = recordingStatusCallback;
            return this;
        }

        public Builder recordingStatusCallbackMethod(Method recordingStatusCallbackMethod) {
            this.recordingStatusCallbackMethod = recordingStatusCallbackMethod;
            return this;
        }

        public Builder options(String key, String value) {
            this.options.put(new QName(key), value);
            return this;
        }

        public Conference build() {
            return new Conference(this);
        }
    }
}
