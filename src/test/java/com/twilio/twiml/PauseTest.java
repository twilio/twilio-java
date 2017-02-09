package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Pause}.
 */
public class PauseTest {

    @Test
    public void testXml() throws TwiMLException {
        Pause pause = new Pause.Builder()
            .length(5)
            .build();

        Assert.assertEquals("<Pause length=\"5\"/>", pause.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Pause pause = new Pause.Builder()
            .length(5)
            .build();

        Assert.assertEquals("%3CPause+length%3D%225%22%2F%3E", pause.toUrl());
    }

    @Test
    public void testOptionsXml() throws TwiMLException {
        Pause pause = new Pause.Builder()
                .length(5)
                .options("foo", "bar")
                .build();

        Assert.assertEquals("<Pause length=\"5\" foo=\"bar\"/>", pause.toXml());
    }

    @Test
    public void testOptionsUrl() throws TwiMLException {
        Pause pause = new Pause.Builder()
                .length(5)
                .options("foo", "bar")
                .build();

        Assert.assertEquals("%3CPause+length%3D%225%22+foo%3D%22bar%22%2F%3E", pause.toUrl());
    }
}
