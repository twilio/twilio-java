package com.twilio.example.resource;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Example for making a call.
 */
public class CallCreatorExample {

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(final String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        try {

            CallCreator creator =
                Call.creator(
                    "AC123",
                    new PhoneNumber("+14156085895"),
                    new PhoneNumber("+14154888928"),
                    new URI("http://twimlbin.com/4397e62f")
                );

            Call call = creator.create();

            System.out.println(call.getSid());
            System.out.println(call.getStatus().toString());

        } catch (URISyntaxException | ApiException e) {
            System.err.println("womp womp");
            System.exit(1);
        }
    }
}
