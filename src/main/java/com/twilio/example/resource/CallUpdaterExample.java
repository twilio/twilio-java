package com.twilio.example.resource;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallUpdater;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Update a call.
 */
public class CallUpdaterExample {

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(final String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        try {

            CallCreator creator = Call.creator(
                "AC123",
                new PhoneNumber("+14156085895"),
                new PhoneNumber("+14154888928"),
                new URI("http://twimlbin.com/cc413d9d")
            );
            Call call = creator.create();

            System.out.println(call.getSid());
            System.out.println(call.getStatus());

            System.out.println("press enter once call is accepted");
            try {
                System.in.read();
            } catch (final IOException e) {
                System.out.println("whoops");
            }

            CallUpdater updater = Call.updater(
                "AC123",
                call.getSid()
            ).setUrl(new URI("http://twimlbin.com/4397e62f"));
            Call updated = updater.update();

            System.out.println(updated.getSid());
            System.out.println(updated.getStatus());

        } catch (URISyntaxException | ApiException e) {

            System.err.println("womp womp");
            System.exit(1);

        }
    }
}
