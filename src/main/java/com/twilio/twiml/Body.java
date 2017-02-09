package com.twilio.twiml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sms/message#nouns.
 */
@XmlRootElement(name = "Body")
public class Body extends TwiML {

    @XmlValue
    private final String body;

    // For XML Serialization
    private Body() {
        this(null);
    }

    public Body(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
