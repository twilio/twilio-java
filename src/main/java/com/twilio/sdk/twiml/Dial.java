package com.twilio.sdk.twiml;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/dial.
 */
@JacksonXmlRootElement
public class Dial extends TwiML {

    public enum Record {
        DO_NOT_RECORD("do-not-record"),
        RECORD_FROM_RINGING("record-from-ringing"),
        RECORD_FROM_ANSWER("record-from-answer");

        private final String value;

        private Record(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Trim {
        TRIM_SILENCE("trim-silence"),
        DO_NOT_TRIM("do-not-trim");

        private final String value;

        private Trim(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean hangupOnStar;

    @JacksonXmlProperty(isAttribute = true)
    private final Integer timeout;

    @JacksonXmlProperty(isAttribute = true)
    private final Integer timeLimit;

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final Method method;

    @JacksonXmlProperty(isAttribute = true)
    private final String callerId;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Record record;

    @JacksonXmlProperty(isAttribute = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private final Trim trim;

    @JacksonXmlProperty(localName = "Number")
    private final Number number;

    @JacksonXmlProperty(localName = "Conference")
    private final Conference conference;

    @JacksonXmlProperty(localName = "Client")
    private final Client client;

    @JacksonXmlProperty(localName = "Queue")
    private final Queue queue;

    @JacksonXmlProperty(localName = "Sip")
    private final Sip sip;

    @JacksonXmlText
    private final String phoneNumber;

    private Dial(Builder b) {
        this.hangupOnStar = b.hangupOnStar;
        this.timeout = b.timeout;
        this.timeLimit = b.timeLimit;
        this.action = b.action;
        this.method = b.method;
        this.callerId = b.callerId;
        this.record = b.record;
        this.trim = b.trim;
        this.number = b.number;
        this.conference = b.conference;
        this.client = b.client;
        this.queue = b.queue;
        this.sip = b.sip;
        this.phoneNumber = b.phoneNumber;
    }

    public Boolean isHangupOnStar() {
        return hangupOnStar;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public String getAction() {
        return action;
    }

    public Method getMethod() {
        return method;
    }

    public String getCallerId() {
        return callerId;
    }

    public Record getRecord() {
        return record;
    }

    public Trim getTrim() {
        return trim;
    }

    public Number getNumber() {
        return number;
    }

    public Conference getConference() {
        return conference;
    }

    public Client getClient() {
        return client;
    }

    public Queue getQueue() {
        return queue;
    }

    public Sip getSip() {
        return sip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder {
        private Boolean hangupOnStar;
        private Integer timeout;
        private Integer timeLimit;
        private String action;
        private Method method;
        private String callerId;
        private Record record;
        private Trim trim;
        private Number number;
        private Conference conference;
        private Client client;
        private Queue queue;
        private Sip sip;
        private String phoneNumber;

        public Builder hangupOnStar(boolean hangupOnStar) {
            this.hangupOnStar = hangupOnStar;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder timeLimit(int timeLimit) {
            this.timeLimit = timeLimit;
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

        public Builder callerId(String callerId) {
            this.callerId = callerId;
            return this;
        }

        public Builder trim(Trim trim) {
            this.trim = trim;
            return this;
        }

        public Builder record(Record record) {
            this.record = record;
            return this;
        }

        public Builder number(Number number) {
            this.number = number;
            return this;
        }

        public Builder conference(Conference conference) {
            this.conference = conference;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder queue(Queue queue) {
            this.queue = queue;
            return this;
        }

        public Builder sip(Sip sip) {
            this.sip = sip;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Dial build() {
            return new Dial(this);
        }
    }
}
