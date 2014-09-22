package com.twilio.examples;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.timing.Stopwatch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Asynchronous {

    public static void main(String[] args) throws MalformedURLException, ExecutionException, InterruptedException {
        TwilioRestClient client = new TwilioRestClient("AC123", "Auth");

        Stopwatch watch = new Stopwatch();

        Stopwatch futureWatch = new Stopwatch();

        futureWatch.start();

        watch.start();
        Future<Call> futureCall1 = client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")).async();
        watch.stop(); watch.debug("Create future call 1");

        watch.start();
        Future<Call> futureCall2 = client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")).async();
        watch.stop(); watch.debug("Create future call 2");

        watch.start();
        Future<Call> futureCall3 = client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")).async();
        watch.stop(); watch.debug("Create future call 3");

        futureCall1.get();
        futureCall2.get();
        futureCall3.get();

        futureWatch.stop(); futureWatch.debug("All futures complete");
    }
}
