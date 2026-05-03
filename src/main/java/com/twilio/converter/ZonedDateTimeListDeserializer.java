package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ZonedDateTimeListDeserializer extends JsonDeserializer<List<ZonedDateTime>> {

    @Override
    public List<ZonedDateTime> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        List<String> dateStrings = parser.readValueAs(new TypeReference<List<String>>() {
        });
        List<ZonedDateTime> dates = new ArrayList<>();
        for (String dateString : dateStrings) {
            ZonedDateTime date = DateConverter.rfc2822DateTimeFromString(dateString);
            if (date != null) {
                dates.add(date);
            }
        }
        return dates;
    }
}