package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/your_response.
 */
@JacksonXmlRootElement(localName = "Response")
public class VoiceResponse extends TwiML {

    @JacksonXmlProperty(localName = "Dial")
    private final Dial dial;

    @JacksonXmlProperty(localName = "Enqueue")
    private final Enqueue enqueue;

    @JacksonXmlProperty(localName = "Gather")
    private final Gather gather;

    @JacksonXmlProperty(localName = "Hangup")
    private final Hangup hangup;

    @JacksonXmlProperty(localName = "Leave")
    private final Leave leave;

    @JacksonXmlProperty(localName = "Pause")
    private final Pause pause;

    @JacksonXmlProperty(localName = "Play")
    private final Play play;

    @JacksonXmlProperty(localName = "Record")
    private final Record record;

    @JacksonXmlProperty(localName = "Redirect")
    private final Redirect redirect;

    @JacksonXmlProperty(localName = "Reject")
    private final Reject reject;

    @JacksonXmlProperty(localName = "Say")
    private final Say say;

    @JacksonXmlProperty(localName = "Sms")
    private final Sms sms;

    private VoiceResponse(Builder builder) {
        this.dial = builder.dial;
        this.enqueue = builder.enqueue;
        this.gather = builder.gather;
        this.hangup = builder.hangup;
        this.leave = builder.leave;
        this.pause = builder.pause;
        this.play = builder.play;
        this.record = builder.record;
        this.redirect = builder.redirect;
        this.reject = builder.reject;
        this.say = builder.say;
        this.sms = builder.sms;
    }

    public Dial getDial() {
        return dial;
    }

    public Enqueue getEnqueue() {
        return enqueue;
    }

    public Gather getGather() {
        return gather;
    }

    public Hangup getHangup() {
        return hangup;
    }

    public Leave getLeave() {
        return leave;
    }

    public Pause getPause() {
        return pause;
    }

    public Play getPlay() {
        return play;
    }

    public Record getRecord() {
        return record;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public Reject getReject() {
        return reject;
    }

    public Say getSay() {
        return say;
    }

    public Sms getSms() {
        return sms;
    }

    public static class Builder {
        private Dial dial;
        private Enqueue enqueue;
        private Gather gather;
        private Hangup hangup;
        private Leave leave;
        private Pause pause;
        private Play play;
        private Record record;
        private Redirect redirect;
        private Reject reject;
        private Say say;
        private Sms sms;

        public Builder dial(Dial dial) {
            this.dial = dial;
            return this;
        }

        public Builder enqueue(Enqueue enqueue) {
            this.enqueue = enqueue;
            return this;
        }

        public Builder gather(Gather gather) {
            this.gather = gather;
            return this;
        }

        public Builder hangup(Hangup hangup) {
            this.hangup = hangup;
            return this;
        }

        public Builder leave(Leave leave) {
            this.leave = leave;
            return this;
        }

        public Builder pause(Pause pause) {
            this.pause = pause;
            return this;
        }

        public Builder play(Play play) {
            this.play = play;
            return this;
        }

        public Builder record(Record record) {
            this.record = record;
            return this;
        }

        public Builder redirect(Redirect redirect) {
            this.redirect = redirect;
            return this;
        }

        public Builder reject(Reject reject) {
            this.reject = reject;
            return this;
        }

        public Builder say(Say say) {
            this.say = say;
            return this;
        }

        public Builder sms(Sms sms) {
            this.sms = sms;
            return this;
        }

        public VoiceResponse build() {
            return new VoiceResponse(this);
        }
    }
}
