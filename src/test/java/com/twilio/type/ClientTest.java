package com.twilio.type;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void testGetEndpoint() {
        assertEquals("client:me", new Client("me").getEndpoint());
        assertEquals("client:YOU", new Client("YOU").getEndpoint());
        assertEquals("CLIENT:HIM", new Client("CLIENT:HIM").getEndpoint());
        assertEquals("cLiEnT:her", new Client("cLiEnT:her").getEndpoint());
    }
}
