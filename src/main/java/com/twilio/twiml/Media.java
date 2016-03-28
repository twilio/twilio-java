package com.twilio.twiml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/sms/message#nouns.
 */
@XmlRootElement(name = "Media")
public class Media extends TwiML {

    @XmlValue
    private final String mediaUrl;

    // For XML Serialization
    private Media() {
        this(null);
    }

    public Media(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
}
