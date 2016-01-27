package com.twilio.examples.resource;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.AuthenticationException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.api.v2010.account.Call;

public class CallLocatorExample {

    public static void main(final String[] args) {
        Twilio.init("ACca498dbda0fef21f361a9a3326354175", "8b20fad4aaf29e4d9f30ee0994a2e3bd");

        ResourceSet<Call> calls = Call.read("AC123").execute();

        calls.setAutoPaging(false);

        int idx = 1;

        for (Call call : calls) {
            System.out.println(idx + ": " + call.getSid());
            idx++;
        }

        System.out.println("All Done");
    }
}
