package com.twilio.examples;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.api.v2010.account.CallCreator;
import com.twilio.sdk.creators.api.v2010.account.IncomingPhoneNumberCreator;
import com.twilio.sdk.creators.api.v2010.account.MessageCreator;
import com.twilio.sdk.readers.api.v2010.account.CallReader;
import com.twilio.sdk.readers.api.v2010.account.MessageReader;
import com.twilio.sdk.readers.api.v2010.account.availablephonenumbercountry.LocalReader;
import com.twilio.sdk.resources.api.v2010.account.Call;
import com.twilio.sdk.resources.api.v2010.account.IncomingPhoneNumber;
import com.twilio.sdk.resources.api.v2010.account.Message;
import com.twilio.sdk.resources.api.v2010.account.availablephonenumbercountry.Local;
import com.twilio.sdk.twiml.Play;
import com.twilio.sdk.twiml.Say;
import com.twilio.sdk.twiml.TwiML;
import com.twilio.sdk.twiml.TwiMLException;
import com.twilio.sdk.twiml.VoiceResponse;
import com.twilio.types.PhoneNumber;

import java.net.URI;
import java.util.Iterator;

public class MainTesting {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static final PhoneNumber PHONE_NUMBER = new PhoneNumber("+18885551234");

    public static void main(String[] args) throws TwiMLException {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Get a number
        IncomingPhoneNumber number = buyNumber();
        System.out.println(number.getPhoneNumber());

        // Send a text message
        Message message = new MessageCreator(
            ACCOUNT_SID,
            PHONE_NUMBER,
            number.getPhoneNumber(),
            "Hello world!"
        ).execute();
        System.out.println(message.getSid());
        System.out.println(message.getBody());

        // Make a phone call
        Call call = new CallCreator(
            ACCOUNT_SID,
            PHONE_NUMBER,
            number.getPhoneNumber(),
            URI.create("https://twilio.com")
        ).execute();
        System.out.println(call.getSid());

        // Print all the messages
        Iterable<Message> messages = new MessageReader(
            ACCOUNT_SID
        ).execute();
        for (Message m : messages) {
            System.out.println(m.getSid());
            System.out.println(m.getBody());
        }

        // Get some calls
        Iterable<Call> calls = new CallReader(ACCOUNT_SID).pageSize(2).execute();
        for (Call c : calls) {
            System.out.println(c.getSid());
        }

        // TwiML
        TwiML twiml = new VoiceResponse.Builder()
            .say(new Say.Builder("Hello World!").build())
            .play(new Play.Builder("https://api.twilio.com/cowbell.mp3").loop(5).build())
            .build();
        System.out.println(twiml.toXml());
    }

    private static IncomingPhoneNumber buyNumber() {
        // Look up some phone numbers
        Iterable<Local> numbers = new LocalReader(ACCOUNT_SID, "US").execute();

        // Buy the first phone number
        Iterator<Local> iter = numbers.iterator();
        if (iter.hasNext()) {
            Local local = iter.next();
            return new IncomingPhoneNumberCreator(
                ACCOUNT_SID,
                local.getPhoneNumber()
            ).execute();
        }

        return null;
    }

}