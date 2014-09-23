package com.twilio.examples;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.Call;

import java.util.List;

public class Querying {

    public static void main(String[] args) {
        TwilioRestClient client = new TwilioRestClient("AC123", "AUTH");

        List<Call> calls = client.calls.find()
                                       .build();

        System.out.println("All Calls");
        for (Call call : calls) {
            System.out.println(call.getFriendlyName());
        }

        calls = client.calls.find()
                            .byFriendlyName("Sample Call #1")
                            .build();

        System.out.println("By FriendlyName");
        for (Call call : calls) {
            System.out.println(call.getFriendlyName());
        }

        Call call = client.calls.get("CA123");

        System.out.println("By Sid");
        System.out.println(call.getFriendlyName());
    }
}
