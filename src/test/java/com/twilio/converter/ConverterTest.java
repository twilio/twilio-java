package com.twilio.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Test class for {@link Converter}.
 */
public class ConverterTest {

    private static class Container {
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("emails")
        @Getter
        @Setter
        private Object emails;

        public String getEmails() {
            return Converter.objectToJson(emails);
        }

        public Container(Object emails) {
            this.emails = emails;
        }
    }

    @Test
    public void testAnyTypeNull() throws IOException {
        Container c = new Container(null);
        Assert.assertEquals("null", c.getEmails());
    }

    @Test
    public void testAnyTypeString() throws IOException {
        Container c = new Container("emails");
        Assert.assertEquals("emails", c.getEmails());
    }

    @Test
    public void testAnyTypeInteger() throws IOException {
        Container c = new Container(2);
        Assert.assertEquals("2", c.getEmails());
    }

    @Test
    public void testAnyTypeBoolean() {
        Container c = new Container(true);
        Assert.assertEquals("true", c.getEmails());
    }

    @Test
    public void testAnyTypeIntArray() {
        Container c = new Container(new int[]{1, 2, 3});
        Assert.assertEquals("[1, 2, 3]", c.getEmails());
    }

    @Test
    public void testAnyTypeLongArray() {
        Container c = new Container(new long[]{1L, 2L, 3L});
        Assert.assertEquals("[1, 2, 3]", c.getEmails());
    }

    @Test
    public void testAnyTypeDoubleArray() {
        Container c = new Container(new double[]{1.1, 2.2, 3.3});
        Assert.assertEquals("[1.1, 2.2, 3.3]", c.getEmails());
    }

    @Test
    public void testAnyTypeFloatArray() {
        Container c = new Container(new float[]{1.1f, 2.2f, 3.3f});
        Assert.assertEquals("[1.1, 2.2, 3.3]", c.getEmails());
    }

    @Test
    public void testAnyTypeBooleanArray() {
        Container c = new Container(new boolean[]{true, false});
        Assert.assertEquals("[true, false]", c.getEmails());
    }

    @Test
    public void testAnyTypeByteArray() {
        Container c = new Container(new byte[]{1, 2, 3});
        Assert.assertEquals("[1, 2, 3]", c.getEmails());
    }

    @Test
    public void testAnyTypeShortArray() {
        Container c = new Container(new short[]{1, 2, 3});
        Assert.assertEquals("[1, 2, 3]", c.getEmails());
    }

    @Test
    public void testAnyTypeCharArray() {
        Container c = new Container(new char[]{'a', 'b', 'c'});
        Assert.assertEquals("[a, b, c]", c.getEmails());
    }

    @Test
    public void testAnyTypeStringArray() {
        Container c = new Container(new String[]{"a", "b", "c"});
        Assert.assertEquals("[a, b, c]", c.getEmails());
    }

    @Test
    public void testAnyTypeList() {
        Container c = new Container(Arrays.asList("test@example.com", "admin@example.com"));
        Assert.assertEquals("[\"test@example.com\",\"admin@example.com\"]", c.getEmails());
    }

    @Test
    public void testAnyTypeSet() {
        Container c = new Container(new HashSet<>(Arrays.asList(5, 6)));
        // Sets have no order, so either possibility is allowed
        String result = c.getEmails();
        Assert.assertTrue(result.equals("[5,6]") || result.equals("[6,5]"));
    }

    @Test
    public void testAnyTypeMap() {
        Map<String, Object> emails = new LinkedHashMap<>();
        emails.put("a", 1);
        emails.put("b", "two");
        Container c = new Container(emails);
        Assert.assertEquals("{\"a\":1,\"b\":\"two\"}", c.getEmails());
    }
}
