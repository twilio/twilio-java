package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializerTest {

    @Test
    public void testDeserializeValidDate() throws IOException {
        // Arrange
        String validDate = "2023-10-01";
        LocalDate expectedDate = LocalDate.parse(validDate);

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(validDate);

        LocalDateDeserializer deserializer = new LocalDateDeserializer();

        // Act
        LocalDate result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertEquals(expectedDate, result);
    }

    @Test
    public void testDeserializeInvalidDate() throws IOException {
        // Arrange
        String invalidDate = "invalid-date";

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(invalidDate);

        LocalDateDeserializer deserializer = new LocalDateDeserializer();

        // Act
        LocalDate result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testDeserializeEmptyDate() throws IOException {
        // Arrange
        String emptyDate = "";

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.getText()).thenReturn(emptyDate);

        LocalDateDeserializer deserializer = new LocalDateDeserializer();

        // Act
        LocalDate result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertNull(result);
    }
}