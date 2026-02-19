package com.twilio.converter;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;


import java.time.LocalDate;

// open-api spec "format: date"
public class LocalDateDeserializer extends ValueDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context)  {
        String dateString = parser.getText();
        return DateConverter.localDateFromString(dateString);
    }
}