package com.twilio.twiml;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Body body1 = (Body) o;
        return Objects.equal(body, body1.body);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(body);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("body", body)
            .toString();
    }
}
