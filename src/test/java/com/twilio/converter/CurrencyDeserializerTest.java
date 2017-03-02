package com.twilio.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Currency;

/**
 * Test class for {@link CurrencyDeserializer}.
 */
public class CurrencyDeserializerTest {

    @Test
    public void testDeserialize() throws IOException {
        String json = "{ \"currency\": \"usd\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container c = Container.fromJson(json, mapper);
        Assert.assertEquals("USD", c.currency.getCurrencyCode());
    }

    @Test(expected = JsonMappingException.class)
    public void testInvalidCurrency() throws IOException {
        String json = "{ \"currency\": \"poo\" }";
        ObjectMapper mapper = new ObjectMapper();

        Container.fromJson(json, mapper);
    }

    private static class Container {
        private final Currency currency;

        public Container(
            @JsonProperty("currency")
            @JsonDeserialize(using = CurrencyDeserializer.class)
            Currency currency
        ) {
            this.currency = currency;
        }

        public static Container fromJson(String json, ObjectMapper mapper) throws IOException {
            return mapper.readValue(json, Container.class);
        }
    }

}
