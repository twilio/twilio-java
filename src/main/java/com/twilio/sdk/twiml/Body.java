package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sms/message#nouns.
 */
@JacksonXmlRootElement(localName = "Body")
public class Body extends TwiML{

    @JacksonXmlText
    private final String body;

    public Body(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
