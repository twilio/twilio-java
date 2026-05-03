package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZonedDateTime;

public class RFC2822DeserializerTest {

    @Test
    public void testDeserializeValidDateTime() throws IOException {
        // Arrange
        String validDateTime = "Wed, 23 Jul 2025 06:18:51 +0000";
        ZonedDateTime expectedDateTime = DateConverter.rfc2822DateTimeFromString(validDateTime);

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(validDateTime);

        RFC2822Deserializer deserializer = new RFC2822Deserializer();

        // Act
        ZonedDateTime result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertEquals(expectedDateTime, result);
    }

    @Test
    public void testDeserializeInvalidDateTime() throws IOException {
        // Arrange
        String invalidDateTime = "invalid-date-time";

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(invalidDateTime);

        RFC2822Deserializer deserializer = new RFC2822Deserializer();

        // Act
        ZonedDateTime result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testDeserializeEmptyDateTime() throws IOException {
        // Arrange
        String emptyDateTime = "";

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(emptyDateTime);

        RFC2822Deserializer deserializer = new RFC2822Deserializer();

        // Act
        ZonedDateTime result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertNull(result);
    }
}