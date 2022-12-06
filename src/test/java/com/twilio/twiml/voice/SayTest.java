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
 * Test class for {@link Say}
 */
public class SayTest {
    @Test
    public void testEmptyElement() {
        Say elem = new Say.Builder().build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say/>",
            elem.toXml()
        );
    }

    @Test
    public void testEmptyElementUrl() {
        Say elem = new Say.Builder().build();

        Assert.assertEquals("%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3CSay%2F%3E", elem.toUrl());
    }

    @Test
    public void testElementWithParams() {
        Say elem = new Say.Builder("message").voice(Say.Voice.MAN).loop(1).language(Say.Language.ARB).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say language=\"arb\" loop=\"1\" voice=\"man\">message</Say>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithExtraAttributes() {
        Say elem = new Say.Builder().option("foo", "bar").option("a", "b").build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say a=\"b\" foo=\"bar\"/>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithChildren() {
        Say.Builder builder = new Say.Builder();

        builder.break_(new SsmlBreak.Builder().strength(SsmlBreak.Strength.NONE).time("time").build());

        builder.emphasis(new SsmlEmphasis.Builder("words").level(SsmlEmphasis.Level.STRONG).build());

        builder.lang(new SsmlLang.Builder("words").xmlLang(SsmlLang.XmlLang.ARB).build());

        builder.p(new SsmlP.Builder("words").build());

        builder.phoneme(new SsmlPhoneme.Builder("words").alphabet(SsmlPhoneme.Alphabet.IPA).ph("ph").build());

        builder.prosody(new SsmlProsody.Builder("words").volume("volume").rate("rate").pitch("pitch").build());

        builder.s(new SsmlS.Builder("words").build());

        builder.sayAs(new SsmlSayAs.Builder("words")
                    .interpretAs(SsmlSayAs.InterpretAs.CHARACTER)
                    .role(SsmlSayAs.Role.MDY)
                    .build());

        builder.sub(new SsmlSub.Builder("words").alias("alias").build());

        builder.w(new SsmlW.Builder("words").role("role").build());

        Say elem = builder.build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say>" +
                "<break strength=\"none\" time=\"time\"/>" +
                "<emphasis level=\"strong\">words</emphasis>" +
                "<lang xml:lang=\"arb\">words</lang>" +
                "<p>words</p>" +
                "<phoneme alphabet=\"ipa\" ph=\"ph\">words</phoneme>" +
                "<prosody pitch=\"pitch\" rate=\"rate\" volume=\"volume\">words</prosody>" +
                "<s>words</s>" +
                "<say-as interpret-as=\"character\" role=\"mdy\">words</say-as>" +
                "<sub alias=\"alias\">words</sub>" +
                "<w role=\"role\">words</w>" +
            "</Say>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithTextNode() {
        Say.Builder builder = new Say.Builder();

        builder.addText("Hey no tags!");

        Say elem = builder.build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say>" +
            "Hey no tags!" +
            "</Say>",
            elem.toXml()
        );
    }

    @Test
    public void testMixedContent() {
        GenericNode.Builder child = new GenericNode.Builder("Child");
        child.addText("content");

        Say.Builder builder = new Say.Builder();

        builder.addText("before");
        builder.addChild(child.build());
        builder.addText("after");

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say>" +
            "before" +
            "<Child>content</Child>" +
            "after" +
            "</Say>",
            builder.build().toXml()
        );
    }

    @Test
    public void testElementWithGenericNode() {
        GenericNode.Builder genericBuilder = new GenericNode.Builder("genericTag");
        genericBuilder.addText("Some text");
        GenericNode node = genericBuilder.build();

        Say.Builder builder = new Say.Builder();
        Say elem = builder.addChild(node).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say>" +
            "<genericTag>" +
            "Some text" +
            "</genericTag>" +
            "</Say>",
            elem.toXml()
        );
    }

    @Test
    public void testElementWithGenericNodeAttributes() {
        GenericNode.Builder genericBuilder = new GenericNode.Builder("genericTag");
        GenericNode node = genericBuilder.option("key", "value").addText("someText").build();

        Say.Builder builder = new Say.Builder();
        Say elem = builder.addChild(node).build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<Say>" +
            "<genericTag key=\"value\">" +
            "someText" +
            "</genericTag>" +
            "</Say>",
            elem.toXml()
        );
    }

    @Test
    public void testXmlAttributesDeserialization() {
        final Say elem = new Say.Builder("message").voice(Say.Voice.MAN).loop(1).language(Say.Language.ARB).build();

        Assert.assertEquals(
            Say.Builder.fromXml("<Say language=\"arb\" loop=\"1\" voice=\"man\">message</Say>").build().toXml(),
            elem.toXml()
        );
    }

    @Test
    public void testXmlChildrenDeserialization() {
        final Say.Builder builder = new Say.Builder();

        builder.break_(new SsmlBreak.Builder().strength(SsmlBreak.Strength.NONE).time("time").build());

        builder.emphasis(new SsmlEmphasis.Builder("words").level(SsmlEmphasis.Level.STRONG).build());

        builder.lang(new SsmlLang.Builder("words").xmlLang(SsmlLang.XmlLang.ARB).build());

        builder.p(new SsmlP.Builder("words").build());

        builder.phoneme(new SsmlPhoneme.Builder("words").alphabet(SsmlPhoneme.Alphabet.IPA).ph("ph").build());

        builder.prosody(new SsmlProsody.Builder("words").volume("volume").rate("rate").pitch("pitch").build());

        builder.s(new SsmlS.Builder("words").build());

        builder.sayAs(new SsmlSayAs.Builder("words")
                    .interpretAs(SsmlSayAs.InterpretAs.CHARACTER)
                    .role(SsmlSayAs.Role.MDY)
                    .build());

        builder.sub(new SsmlSub.Builder("words").alias("alias").build());

        builder.w(new SsmlW.Builder("words").role("role").build());

        final Say elem = builder.build();

        Assert.assertEquals(
            Say.Builder.fromXml("<Say>" +
                "<break strength=\"none\" time=\"time\"/>" +
                "<emphasis level=\"strong\">words</emphasis>" +
                "<lang xml:lang=\"arb\">words</lang>" +
                "<p>words</p>" +
                "<phoneme alphabet=\"ipa\" ph=\"ph\">words</phoneme>" +
                "<prosody pitch=\"pitch\" rate=\"rate\" volume=\"volume\">words</prosody>" +
                "<s>words</s>" +
                "<say-as interpret-as=\"character\" role=\"mdy\">words</say-as>" +
                "<sub alias=\"alias\">words</sub>" +
                "<w role=\"role\">words</w>" +
            "</Say>").build().toXml(),
            elem.toXml()
        );
    }

    @Test
    public void testXmlEmptyChildrenDeserialization() {
        final Say.Builder builder = new Say.Builder();

        builder.break_(new SsmlBreak.Builder().build());

        builder.emphasis(new SsmlEmphasis.Builder().build());

        builder.lang(new SsmlLang.Builder().build());

        builder.p(new SsmlP.Builder().build());

        builder.prosody(new SsmlProsody.Builder().build());

        builder.s(new SsmlS.Builder().build());

        builder.w(new SsmlW.Builder().build());

        final Say elem = builder.build();

        Assert.assertEquals(
            Say.Builder.fromXml("<Say>" +
                "<break/>" +
                "<emphasis/>" +
                "<lang/>" +
                "<p/>" +
                "<prosody/>" +
                "<s/>" +
                "<w/>" +
            "</Say>").build().toXml(),
            elem.toXml()
        );
    }
}