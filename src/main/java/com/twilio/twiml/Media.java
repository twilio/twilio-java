package com.twilio.twiml;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Media media = (Media) o;
        return Objects.equal(mediaUrl, media.mediaUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mediaUrl);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("mediaUrl", mediaUrl)
            .toString();
    }
}
