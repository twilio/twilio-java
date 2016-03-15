package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement
public class Media extends TwiML {

    @JacksonXmlText
    private final String mediaUrl;

    public Media(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
}
