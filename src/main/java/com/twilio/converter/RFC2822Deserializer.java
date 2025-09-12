package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;

// open-api spec "format: date-time-rfc-2822"
public class RFC2822Deserializer extends JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String dateString = parser.getText();
        return DateConverter.rfc2822DateTimeFromString(dateString);
    }
}