package com.twilio.examples.resource;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.deleters.CallDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;

public class CallDeleterExample {

    public static void main(String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        CallDeleter c = new CallDeleter("CA123");
        try {
            c.execute();
        } catch (InvalidRequestException | ApiConnectionException | ApiException e) {
            System.err.println("womp womp");
            System.exit(1);
        }
        System.out.println("Deleted");
    }
}
