package com.twilio.examples;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.timing.Stopwatch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Synchronous {

    public static void main(String[] args) throws MalformedURLException, ExecutionException, InterruptedException {
        TwilioRestClient client = new TwilioRestClient("AC123", "Auth");

        Stopwatch watch = new Stopwatch();

        watch.start();
        client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com"))
                    .build();
        watch.stop(); watch.debug("Create call 1");

        watch.start();
        client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com"))
                    .build();
        watch.stop(); watch.debug("Create call 2");

        watch.start();
        client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com"))
                    .build();
        watch.stop(); watch.debug("Create call 3");
    }
}
