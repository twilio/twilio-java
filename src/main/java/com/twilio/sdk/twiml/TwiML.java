package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * TwiML object.
 */
public abstract class TwiML {

    // TODO: fix these exceptions
    public String toXml() throws Exception {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(this);
    }

    public String toUrl() throws Exception {
        try {
            return URLEncoder.encode(toXml(), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
