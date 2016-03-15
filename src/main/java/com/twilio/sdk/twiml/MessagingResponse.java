package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * TwiML wrapper for {@see https://www.twilio.com/docs/api/twiml/sms/your_response}.
 */
public class MessagingResponse extends TwiML {

    @JacksonXmlProperty(localName = "Message")
    private final Message message;

    @JacksonXmlProperty(localName = "Redirect")
    private final Redirect redirect;

    private MessagingResponse(Builder b) {
        this.message = b.message;
        this.redirect = b.redirect;
    }

    public Message getMessage() {
        return message;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public static class Builder {
        private Message message;
        private Redirect redirect;

        public Builder message(Message message) {
            this.message = message;
            return this;
        }

        public Builder redirect(Redirect redirect) {
            this.redirect = redirect;
            return this;
        }

        public MessagingResponse build() {
            return new MessagingResponse(this);
        }
    }
}
