package com.twilio.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Currency;

public class CurrencyDeserializer extends JsonDeserializer<Currency> {

    @Override
    public Currency deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {

        String currencyCode = jsonParser.readValueAs(String.class);
        return Currency.getInstance(currencyCode.toUpperCase());

    }

}
