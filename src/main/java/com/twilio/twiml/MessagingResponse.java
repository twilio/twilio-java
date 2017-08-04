package com.twilio.twiml;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessagingResponse that = (MessagingResponse) o;
        return Objects.equal(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(actions);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("actions", actions)
            .toString();
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
