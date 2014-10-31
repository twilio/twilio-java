package com.twilio.examples.resource;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.resources.Call;
import com.twilio.sdk.updaters.CallUpdater;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CallUpdaterExample {
    public static void main(String[] args) {
        Twilio.setAccountSid("ACf03058d205530a5dbded37b9ee6fe61b");
        Twilio.setAuthToken("b066e13bd65fced5bef47dd51903ba18");

        try {
            URI u = new URI("http://twimlbin.com/cc413d9d");
            CallCreator c = new CallCreator("+14156085895", "+14154888928", u);

            Call call = c.build();

            System.out.println(call.getSid());
            System.out.println(call.getStatus().toString());

            System.out.println("press enter once call is accepted");
            try {
                System.in.read();
            } catch (IOException e) {
                // blah
                System.out.println("whoops");
            }

            CallUpdater updater = new CallUpdater();
            updater.setUrl(new URI("http://twimlbin.com/4397e62f"));
            Call updated = updater.build(call);

            System.out.println(updated.getSid());
            System.out.println(updated.getStatus().toString());

        } catch(URISyntaxException e) {
            System.err.println("womp womp");
            System.exit(1);
        }
    }
}
