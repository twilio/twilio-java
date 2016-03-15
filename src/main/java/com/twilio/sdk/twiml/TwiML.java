package com.twilio.sdk.twiml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * TwiML object.
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public abstract class TwiML {

    /**
     * Convert TwiML object to XML.
     *
     * @return XML string of TwiML object
     * @throws TwiMLException if cannot generate XML
     */
    public String toXml() throws TwiMLException {
        XmlMapper mapper = new XmlMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new TwiMLException(e.getMessage());
        }
    }

    /**
     * Convert TwiML object to URL.
     *
     * @return URL string of TwiML object
     * @throws TwiMLException if cannot generate URL
     */
    public String toUrl() throws TwiMLException {
        try {
            return URLEncoder.encode(toXml(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TwiMLException(e.getMessage());
        }
    }
}
