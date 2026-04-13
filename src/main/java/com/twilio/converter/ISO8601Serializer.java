package com.twilio.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// open-api spec "format: date-time"
public class ISO8601Serializer extends JsonSerializer<ZonedDateTime> {
  @Override
  public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeString(value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
  }
}

