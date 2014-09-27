package com.twilio.sdk.numbers;

import com.twilio.sdk.clients.TwilioRestClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhoneNumberGroupTest {
    protected PhoneNumberGroup group;

    @Before
    public void setUp() throws Exception {
        TwilioRestClient client = TwilioRestClient.mock();

        group = new PhoneNumberGroup(client);
        group.add("+14155551234");
        group.add("+14155554567");
        group.add("+14155557890");
    }


    @Test
    public void testGet() throws Exception {
        assertEquals("+14155551234", group.get("+14155555432"));
        assertEquals("+14155554567", group.get("+14155557659"));
        assertEquals("+14155557890", group.get("+14155550987"));
    }
}