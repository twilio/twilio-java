package com.twilio.converter;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.time.ZonedDateTime;

// open-api spec "format: date-time"
public class ISO8601Deserializer extends ValueDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser parser, DeserializationContext context) {
        String dateString = parser.getText();
        return DateConverter.iso8601DateTimeFromString(dateString);
    }
}