package com.twilio.examples.resource;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.CallCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.resources.Call;

import java.net.URI;
import java.net.URISyntaxException;

public class CallCreatorExample {

    public static void main(final String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        try {
            URI u = new URI("http://twimlbin.com/4397e62f");
            CallCreator c = new CallCreator("+14156085895", "+14154888928", u);

            Call call = c.execute();

            System.out.println(call.getSid());
            System.out.println(call.getStatus().toString());
        } catch (URISyntaxException | InvalidRequestException | ApiConnectionException | ApiException | AuthenticationException e) {
            System.err.println("womp womp");
            System.exit(1);
        }

    }
}
