package com.twilio.examples;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.numbers.PhoneNumberGroup;
import com.twilio.sdk.resources.Call;

import java.net.MalformedURLException;
import java.net.URL;

public class PhoneNumberGroupExample {

    public static void main(String[] args) throws MalformedURLException {
        TwilioRestClient client = TwilioRestClient.mock();
        PhoneNumberGroup group = new PhoneNumberGroup(client);

        group.add("+14155551234");
        group.add("+14155554567");
        group.add("+14155557890");

        Call call = group.create("+14155555555", new URL("http://www.twilio.com"))
                         .build();

        System.out.println(call.getFrom());


    }
}
