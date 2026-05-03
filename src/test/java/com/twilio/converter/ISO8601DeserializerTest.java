package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZonedDateTime;

public class ISO8601DeserializerTest {

    @Test
    public void testDeserializeValidDateTime() throws IOException {
        // Arrange
        String validDateTime = "2023-10-01T12:34:56Z";
        ZonedDateTime expectedDateTime = ZonedDateTime.parse(validDateTime);

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(validDateTime);

        ISO8601Deserializer deserializer = new ISO8601Deserializer();

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
        ISO8601Deserializer deserializer = new ISO8601Deserializer();

        // Act
        ZonedDateTime result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testDeserializeNullDateTime() throws IOException {
        // Arrange
        String invalidDateTime = null;
        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);
        Mockito.when(mockParser.getText()).thenReturn(invalidDateTime);
        ISO8601Deserializer deserializer = new ISO8601Deserializer();

        // Act
        ZonedDateTime result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertNull(result);
    }
}