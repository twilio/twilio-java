package com.twilio.example.resource;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.readers.api.v2010.account.CallReader;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.api.v2010.account.Call;

/**
 * Fetch a list of calls.
 */
public class CallReaderExample {

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(final String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        try {

            CallReader reader = new CallReader("AC123");
            ResourceSet<Call> calls = reader.execute();

            int idx = 1;
            for (Call call : calls) {
                System.out.println(idx + ": " + call.getSid());
                idx++;
            }

            System.out.println("All Done");

        } catch (ApiException e) {
            System.err.println("womp womp");
            System.exit(1);
        }
    }
}
