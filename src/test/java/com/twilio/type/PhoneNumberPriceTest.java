package com.twilio.type;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

/**
 * Test class for {@link PhoneNumberPrice}.
 */
public class PhoneNumberPriceTest extends TypeTest {

    @Test
    public void testFromJson() throws IOException {
        String json = "{\n" +
            "    \"base_price\": 1.00,\n" +
            "    \"current_price\": 2.00,\n" +
            "    \"number_type\": \"mobile\"\n" +
            "}";

        PhoneNumberPrice pnp = fromJson(json, PhoneNumberPrice.class);
        assertEquals(1.00, pnp.getBasePrice(), 0.00);
        assertEquals(2.00, pnp.getCurrentPrice(), 0.00);
        assertEquals(PhoneNumberPrice.Type.MOBILE, pnp.getType());
    }

    @Test
    public void testFromJsonTollFreeType() throws IOException {
        String json = "{\n" +
                "    \"base_price\": 1.00,\n" +
                "    \"current_price\": 2.00,\n" +
                "    \"number_type\": \"toll free\"\n" +
                "}";

        PhoneNumberPrice pnp = fromJson(json, PhoneNumberPrice.class);
        assertEquals(1.00, pnp.getBasePrice(), 0.00);
        assertEquals(2.00, pnp.getCurrentPrice(), 0.00);
        assertEquals(PhoneNumberPrice.Type.TOLLFREE, pnp.getType());
    }

}
