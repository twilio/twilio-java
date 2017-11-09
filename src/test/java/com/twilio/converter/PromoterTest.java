package com.twilio.converter;

import com.google.common.collect.Lists;
import com.twilio.type.PhoneNumber;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

/**
 * Test class for {@link Promoter}
 */
public class PromoterTest {

    @Test
    public void testPromoteUri() {
        URI uri = Promoter.uriFromString("https://trunking.twilio.com/v1/Trunks/TK123/OriginationUrls");
        Assert.assertEquals(
            "https://trunking.twilio.com/v1/Trunks/TK123/OriginationUrls",
            uri.toString()
        );
    }

    @Test
    public void testPhoneNumberFromString() {
        PhoneNumber pn = Promoter.phoneNumberFromString("+12345678910");
        Assert.assertEquals(new PhoneNumber("+12345678910"), pn);
    }

    @Test
    public void testPromoteList() {
        String s = "hi";
        Assert.assertEquals(
            Lists.newArrayList(s),
            Promoter.listOfOne(s)
        );
    }
}
