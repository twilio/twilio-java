package com.twilio.examples;

import com.twilio.sdk.bulk.DeferredBulkDialer;
import com.twilio.sdk.bulk.ImmediateBulkDialer;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.timing.Stopwatch;

import java.net.MalformedURLException;
import java.net.URL;

public class Bulk {

    public static void main(String[] args) throws MalformedURLException {
        TwilioRestClient client = new TwilioRestClient("AC123", "Auth");

        Stopwatch immediateWatch = new Stopwatch();

        immediateWatch.start();

        ImmediateBulkDialer immediateDialer = new ImmediateBulkDialer();

        immediateDialer.add("call1", client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")));
        immediateDialer.add("call2", client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")));
        immediateDialer.add("call3", client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")));

        immediateDialer.complete();

        immediateWatch.stop(); immediateWatch.debug("ImmediateBulkDialer");

        Stopwatch deferredWatch = new Stopwatch();

        deferredWatch.start();

        DeferredBulkDialer deferredDialer = new DeferredBulkDialer();

        deferredDialer.add("call1", client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")));
        deferredDialer.add("call2", client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")));
        deferredDialer.add("call3", client.calls.create("+14155551234", "+14155557890", new URL("http://www.twilio.com")));

        deferredDialer.complete();

        deferredWatch.stop(); deferredWatch.debug("DeferredBulkDialer");

    }
}
