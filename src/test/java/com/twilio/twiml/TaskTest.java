package com.twilio.twiml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Task}.
 */
public class TaskTest {

    @Test
    public void testXml() throws TwiMLException {
        Task task = new Task.Builder()
            .data("foobar")
            .timeout(5)
            .priority(1)
            .build();

        Assert.assertEquals("<Task priority=\"1\" timeout=\"5\">foobar</Task>", task.toXml());
    }

    @Test
    public void testXmlNoDefaults() throws TwiMLException {
        Task task = new Task.Builder()
            .data("foobar")
            .timeout(5)
            .build();

        Assert.assertEquals("<Task timeout=\"5\">foobar</Task>", task.toXml());
    }

    @Test
    public void testUrl() throws TwiMLException {
        Task task = new Task.Builder()
            .data("foobar")
            .timeout(5)
            .priority(1)
            .build();

        Assert.assertEquals("%3CTask+priority%3D%221%22+timeout%3D%225%22%3Efoobar%3C%2FTask%3E", task.toUrl());
    }
}
