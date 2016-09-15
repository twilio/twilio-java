package com.twilio.twiml;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/your_response.
 */
@XmlRootElement(name = "Response")
public class VoiceResponse extends TwiML {

    @SuppressWarnings("checkstyle:indentation")
    @XmlElements({
        @XmlElement(name = "Dial", type = Dial.class),
        @XmlElement(name = "Enqueue", type = Enqueue.class),
        @XmlElement(name = "Enqueue", type = EnqueueTask.class),
        @XmlElement(name = "Gather", type = Gather.class),
        @XmlElement(name = "Hangup", type = Hangup.class),
        @XmlElement(name = "Leave", type = Leave.class),
        @XmlElement(name = "Pause", type = Pause.class),
        @XmlElement(name = "Play", type = Play.class),
        @XmlElement(name = "Record", type = Record.class),
        @XmlElement(name = "Redirect", type = Redirect.class),
        @XmlElement(name = "Reject", type = Reject.class),
        @XmlElement(name = "Say", type = Say.class),
        @XmlElement(name = "Sms", type = Sms.class)
    })
    private final List<TwiML> actions;

    private VoiceResponse() {
        this(new Builder());
    }

    private VoiceResponse(Builder builder) {
        this.actions = Lists.newArrayList(builder.actions);
    }

    public static class Builder {
        private List<TwiML> actions = Lists.newArrayList();

        public Builder dial(Dial dial) {
            this.actions.add(dial);
            return this;
        }

        public Builder enqueue(Enqueue enqueue) {
            this.actions.add(enqueue);
            return this;
        }

        public Builder enqueue(EnqueueTask enqueue) {
            this.actions.add(enqueue);
            return this;
        }

        public Builder gather(Gather gather) {
            this.actions.add(gather);
            return this;
        }

        public Builder hangup(Hangup hangup) {
            this.actions.add(hangup);
            return this;
        }

        public Builder leave(Leave leave) {
            this.actions.add(leave);
            return this;
        }

        public Builder pause(Pause pause) {
            this.actions.add(pause);
            return this;
        }

        public Builder play(Play play) {
            this.actions.add(play);
            return this;
        }

        public Builder record(Record record) {
            this.actions.add(record);
            return this;
        }

        public Builder redirect(Redirect redirect) {
            this.actions.add(redirect);
            return this;
        }

        public Builder reject(Reject reject) {
            this.actions.add(reject);
            return this;
        }

        public Builder say(Say say) {
            this.actions.add(say);
            return this;
        }

        public Builder sms(Sms sms) {
            this.actions.add(sms);
            return this;
        }

        public VoiceResponse build() {
            return new VoiceResponse(this);
        }
    }
}
