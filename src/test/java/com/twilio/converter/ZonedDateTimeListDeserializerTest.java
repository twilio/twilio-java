package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ZonedDateTimeListDeserializerTest {

    @Test
    public void testDeserializeValidDateTimeList() throws IOException {
        // Arrange
        List<String> validDateTimes = Arrays.asList(
                "Tue, 03 Oct 2023 14:28:00 +0000",
                "Wed, 04 Oct 2023 10:15:30 +0000"
        );
        List<ZonedDateTime> expectedDates = Arrays.asList(
                DateConverter.rfc2822DateTimeFromString(validDateTimes.get(0)),
                DateConverter.rfc2822DateTimeFromString(validDateTimes.get(1))
        );

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.readValueAs(Mockito.<TypeReference<List<String>>>any()))
                .thenReturn(validDateTimes);

        ZonedDateTimeListDeserializer deserializer = new ZonedDateTimeListDeserializer();

        // Act
        List<ZonedDateTime> result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertEquals(expectedDates, result);
    }

    @Test
    public void testDeserializeInvalidDateTimeList() throws IOException {
        // Arrange
        List<String> mixedDateTimes = Arrays.asList(
                "Tue, 03 Oct 2023 14:28:00 +0000",
                "invalid-date-time"
        );
        List<ZonedDateTime> expectedDates = Collections.singletonList(
                DateConverter.rfc2822DateTimeFromString(mixedDateTimes.get(0))
        );

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.readValueAs(Mockito.<TypeReference<List<String>>>any()))
                .thenReturn(mixedDateTimes);

        ZonedDateTimeListDeserializer deserializer = new ZonedDateTimeListDeserializer();

        // Act
        List<ZonedDateTime> result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertEquals(expectedDates, result);
    }

    @Test
    public void testDeserializeEmptyDateTimeList() throws IOException {
        // Arrange
        List<String> emptyDateTimes = Collections.emptyList();

        JsonParser mockParser = Mockito.mock(JsonParser.class);
        DeserializationContext mockContext = Mockito.mock(DeserializationContext.class);

        Mockito.when(mockParser.readValueAs(Mockito.<TypeReference<List<String>>>any()))
                .thenReturn(emptyDateTimes);

        ZonedDateTimeListDeserializer deserializer = new ZonedDateTimeListDeserializer();

        // Act
        List<ZonedDateTime> result = deserializer.deserialize(mockParser, mockContext);

        // Assert
        Assert.assertTrue(result.isEmpty());
    }
}