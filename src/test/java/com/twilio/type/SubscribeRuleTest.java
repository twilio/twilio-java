package com.twilio.type;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;


public class SubscribeRuleTest extends TypeTest {
    @Test
    public void testBuilderOneFilter() {
        try {
            SubscribeRule.builder().withType(SubscribeRule.Type.INCLUDE).withPublisher(null).build();
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Rule builder should enforce a filter");
    }

    @Test
    public void testBuilderMustHaveType() {
        try {
            SubscribeRule.builder().withType(null).withPublisher("alice").build();
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

        SubscribeRule r = fromJson(json, SubscribeRule.class);
        assertEquals(SubscribeRule.Type.INCLUDE, r.getType());
        assertEquals(true, r.getAll());

        assertTrue(convertsToAndFromJson(r, SubscribeRule.class));
    }

    @Test
    public void testFilters() throws IOException {
        String json = "{\n" +
                "    \"type\": \"exclude\",\n" +
                "    \"track\": \"screen\",\n" +
                "    \"kind\": \"video\",\n" +
                "    \"publisher\": \"alice\",\n" +
                "    \"priority\": \"standard\"\n" +
                "}";

        SubscribeRule r = fromJson(json, SubscribeRule.class);
        assertEquals(SubscribeRule.Type.EXCLUDE, r.getType());
        assertEquals(SubscribeRule.Kind.VIDEO, r.getKind());
        assertEquals("alice", r.getPublisher());
        assertEquals(SubscribeRule.Priority.STANDARD, r.getPriority());
        assertNull(r.getAll());

        assertTrue(convertsToAndFromJson(r, SubscribeRule.class));
    }

    @Test
    public void testUpdate() throws IOException {
        final SubscribeRule allAudio = SubscribeRule.builder()
                .withType(SubscribeRule.Type.INCLUDE)
                .withKind(SubscribeRule.Kind.AUDIO)
                .build();
        final SubscribeRule presenterVideo = SubscribeRule.builder()
                .withType(SubscribeRule.Type.INCLUDE)
                .withKind(SubscribeRule.Kind.VIDEO)
                .withPublisher("presenter")
                .build();

        final SubscribeRulesUpdate update = new SubscribeRulesUpdate(Arrays.asList(
                allAudio, presenterVideo
        ));

        assertTrue(convertsToAndFromJson(update, SubscribeRulesUpdate.class));
    }
}
