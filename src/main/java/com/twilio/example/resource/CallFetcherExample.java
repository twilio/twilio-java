package com.twilio.example.resource;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.CallFetcher;
import com.twilio.rest.api.v2010.account.Call;

/**
 * Fetch a call.
 */
public class CallFetcherExample {

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        try {

            CallFetcher fetcher = Call.fetcher("AC123", "CA123");
            Call call = fetcher.fetch();

            System.out.print(call.getSid());

        } catch (ApiException e) {

            System.err.println("womp womp");
            System.exit(1);

        }
    }

}
