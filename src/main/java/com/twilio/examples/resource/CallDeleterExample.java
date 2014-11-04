package com.twilio.examples.resource;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.deleters.CallDeleter;

public class CallDeleterExample {

    public static void main(String[] args) {
        Twilio.init("AC123", "AUTH TOKEN");

        CallDeleter c = new CallDeleter();
        c.execute("CA123");
        System.out.println("Deleted");
    }
}
