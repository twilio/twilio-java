package com.twilio.twiml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/record.
 */
@XmlRootElement(name = "Record")
public class Record extends TwiML {

    @XmlAttribute
    private final Boolean transcribe;

    @XmlAttribute
    private final Boolean playBeep;

    @XmlAttribute
    private final Integer timeout;

    @XmlAttribute
    private final Integer maxLength;

    @XmlAttribute
    private final String action;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String recordingStatusCallback;

    @XmlAttribute
    private final Method recordingStatusCallbackMethod;

    @XmlAttribute
    private final String finishOnKey;

    @XmlAttribute
    private final String transcribeCallback;

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Trim trim;

    // For XML Serialization
    private Record() {
        this(new Builder());
    }

    private Record(Builder b) {
        this.transcribe = b.transcribe;
        this.playBeep = b.playBeep;
        this.timeout = b.timeout;
        this.maxLength = b.maxLength;
        this.action = b.action;
        this.method = b.method;
        this.recordingStatusCallback = b.recordingStatusCallback;
        this.recordingStatusCallbackMethod = b.recordingStatusCallbackMethod;
        this.finishOnKey = b.finishOnKey;
        this.transcribeCallback = b.transcribeCallback;
        this.trim = b.trim;
    }

    public Boolean isTranscribe() {
        return transcribe;
    }

    public Boolean isPlayBeep() {
        return playBeep;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public String getAction() {
        return action;
    }

    public Method getMethod() {
        return method;
    }

    public String getRecordingStatusCallback() {
        return recordingStatusCallback;
    }

    public Method getRecordingStatusCallbackMethod() {
        return recordingStatusCallbackMethod;
    }

    public String getFinishOnKey() {
        return finishOnKey;
    }

    public String getTranscribeCallback() {
        return transcribeCallback;
    }

    public Trim getTrim() {
        return trim;
    }

    public static class Builder {
        private Boolean transcribe;
        private Boolean playBeep;
        private Integer timeout;
        private Integer maxLength;
        private String action;
        private Method method;
        private String recordingStatusCallback;
        private Method recordingStatusCallbackMethod;
        private String finishOnKey;
        private String transcribeCallback;
        private Trim trim;

        public Builder transcribe(boolean transcribe) {
            this.transcribe = transcribe;
            return this;
        }

        public Builder playBeep(boolean playBeep) {
            this.playBeep = playBeep;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder maxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
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

        public Builder finishOnKey(String finishOnKey) {
            this.finishOnKey = finishOnKey;
            return this;
        }

        public Builder transcribeCallback(String transcribeCallback) {
            this.transcribeCallback = transcribeCallback;
            return this;
        }

        public Builder trim(Trim trim) {
            this.trim = trim;
            return this;
        }

        public Record build() {
            return new Record(this);
        }
    }
}
