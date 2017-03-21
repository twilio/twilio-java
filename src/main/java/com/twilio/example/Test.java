package com.twilio.example;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.pricing.v1.phonenumber.CountryFetcher;

/**
 * Created by efossier on 3/9/17.
 */
public class Test {
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();

        new CountryFetcher("US").fetch(client);
    }
}
