package com.twilio.examples;

import com.twilio.sdk.bulk.ImmediateBulkDialer;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.numbers.PhoneNumberGroup;
import com.twilio.sdk.resources.Call;

import java.net.MalformedURLException;
import java.net.URL;

public class BulkWithPhoneNumberGroup {

    public static void main(String[] args) throws MalformedURLException {
        TwilioRestClient client = new TwilioRestClient("AC123", "AUTH");

        PhoneNumberGroup group = new PhoneNumberGroup(client);
        group.add("+14155551234");
        group.add("+14155554567");
        group.add("+14155557890");

        ImmediateBulkDialer dialer = new ImmediateBulkDialer();

        for (int i = 0; i < 100; i++) {
            dialer.add("call" + i, group.create("+141555555" + i, new URL("http://www.twilio.com")));
        }

        dialer.complete();

        int counter1234 = 0;
        int counter4567 = 0;
        int counter7890 = 0;
        int unknown = 0;

        for (Call call : dialer) {
            System.out.println(call.getFrom() + " -> " + call.getTo());
            if ("+14155551234".equals(call.getFrom())) {
                counter1234++;
            } else if ("+14155554567".equals(call.getFrom())) {
                counter4567++;
            } else if ("+14155557890".equals(call.getFrom())) {
                counter7890++;
            } else {
                unknown++;
            }
        }

        System.out.println(counter1234 + " called with the +14155551234");
        System.out.println(counter4567 + " called with the +14155554567");
        System.out.println(counter7890 + " called with the +14155557890");
        System.out.println(unknown + " called with the an UNKNOWN NUMBER");
    }
}
