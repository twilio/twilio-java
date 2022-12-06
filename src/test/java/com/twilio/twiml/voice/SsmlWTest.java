/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.twilio.twiml.GenericNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link SsmlW}
 */
public class SsmlWTest {
    @Test
    public void testEmptyElement() {
        SsmlW elem = new SsmlW.Builder().build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w/>",
            elem.toXml()
        );
    }

    @Test
    public void testEmptyElementUrl() {
        SsmlW elem = new SsmlW.Builder().build();

        Assert.assertEquals("%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cw%2F%3E", elem.toUrl());
    }

    @Test
    public void testElementWithParams() {
        SsmlW elem = new SsmlW.Builder("words").role("role").build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w role=\"role\">words</w>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithExtraAttributes() {
        SsmlW elem = new SsmlW.Builder().option("foo", "bar").option("a", "b").build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w a=\"b\" foo=\"bar\"/>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithChildren() {
        SsmlW.Builder builder = new SsmlW.Builder();

        builder.break_(new SsmlBreak.Builder().strength(SsmlBreak.Strength.NONE).time("time").build());

        builder.emphasis(new SsmlEmphasis.Builder("words").level(SsmlEmphasis.Level.STRONG).build());

        builder.phoneme(new SsmlPhoneme.Builder("words").alphabet(SsmlPhoneme.Alphabet.IPA).ph("ph").build());

        builder.prosody(new SsmlProsody.Builder("words").volume("volume").rate("rate").pitch("pitch").build());

        builder.sayAs(new SsmlSayAs.Builder("words")
                    .interpretAs(SsmlSayAs.InterpretAs.CHARACTER)
                    .role(SsmlSayAs.Role.MDY)
                    .build());

        builder.sub(new SsmlSub.Builder("words").alias("alias").build());

        SsmlW elem = builder.build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w>" +
                "<break strength=\"none\" time=\"time\"/>" +
                "<emphasis level=\"strong\">words</emphasis>" +
                "<phoneme alphabet=\"ipa\" ph=\"ph\">words</phoneme>" +
                "<prosody pitch=\"pitch\" rate=\"rate\" volume=\"volume\">words</prosody>" +
                "<say-as interpret-as=\"character\" role=\"mdy\">words</say-as>" +
                "<sub alias=\"alias\">words</sub>" +
            "</w>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithTextNode() {
        SsmlW.Builder builder = new SsmlW.Builder();

        builder.addText("Hey no tags!");

        SsmlW elem = builder.build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w>" +
            "Hey no tags!" +
            "</w>",
            elem.toXml()
        );
    }

    @Test
    public void testMixedContent() {
        GenericNode.Builder child = new GenericNode.Builder("Child");
        child.addText("content");

        SsmlW.Builder builder = new SsmlW.Builder();

        builder.addText("before");
        builder.addChild(child.build());
        builder.addText("after");

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w>" +
            "before" +
            "<Child>content</Child>" +
            "after" +
            "</w>",
            builder.build().toXml()
        );
    }

    @Test
    public void testElementWithGenericNode() {
        GenericNode.Builder genericBuilder = new GenericNode.Builder("genericTag");
        genericBuilder.addText("Some text");
        GenericNode node = genericBuilder.build();

        SsmlW.Builder builder = new SsmlW.Builder();
        SsmlW elem = builder.addChild(node).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w>" +
            "<genericTag>" +
            "Some text" +
            "</genericTag>" +
            "</w>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithGenericNodeAttributes() {
        GenericNode.Builder genericBuilder = new GenericNode.Builder("genericTag");
        GenericNode node = genericBuilder.option("key", "value").addText("someText").build();

        SsmlW.Builder builder = new SsmlW.Builder();
        SsmlW elem = builder.addChild(node).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<w>" +
            "<genericTag key=\"value\">" +
            "someText" +
            "</genericTag>" +
            "</w>",
            elem.toXml()
        );
    }

    @Test
    public void testXmlAttributesDeserialization() {
        final SsmlW elem = new SsmlW.Builder("words").role("role").build();

        Assert.assertEquals(
            SsmlW.Builder.fromXml("<w role=\"role\">words</w>").build().toXml(),
            elem.toXml()
        );
    }

    @Test
    public void testXmlChildrenDeserialization() {
        final SsmlW.Builder builder = new SsmlW.Builder();

        builder.break_(new SsmlBreak.Builder().strength(SsmlBreak.Strength.NONE).time("time").build());

        builder.emphasis(new SsmlEmphasis.Builder("words").level(SsmlEmphasis.Level.STRONG).build());

        builder.phoneme(new SsmlPhoneme.Builder("words").alphabet(SsmlPhoneme.Alphabet.IPA).ph("ph").build());

        builder.prosody(new SsmlProsody.Builder("words").volume("volume").rate("rate").pitch("pitch").build());

        builder.sayAs(new SsmlSayAs.Builder("words")
                    .interpretAs(SsmlSayAs.InterpretAs.CHARACTER)
                    .role(SsmlSayAs.Role.MDY)
                    .build());

        builder.sub(new SsmlSub.Builder("words").alias("alias").build());

        final SsmlW elem = builder.build();

        Assert.assertEquals(
            SsmlW.Builder.fromXml("<w>" +
                "<break strength=\"none\" time=\"time\"/>" +
                "<emphasis level=\"strong\">words</emphasis>" +
                "<phoneme alphabet=\"ipa\" ph=\"ph\">words</phoneme>" +
                "<prosody pitch=\"pitch\" rate=\"rate\" volume=\"volume\">words</prosody>" +
                "<say-as interpret-as=\"character\" role=\"mdy\">words</say-as>" +
                "<sub alias=\"alias\">words</sub>" +
            "</w>").build().toXml(),
            elem.toXml()
        );
    }

    @Test
    public void testXmlEmptyChildrenDeserialization() {
        final SsmlW.Builder builder = new SsmlW.Builder();

        builder.break_(new SsmlBreak.Builder().build());

        builder.emphasis(new SsmlEmphasis.Builder().build());

        builder.prosody(new SsmlProsody.Builder().build());

        final SsmlW elem = builder.build();

        Assert.assertEquals(
            SsmlW.Builder.fromXml("<w>" +
                "<break/>" +
                "<emphasis/>" +
                "<prosody/>" +
            "</w>").build().toXml(),
            elem.toXml()
        );
    }
}