package com.twilio.examples.bulk;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.bulk.BulkDialer;
import com.twilio.sdk.bulk.ImmediateBulkDialer;
import com.twilio.sdk.http.ConsumableResponse;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.timing.Stopwatch;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Example of how to use the ImmediateBulkDialer.
 *
 * Twilio's next-generation helper library comes with a built in thread pool
 * executor service.  You can use this directly by replacing any calls to
 * build() with calls to async() instead.
 *
 * This can be cumbersome though for common use cases, keeping track of futures
 * and making sure you understand how to pool workers correctly.  Twilio makes
 * this easier by providing a higher level abstract, the BulkDialer.
 *
 * BulkDialer comes in two flavors, this example shows off the
 * ImmediateBulkDialer.  ImmediateBulkDialer bulk dials as quickly as possible,
 * starting every call immediately after you add it to the bulk dialer.
 */
public class ImmediateBulkDialerExample {

    public static void main(String[] args) throws URISyntaxException {
        // First we are going to mock out the network and give every request a
        // uniform delay of 1000ms (1 second)
        long requestDelay = 1000L;
        setupMocking(requestDelay);

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

        // Once we get here we know that all the inflight requests are complete
        stopwatch.stop();

        // Let's see how long that took.
        long bulkTime = stopwatch.getElapsed();
        System.out.println("Made " + callsToMake + " calls in " + bulkTime + "ms");

        // How long would that have taken if we made the calls serially?
        long serialTime = callsToMake * requestDelay;
        System.out.println("Estimated serial creation time for " + callsToMake + " calls is " + serialTime + "ms");

        // How much time did we save?
        long timeDifference = serialTime - bulkTime;
        double timeDifferencePercent = (timeDifference * 100.0) / serialTime;
        System.out.println("BulkDialer saved " + timeDifference + "ms (" + timeDifferencePercent + "%)");

        // Now to access the call objects we can treat the dialer like a Map
        Call firstCall = dialer.get("call-0");

        // See how easy it is to get a particular call
        System.out.println("First call is: " + firstCall);

        // We can also iterate over the dialer to get every call
        System.out.println("All the calls are:");
        for (Call call : dialer) {
            System.out.println("\t" + call);
        }

        // The BulkDialer makes it trivial to originate calls quickly and
        // efficiently.
        System.out.println("Example Complete :)");
    }

    private static final String JSON = "{" +
            "\"account_sid\": \"ACca498dbda0fef21f361a9a3326354175\",\n" +
            "\"annotation\": null,\n" +
            "\"answered_by\": null,\n" +
            "\"api_version\": \"2008-08-01\",\n" +
            "\"caller_name\": null,\n" +
            "\"date_created\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
            "\"date_updated\": \"Mon, 29 Sep 2014 20:39:50 +0000\",\n" +
            "\"direction\": \"inbound\",\n" +
            "\"duration\": \"8\",\n" +
            "\"end_time\": \"Mon, 29 Sep 2014 20:39:50 +0000\",\n" +
            "\"forwarded_from\": \"+19143689587\",\n" +
            "\"from\": \"+16507843280\",\n" +
            "\"from_formatted\": \"(650) 784-3280\",\n" +
            "\"group_sid\": null,\n" +
            "\"parent_call_sid\": null,\n" +
            "\"phone_number_sid\": \"PN5c269861c7f337f6876d6ef214a094cc\",\n" +
            "\"price\": \"-0.01000\",\n" +
            "\"price_unit\": \"USD\",\n" +
            "\"sid\": \"CA9e966bd3ef2abcfe941fb0a06e3fc027\",\n" +
            "\"start_time\": \"Mon, 29 Sep 2014 20:39:42 +0000\",\n" +
            "\"status\": \"completed\",\n" +
            "\"subresource_uris\": {\n" +
            "    \"notifications\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Notifications.json\",\n" +
            "    \"recordings\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027/Recordings.json\"\n" +
            "},\n" +
            "\"to\": \"+19143689587\",\n" +
            "\"to_formatted\": \"(914) 368-9587\",\n" +
            "\"uri\": \"/2010-04-01/Accounts/ACca498dbda0fef21f361a9a3326354175/Calls/CA9e966bd3ef2abcfe941fb0a06e3fc027.json\"\n" +
            "}";


    private static void setupMocking(long delay) {
        Twilio.setAccountSid("AC123");
        Twilio.setAuthToken("AUTH TOKEN");
        Twilio.setMockDelay(delay);
        Twilio.useMockResponses(new ConsumableResponse(JSON, 201));
    }

}
