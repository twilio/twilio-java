package com.twilio.examples.bulk;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.bulk.BulkDialer;
import com.twilio.sdk.bulk.ImmediateBulkDialer;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.timing.Stopwatch;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Example of how to use the ImmediateBulkDialer.
 * <p/>
 * Twilio's next-generation helper library comes with a built in thread pool executor service.  You can use this
 * directly by replacing any calls to execute() with calls to async() instead.
 * <p/>
 * This can be cumbersome though for common use cases, keeping track of futures and making sure you understand how to
 * pool workers correctly.  Twilio makes this easier by providing a higher level abstract, the BulkDialer.
 * <p/>
 * BulkDialer comes in two flavors, this example shows off the ImmediateBulkDialer.  ImmediateBulkDialer bulk dials as
 * quickly as possible, starting every call immediately after you add it to the bulk dialer.
 */
public class ImmediateBulkDialerExample {

    public static void main(final String[] args) throws URISyntaxException {
        // Change those value to your Twilio account Sid and authentication token.
        Twilio.init("AC123", "AUTH TOKEN");

        // Next we create a new BulkDialer instance
        BulkDialer dialer = new ImmediateBulkDialer();

        // Here we define how many calls we would like to make, feel free to
        // change this number to see how it effects the runtime behavior of the
        // bulk dialer
        int callsToMake = 10;

        // Create a URL to use as the Action URL for the call creation
        URI uri = new URI("http://example.com");

        // To see how the bulk dialer is more efficient, let's start a timer
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        // Create callsToMake number of calls, using the bulk dialer
        for (int i = 0; i < callsToMake; i++) {
            dialer.add("call-" + i, Call.create("+14155551234", "+14155557890", uri));
        }

        // The dialer makes the calls asynchronously with a pool of workers, it
        // would be cheating to stop the stopwatch before we were sure that all
        // the calls got created.  We can ask the dialer to synchronously block
        // by calling the .complete() method
        dialer.complete();

        // Once we get here we know that all the in-flight requests are complete
        stopwatch.stop();

        // Let's see how long that took.
        long bulkTime = stopwatch.getElapsed();
        System.out.println("Made " + callsToMake + " calls in " + bulkTime + "ms");

        // Now to access the call objects we can treat the dialer like a Map
        Call firstCall = dialer.get("call-0");

        // See how easy it is to get a particular call
        System.out.println("First call is: " + firstCall);

        // We can also iterate over the dialer to get every call
        System.out.println("All the calls are:");
        for (final Call call : dialer) {
            System.out.println("\t" + call);
        }

        // The BulkDialer makes it trivial to originate calls quickly and
        // efficiently.
        System.out.println("Example Complete :)");
    }

}
