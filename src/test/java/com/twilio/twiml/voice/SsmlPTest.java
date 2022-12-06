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
 * Test class for {@link SsmlP}
 */
public class SsmlPTest {
    @Test
    public void testEmptyElement() {
        SsmlP elem = new SsmlP.Builder().build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p/>",
            elem.toXml()
        );
    }

    @Test
    public void testEmptyElementUrl() {
        SsmlP elem = new SsmlP.Builder().build();

        Assert.assertEquals("%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cp%2F%3E", elem.toUrl());
    }

    @Test
    public void testElementWithParams() {
        SsmlP elem = new SsmlP.Builder("words").build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p>words</p>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithExtraAttributes() {
        SsmlP elem = new SsmlP.Builder().option("foo", "bar").option("a", "b").build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p a=\"b\" foo=\"bar\"/>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithChildren() {
        SsmlP.Builder builder = new SsmlP.Builder();

        builder.break_(new SsmlBreak.Builder().strength(SsmlBreak.Strength.NONE).time("time").build());

        builder.emphasis(new SsmlEmphasis.Builder("words").level(SsmlEmphasis.Level.STRONG).build());

        builder.lang(new SsmlLang.Builder("words").xmlLang(SsmlLang.XmlLang.ARB).build());

        builder.phoneme(new SsmlPhoneme.Builder("words").alphabet(SsmlPhoneme.Alphabet.IPA).ph("ph").build());

        builder.prosody(new SsmlProsody.Builder("words").volume("volume").rate("rate").pitch("pitch").build());

        builder.s(new SsmlS.Builder("words").build());

        builder.sayAs(new SsmlSayAs.Builder("words")
                    .interpretAs(SsmlSayAs.InterpretAs.CHARACTER)
                    .role(SsmlSayAs.Role.MDY)
                    .build());

        builder.sub(new SsmlSub.Builder("words").alias("alias").build());

        builder.w(new SsmlW.Builder("words").role("role").build());

        SsmlP elem = builder.build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p>" +
                "<break strength=\"none\" time=\"time\"/>" +
                "<emphasis level=\"strong\">words</emphasis>" +
                "<lang xml:lang=\"arb\">words</lang>" +
                "<phoneme alphabet=\"ipa\" ph=\"ph\">words</phoneme>" +
                "<prosody pitch=\"pitch\" rate=\"rate\" volume=\"volume\">words</prosody>" +
                "<s>words</s>" +
                "<say-as interpret-as=\"character\" role=\"mdy\">words</say-as>" +
                "<sub alias=\"alias\">words</sub>" +
                "<w role=\"role\">words</w>" +
            "</p>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithTextNode() {
        SsmlP.Builder builder = new SsmlP.Builder();

        builder.addText("Hey no tags!");

        SsmlP elem = builder.build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p>" +
            "Hey no tags!" +
            "</p>",
            elem.toXml()
        );
    }

    @Test
    public void testMixedContent() {
        GenericNode.Builder child = new GenericNode.Builder("Child");
        child.addText("content");

        SsmlP.Builder builder = new SsmlP.Builder();

        builder.addText("before");
        builder.addChild(child.build());
        builder.addText("after");

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p>" +
            "before" +
            "<Child>content</Child>" +
            "after" +
            "</p>",
            builder.build().toXml()
        );
    }

    @Test
    public void testElementWithGenericNode() {
        GenericNode.Builder genericBuilder = new GenericNode.Builder("genericTag");
        genericBuilder.addText("Some text");
        GenericNode node = genericBuilder.build();

        SsmlP.Builder builder = new SsmlP.Builder();
        SsmlP elem = builder.addChild(node).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p>" +
            "<genericTag>" +
            "Some text" +
            "</genericTag>" +
            "</p>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithGenericNodeAttributes() {
        GenericNode.Builder genericBuilder = new GenericNode.Builder("genericTag");
        GenericNode node = genericBuilder.option("key", "value").addText("someText").build();

        SsmlP.Builder builder = new SsmlP.Builder();
        SsmlP elem = builder.addChild(node).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<p>" +
            "<genericTag key=\"value\">" +
            "someText" +
            "</genericTag>" +
            "</p>",
            elem.toXml()
        );
    }

    @Test
    public void testXmlAttributesDeserialization() {
        final SsmlP elem = new SsmlP.Builder("words").build();

        Assert.assertEquals(
            SsmlP.Builder.fromXml("<p>words</p>").build().toXml(),
            elem.toXml()
        );
    }

    @Test
    public void testXmlChildrenDeserialization() {
        final SsmlP.Builder builder = new SsmlP.Builder();

        builder.break_(new SsmlBreak.Builder().strength(SsmlBreak.Strength.NONE).time("time").build());

        builder.emphasis(new SsmlEmphasis.Builder("words").level(SsmlEmphasis.Level.STRONG).build());

        builder.lang(new SsmlLang.Builder("words").xmlLang(SsmlLang.XmlLang.ARB).build());

        builder.phoneme(new SsmlPhoneme.Builder("words").alphabet(SsmlPhoneme.Alphabet.IPA).ph("ph").build());

        builder.prosody(new SsmlProsody.Builder("words").volume("volume").rate("rate").pitch("pitch").build());

        builder.s(new SsmlS.Builder("words").build());

        builder.sayAs(new SsmlSayAs.Builder("words")
                    .interpretAs(SsmlSayAs.InterpretAs.CHARACTER)
                    .role(SsmlSayAs.Role.MDY)
                    .build());

        builder.sub(new SsmlSub.Builder("words").alias("alias").build());

        builder.w(new SsmlW.Builder("words").role("role").build());

        final SsmlP elem = builder.build();

        Assert.assertEquals(
            SsmlP.Builder.fromXml("<p>" +
                "<break strength=\"none\" time=\"time\"/>" +
                "<emphasis level=\"strong\">words</emphasis>" +
                "<lang xml:lang=\"arb\">words</lang>" +
                "<phoneme alphabet=\"ipa\" ph=\"ph\">words</phoneme>" +
                "<prosody pitch=\"pitch\" rate=\"rate\" volume=\"volume\">words</prosody>" +
                "<s>words</s>" +
                "<say-as interpret-as=\"character\" role=\"mdy\">words</say-as>" +
                "<sub alias=\"alias\">words</sub>" +
                "<w role=\"role\">words</w>" +
            "</p>").build().toXml(),
            elem.toXml()
        );
    }

    @Test
    public void testXmlEmptyChildrenDeserialization() {
        final SsmlP.Builder builder = new SsmlP.Builder();

        builder.break_(new SsmlBreak.Builder().build());

        builder.emphasis(new SsmlEmphasis.Builder().build());

        builder.lang(new SsmlLang.Builder().build());

        builder.prosody(new SsmlProsody.Builder().build());

        builder.s(new SsmlS.Builder().build());

        builder.w(new SsmlW.Builder().build());

        final SsmlP elem = builder.build();

        Assert.assertEquals(
            SsmlP.Builder.fromXml("<p>" +
                "<break/>" +
                "<emphasis/>" +
                "<lang/>" +
                "<prosody/>" +
                "<s/>" +
                "<w/>" +
            "</p>").build().toXml(),
            elem.toXml()
        );
    }
}