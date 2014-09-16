package com.twilio.sdk;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.RequiredFieldException;
import com.twilio.sdk.resources.Call;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        TwilioRestClient client = new TwilioRestClient("AC123", "Auth");

        Call call = client.calls.create()
                                .setTo("+14155551234")
                                .setFrom("+14155557890")
                                .setUrl(new URL("http://www.twilio.com"))
                                .go();

        dumpCall(call);

        call = client.calls.update(call)
                           .setFriendlyName("Hello World")
                           .go();

        dumpCall(call);

        call = client.calls.update()
                           .setFriendlyName("Goodbye Demo")
                           .go(call);

        dumpCall(call);

        try {
            call = client.calls.create()
                               .setTo("+14155551234")
                               .go();
        } catch(RequiredFieldException e) {
            System.out.println(e.getFieldMessage());
        }
    }

    protected static void dumpCall(Call call) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Call to:           " + call.getTo());
        System.out.println("Call from:         " + call.getFrom());
        System.out.println("Call url:          " + call.getUrl());
        System.out.println("Call friendlyName: " + call.getFriendlyName());
        System.out.println("-------------------------------------------------");
        System.out.println();
    }
}
