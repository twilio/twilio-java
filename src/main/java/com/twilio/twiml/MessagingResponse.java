package com.twilio.twiml;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sms/your_response.
 */
@XmlRootElement(name = "Response")
public class MessagingResponse extends TwiML {

    @SuppressWarnings("checkstyle:indentation")
    @XmlElements({
        @XmlElement(name = "Message", type = Message.class),
        @XmlElement(name = "Redirect", type = Redirect.class)
    })
    private final List<TwiML> actions;

    // For XML Serialization
    private MessagingResponse() {
        this(new Builder());
    }

    private MessagingResponse(Builder b) {
        this.actions = Lists.newArrayList(b.actions);
    }

    public List<TwiML> getActions() {
        return actions;
    }

    public static class Builder {
        private List<TwiML> actions = Lists.newArrayList();

        public Builder message(Message message) {
            this.actions.add(message);
            return this;
        }

        public Builder redirect(Redirect redirect) {
            this.actions.add(redirect);
            return this;
        }

        public MessagingResponse build() {
            return new MessagingResponse(this);
        }
    }
}
