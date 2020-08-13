package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

public class ClientTest {

    @Test
    public void testGetEndpoint() {
        Assert.assertEquals("client:me", new Client("me").getEndpoint());
        Assert.assertEquals("client:YOU", new Client("YOU").getEndpoint());
        Assert.assertEquals("CLIENT:HIM", new Client("CLIENT:HIM").getEndpoint());
        Assert.assertEquals("cLiEnT:her", new Client("cLiEnT:her").getEndpoint());
    }
}
