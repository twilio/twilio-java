package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.fail;

public class RecordingRuleTest extends TypeTest {
    @Test
    public void testBuilderOneFilter() {
        try {
            RecordingRule.builder().withType(RecordingRule.Type.INCLUDE).withPublisher(null).build();
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Rule builder should enforce a filter");
    }

    @Test
    public void testBuilderMustHaveType() {
        try {
            RecordingRule.builder().withType(null).withPublisher("alice").build();
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Rule builder should enforce setting a type");
    }

    @Test
    public void testAllExclusive() throws IOException {
        String json = "{\n" +
                "    \"type\": \"include\",\n" +
                "    \"all\": true\n" +
                "}";

        RecordingRule r = fromJson(json, RecordingRule.class);
        Assert.assertEquals(RecordingRule.Type.INCLUDE, r.getType());
        Assert.assertEquals(true, r.getAll());

        Assert.assertTrue(convertsToAndFromJson(r, RecordingRule.class));
    }

    @Test
    public void testFilters() throws IOException {
        String json = "{\n" +
                "    \"type\": \"exclude\",\n" +
                "    \"track\": \"screen\",\n" +
                "    \"kind\": \"video\",\n" +
                "    \"publisher\": \"alice\"\n" +
                "}";

        RecordingRule r = fromJson(json, RecordingRule.class);
        Assert.assertEquals(RecordingRule.Type.EXCLUDE, r.getType());
        Assert.assertEquals(RecordingRule.Kind.VIDEO, r.getKind());
        Assert.assertEquals("alice", r.getPublisher());
        Assert.assertNull(r.getAll());

        Assert.assertTrue(convertsToAndFromJson(r, RecordingRule.class));
    }

    @Test
    public void testUpdate() throws IOException {
        final RecordingRule allAudio = RecordingRule.builder()
                .withType(RecordingRule.Type.INCLUDE)
                .withKind(RecordingRule.Kind.AUDIO)
                .build();
        final RecordingRule presenterVideo = RecordingRule.builder()
                .withType(RecordingRule.Type.INCLUDE)
                .withKind(RecordingRule.Kind.VIDEO)
                .withPublisher("presenter")
                .build();

        final RecordingRulesUpdate update = new RecordingRulesUpdate(Arrays.asList(
                allAudio, presenterVideo
        ));

        Assert.assertTrue(convertsToAndFromJson(update, RecordingRulesUpdate.class));
    }
}
