package com.twilio.sdk.twiml;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/dial.
 */
@XmlRootElement(name = "Dial")
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

    @XmlAttribute
    private final Boolean hangupOnStar;

    @XmlAttribute
    private final Integer timeout;

    @XmlAttribute
    private final Integer timeLimit;

    @XmlAttribute
    private final String action;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String callerId;

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Record record;

    @XmlAttribute
    @XmlJavaTypeAdapter(TwiML.ToStringAdapter.class)
    private final Trim trim;

    @XmlElements({
        @XmlElement(name = "Number", type = Number.class)
    })
    private final List<Number> numbers;

    @XmlElements({
        @XmlElement(name = "Client", type = Client.class)
    })
    private final List<Client> clients;

    @XmlElement(name = "Conference")
    private final Conference conference;

    @XmlElement(name = "Queue")
    private final Queue queue;

    @XmlElement(name = "Sip")
    private final Sip sip;

    // For XML Serialization
    private Dial() {
        this(new Builder());
    }

    private Dial(Builder b) {
        this.hangupOnStar = b.hangupOnStar;
        this.timeout = b.timeout;
        this.timeLimit = b.timeLimit;
        this.action = b.action;
        this.method = b.method;
        this.callerId = b.callerId;
        this.record = b.record;
        this.trim = b.trim;
        this.numbers = Lists.newArrayList(b.numbers);
        this.clients = Lists.newArrayList(b.clients);
        this.conference = b.conference;
        this.queue = b.queue;
        this.sip = b.sip;
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

    public List<Number> getNumbers() {
        return numbers;
    }

    public List<Client> getClients() {
        return clients;
    }

    public Conference getConference() {
        return conference;
    }

    public Queue getQueue() {
        return queue;
    }

    public Sip getSip() {
        return sip;
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
        private List<Number> numbers = Lists.newArrayList();
        private List<Client> clients = Lists.newArrayList();
        private Conference conference;
        private Queue queue;
        private Sip sip;

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
            this.numbers.add(number);
            return this;
        }

        public Builder client(Client client) {
            this.clients.add(client);
            return this;
        }

        public Builder conference(Conference conference) {
            this.conference = conference;
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

        public Dial build() {
            return new Dial(this);
        }
    }
}
