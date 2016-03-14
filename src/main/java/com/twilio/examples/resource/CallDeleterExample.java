package com.twilio.examples.resource;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.deleters.api.v2010.account.CallDeleter;
import com.twilio.sdk.exceptions.ApiException;

/**
 * Call Deletion example.
 */
public class CallDeleterExample {

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(final String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        try {

            CallDeleter deleter = new CallDeleter("AC123", "CA123");
            deleter.execute();

        } catch (ApiException e) {

            System.err.println("womp womp");
            System.exit(1);

        }

        System.out.println("Deleted");
    }
}
