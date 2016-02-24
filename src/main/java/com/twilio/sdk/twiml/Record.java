package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Record extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final boolean transcribe;

    @JacksonXmlProperty(isAttribute = true)
    private final boolean playBeep;

    @JacksonXmlProperty(isAttribute = true)
    private final int timeout;

    @JacksonXmlProperty(isAttribute = true)
    private final int maxLength;

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlProperty(isAttribute = true)
    private final String finishOnKey;

    @JacksonXmlProperty(isAttribute = true)
    private final String transcribeCallback;

    private Record(
        boolean transcribe,
        boolean playBeep,
        int timeout,
        int maxLength,
        String action,
        String method,
        String finishOnKey,
        String transcribeCallback
    ) {
        this.transcribe = transcribe;
        this.playBeep = playBeep;
        this.timeout = timeout;
        this.maxLength = maxLength;
        this.action = action;
        this.method = method;
        this.finishOnKey = finishOnKey;
        this.transcribeCallback = transcribeCallback;
    }

    public boolean isTranscribe() {
        return transcribe;
    }

    public boolean isPlayBeep() {
        return playBeep;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }

    public String getFinishOnKey() {
        return finishOnKey;
    }

    public String getTranscribeCallback() {
        return transcribeCallback;
    }

    public static class Builder {
        private boolean transcribe;
        private boolean playBeep;
        private int timeout;
        private int maxLength;
        private String action;
        private String method;
        private String finishOnKey;
        private String transcribeCallback;

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

        public Builder method(String method) {
            this.method = method;
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

        public Record build() {
            return new Record(
                transcribe,
                playBeep,
                timeout,
                maxLength,
                action,
                method,
                finishOnKey,
                transcribeCallback
            );
        }
    }
}
