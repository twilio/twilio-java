package com.twilio.sdk.verbs;


/*
Copyright (c) 2008-2015 Twilio, Inc.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class Verb.
 */
public class Verb {

    /** The tag. */
    private final String tag;

    /** The body. */
    private final String body;

    /** The attributes. */
    private final Map<String, String> attributes;

    /** The children. */
    private final List<Verb> children;

    /** The allowed verbs. */
    protected List<String> allowedVerbs;

    /** Constants */

    public static final String V_BODY = "Body";

    public static final String V_CLIENT = "Client";

    public static final String V_CONFERENCE = "Conference";

    public static final String V_DIAL = "Dial";

    public static final String V_ENQUEUE = "Enqueue";

    public static final String V_GATHER = "Gather";

    public static final String V_HANGUP = "Hangup";

    public static final String V_LEAVE = "Leave";

    public static final String V_MEDIA = "Media";

    public static final String V_MESSAGE = "Message";

    public static final String V_NUMBER = "Number";

    public static final String V_PAUSE = "Pause";

    public static final String V_PLAY = "Play";

    public static final String V_QUEUE = "Queue";

    public static final String V_RECORD = "Record";

    public static final String V_REDIRECT = "Redirect";

    public static final String V_REJECT = "Reject";

    public static final String V_RESPONSE = "Response";

    public static final String V_SAY = "Say";

    public static final String V_SIP = "Sip";

    /** The Constant V_SMS. */
    public static final String V_SMS = "Sms";

    public static final String V_URI = "Uri";

    /**
     * Instantiates a new verb.
     *
     * @param tag the tag
     * @param body the body
     */
    public Verb(final String tag, final String body) {
        this.tag = tag;
        this.body = body;
        attributes = new HashMap<>();
        children = new ArrayList<>();
    }

    /**
     * Append.
     *
     * @param verb the verb
     * @return the verb
     * @throws TwiMLException the twiml exception
     */
    public Verb append(final Verb verb) throws TwiMLException {
        if (allowedVerbs != null && allowedVerbs.contains(verb.getTag())) {
            children.add(verb);
            return verb;
        } else {
            throw new TwiMLException("This is not a supported verb");
        }
    }

    /**
     * Returns (unescaped) xml representation of the Verb.
     *
     * @return the string
     */
    public String toXML() {
        return toXML(false);
    }

    /**
     * Build the XML representation of the Verb.
     *
     * @param escape whether to escape the XML body
     * @return XML string
     */
    private String toXML(final boolean escape) {
        String xml = "<" + tag;
        for (final String key : attributes.keySet()) {
            xml += " " + key + "=\"" + attributes.get(key) + "\"";
        }
        xml += ">";
        if (body != null) {
            final String body = escape ? StringEscapeUtils.escapeXml10(this.body) : this.body;
            xml += body;
        }
        for (final Verb child : children) {
            xml += child.toXML();
        }
        return xml += "</" + tag + ">";
    }

    /**
     * Return escaped XML representation of the verb. See issue #110 for why this method was added.
     *
     * @return escaped XML String
     */
    public String toEscapedXML() {
        return toXML(true);
    }

    /**
     * As url.
     *
     * @return the string
     */
    public String asURL() {
        try {
            return URLEncoder.encode(toXML(), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sets the.
     *
     * @param key the key
     * @param value the value
     */
    public void set(final String key, final String value) {
        attributes.put(key, value);
    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets the children.
     *
     * @return the children
     */
    public List<Verb> getChildren() {
        return children;
    }

    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }
}
