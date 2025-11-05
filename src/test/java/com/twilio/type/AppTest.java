package com.twilio.type;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testGetEndpoint() {
        Assert.assertEquals("app:me", new App("me").getEndpoint());
        Assert.assertEquals("app:YOU", new App("YOU").getEndpoint());
        Assert.assertEquals("APP:HIM", new App("APP:HIM").getEndpoint());
        Assert.assertEquals("aPp:her", new App("aPp:her").getEndpoint());
        Assert.assertEquals("app:AP12345?mycustomparam1=foo&mycustomparam2=bar", new App("app:AP12345?mycustomparam1=foo&mycustomparam2=bar").getEndpoint());
        Assert.assertEquals("app:AP12345?mycustomparam1=foo&mycustomparam2=bar", new App("AP12345?mycustomparam1=foo&mycustomparam2=bar").getEndpoint());
    }
}