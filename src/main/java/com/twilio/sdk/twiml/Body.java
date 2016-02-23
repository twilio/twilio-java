package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "Body")
public class Body extends TwiML{

    @JacksonXmlText
    private final String body;

    private Body(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
