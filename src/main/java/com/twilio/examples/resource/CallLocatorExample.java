package com.twilio.examples.resource;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.fetchers.CallFetcher;
import com.twilio.sdk.resources.Call;

public class CallLocatorExample {

    public static void main(final String[] args) throws AuthenticationException {
        Twilio.init("AC123", "AUTH TOKEN");

        String callSid = "CAa4df5c8404a784212bc026f48c497219";

        Call call = null;
        try {
            call = new CallFetcher(callSid).execute();
        } catch (InvalidRequestException | ApiConnectionException | ApiException | AuthenticationException e) {
            System.err.println("womp womp");
            System.exit(1);
        }

        System.out.println(call.getSid());
        System.out.println(call.getStatus().toString());

    }
}
