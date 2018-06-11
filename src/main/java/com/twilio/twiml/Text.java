package com.twilio.twiml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Text extends TwiML {
    private final String text;

    protected Text(final String text) {
        super(null, null);
        this.text = text;
    }

    @Override
    protected Node buildXmlElement(final Document parentDoc) {
        return parentDoc.createTextNode(this.text);
    }
}
