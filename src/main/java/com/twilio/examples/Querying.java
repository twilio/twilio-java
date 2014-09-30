package com.twilio.examples;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.resources.Call;

import java.util.List;

public class Querying {

    public static void main(String[] args) {
        TwilioRestClient client = TwilioRestClient.mock();

        List<Call> calls = client.calls.find()
                                       .build();

        System.out.println("All Calls");
        for (Call call : calls) {
            System.out.println(call.getSid());
        }

        calls = client.calls.find()
                            .byFriendlyName("Sample Call #1")
                            .build();

        System.out.println("By FriendlyName");
        for (Call call : calls) {
            System.out.println(call.getSid());
        }

        Call call = client.calls.get("CA123");

        System.out.println("By Sid");
        System.out.println(call.getSid());
    }
}
