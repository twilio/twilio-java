package com.twilio.converter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for {@link PrefixedCollapsibleMap}.
 */
public class PrefixedCollapsibleMapTest {

    @Test
    public void testNull() {
        Map<String, String> actual = PrefixedCollapsibleMap.serialize(null, "Prefix");
        assertEquals(Collections.emptyMap(), actual);
    }

    @Test
    public void testSingleKey() {
        Map<String, Object> input = new HashMap<>();
        input.put("foo", "bar");

        Map<String, String> actual = PrefixedCollapsibleMap.serialize(input, "Prefix");

        Map<String, String> expected = new HashMap<>();
        expected.put("Prefix.foo", "bar");

        assertEquals(expected, actual);
    }

    @Test
    public void testNestedKeys() {
        Map<String, Object> input = new HashMap<>();
        Map<String, Object> nested = new HashMap<>();
        nested.put("bar", "baz");
        input.put("foo", nested);

        Map<String, String> actual = PrefixedCollapsibleMap.serialize(input, "Prefix");

        Map<String, String> expected = new HashMap<>();
        expected.put("Prefix.foo.bar", "baz");

        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleKeys() {
        Map<String, Object> input = new HashMap<>();
        Map<String, Object> nested = new HashMap<>();
        nested.put("bar", "baz");

        Map<String, Object> watson = new HashMap<>();
        watson.put("language", "en");
        watson.put("alice", "bob");

        input.put("foo", nested);
        input.put("watson", watson);

        Map<String, String> actual = PrefixedCollapsibleMap.serialize(input, "Prefix");

        Map<String, String> expected = new HashMap<>();
        expected.put("Prefix.foo.bar", "baz");
        expected.put("Prefix.watson.language", "en");
        expected.put("Prefix.watson.alice", "bob");

        assertEquals(expected, actual);
    }

}
