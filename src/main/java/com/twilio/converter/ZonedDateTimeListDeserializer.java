package com.twilio.converter;

import tools.jackson.core.JsonParser;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ZonedDateTimeListDeserializer extends ValueDeserializer<List<ZonedDateTime>> {

    @Override
    public List<ZonedDateTime> deserialize(JsonParser parser, DeserializationContext context)  {
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