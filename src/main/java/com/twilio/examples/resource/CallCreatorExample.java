package com.twilio.examples.resource;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.resources.Call;

import java.net.URI;
import java.net.URISyntaxException;

public class CallCreatorExample {

    public static void main(String[] args) {
        Twilio.setAccountSid("ACf03058d205530a5dbded37b9ee6fe61b");
        Twilio.setAuthToken("b066e13bd65fced5bef47dd51903ba18");

        try {
            URI u = new URI("http://twimlbin.com/4397e62f");
            CallCreator c = new CallCreator("+14156085895", "+14154888928", u);

            Call call = c.build();

            System.out.println(call.getSid());
        } catch(URISyntaxException e) {
            System.err.println("womp womp");
            System.exit(1);
        }

    }
}
