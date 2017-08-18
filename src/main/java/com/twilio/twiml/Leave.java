package com.twilio.twiml;

import com.google.common.base.MoreObjects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/leave.
 */
@XmlRootElement(name = "Leave")
public class Leave extends TwiML {

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .toString();
    }

}
