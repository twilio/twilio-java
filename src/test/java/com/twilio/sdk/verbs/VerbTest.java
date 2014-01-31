package com.twilio.sdk.verbs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerbTest {

    /**
     * Test that the XML '&' entity is escaped.
     */
    @Test
    public void testEscapeAmpersand() {
        Say say = new Say("Hello there Tom & Jerry");
        assertEquals("<Say>Hello there Tom &amp; Jerry</Say>", say.toEscapedXML());
    }

    /**
     * Test that the XML '>' entity is escaped.
     */
    @Test
    public void testEscapeGt() {
        Say say = new Say("2 > 1");
        assertEquals("<Say>2 &gt; 1</Say>", say.toEscapedXML());
    }

    /**
     * Test that the toXML() method still returns unescaped XML.
     */
    @Test
    public void testUnescapedXML() {
        Say say = new Say("2 > 1");
        assertEquals("<Say>2 > 1</Say>", say.toXML());
    }

    @Test
    public void testMessageConstructor() {
        Message msg = new Message("foo bar");
        assertEquals("<Message>foo bar</Message>", msg.toXML());

        msg = new Message();
        assertEquals("<Message></Message>", msg.toXML());
    }
}
