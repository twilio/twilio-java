package com.twilio.converter;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;


import java.util.Currency;

public class CurrencyDeserializer extends ValueDeserializer<Currency> {

    @Override
    public Currency deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext)  {

        String currencyCode = jsonParser.readValueAs(String.class);
        return Currency.getInstance(currencyCode.toUpperCase());

    }

}
