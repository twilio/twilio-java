package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Dial extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final boolean hangupOnStar;

    @JacksonXmlProperty(isAttribute = true)
    private final int timeout;

    @JacksonXmlProperty(isAttribute = true)
    private final int timeLimit;

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlProperty(isAttribute = true)
    private final String callerId;

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

    private Dial(
        boolean hangupOnStar,
        int timeout,
        int timeLimit,
        String action,
        String method,
        String callerId,
        Number number,
        Conference conference,
        Client client,
        Queue queue,
        Sip sip,
        String phoneNumber
    ) {
        this.hangupOnStar = hangupOnStar;
        this.timeout = timeout;
        this.timeLimit = timeLimit;
        this.action = action;
        this.method = method;
        this.callerId = callerId;
        this.number = number;
        this.conference = conference;
        this.client = client;
        this.queue = queue;
        this.sip = sip;
        this.phoneNumber = phoneNumber;
    }

    public boolean isHangupOnStar() {
        return hangupOnStar;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }

    public String getCallerId() {
        return callerId;
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
        private boolean hangupOnStar;
        private int timeout;
        private int timeLimit;
        private String action;
        private String method;
        private String callerId;
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

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder callerId(String callerId) {
            this.callerId = callerId;
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
            return new Dial(
                hangupOnStar,
                timeout,
                timeLimit,
                action,
                method,
                callerId,
                number,
                conference,
                client,
                queue,
                sip,
                phoneNumber
            );
        }
    }
}
