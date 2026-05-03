package com.twilio.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ISO8601SerializerTest {

    @Test
    public void testSerializeUTCDateTime() throws IOException {
        // Arrange
        ZonedDateTime dateTime = ZonedDateTime.of(2023, 10, 1, 12, 34, 56, 0, ZoneOffset.UTC);
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        SerializerProvider mockProvider = Mockito.mock(SerializerProvider.class);

        ISO8601Serializer serializer = new ISO8601Serializer();

        // Act
        serializer.serialize(dateTime, mockGen, mockProvider);

        // Assert
        Mockito.verify(mockGen).writeString("2023-10-01T12:34:56Z");
    }

    @Test
    public void testSerializeDateTimeWithOffset() throws IOException {
        // Arrange
        ZonedDateTime dateTime = ZonedDateTime.of(2023, 10, 1, 12, 34, 56, 0, ZoneOffset.ofHours(5));
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        SerializerProvider mockProvider = Mockito.mock(SerializerProvider.class);

        ISO8601Serializer serializer = new ISO8601Serializer();

        // Act
        serializer.serialize(dateTime, mockGen, mockProvider);

        // Assert
        Mockito.verify(mockGen).writeString("2023-10-01T12:34:56+05:00");
    }

    @Test
    public void testSerializeDateTimeWithNegativeOffset() throws IOException {
        // Arrange
        ZonedDateTime dateTime = ZonedDateTime.of(2023, 6, 15, 8, 0, 0, 0, ZoneOffset.ofHours(-7));
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        SerializerProvider mockProvider = Mockito.mock(SerializerProvider.class);

        ISO8601Serializer serializer = new ISO8601Serializer();

        // Act
        serializer.serialize(dateTime, mockGen, mockProvider);

        // Assert
        Mockito.verify(mockGen).writeString("2023-06-15T08:00:00-07:00");
    }

    @Test
    public void testSerializeDateTimeWithNanoseconds() throws IOException {
        // Arrange
        ZonedDateTime dateTime = ZonedDateTime.of(2023, 10, 1, 12, 34, 56, 123000000, ZoneOffset.UTC);
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        SerializerProvider mockProvider = Mockito.mock(SerializerProvider.class);

        ISO8601Serializer serializer = new ISO8601Serializer();

        // Act
        serializer.serialize(dateTime, mockGen, mockProvider);

        // Assert
        Mockito.verify(mockGen).writeString("2023-10-01T12:34:56.123Z");
    }

    @Test
    public void testSerializeDateTimeWithZoneId() throws IOException {
        // Arrange
        ZonedDateTime dateTime = ZonedDateTime.of(2023, 1, 15, 10, 30, 0, 0, ZoneId.of("America/New_York"));
        JsonGenerator mockGen = Mockito.mock(JsonGenerator.class);
        SerializerProvider mockProvider = Mockito.mock(SerializerProvider.class);

        ISO8601Serializer serializer = new ISO8601Serializer();

        // Act
        serializer.serialize(dateTime, mockGen, mockProvider);

        // Assert
        Mockito.verify(mockGen).writeString("2023-01-15T10:30:00-05:00");
    }
}
