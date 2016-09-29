package com.twilio.example;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumberCreator;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.trunking.v1.TrunkCreator;
import com.twilio.rest.api.v2010.account.CallReader;
import com.twilio.rest.api.v2010.account.MessageReader;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.LocalReader;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Local;
import com.twilio.rest.notify.v1.Service;
import com.twilio.rest.trunking.v1.Trunk;
import com.twilio.type.PhoneNumber;
import com.twilio.twiml.Play;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import java.net.URI;
import java.util.Iterator;

public class Example {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static final PhoneNumber PHONE_NUMBER = new PhoneNumber("+18885551234");

    /**
     * Example Twilio usage.
     *
     * @param args command line args
     * @throws TwiMLException if unable to generate TwiML
     */
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
        ).create();

        System.out.println(message.getSid());
        System.out.println(message.getBody());

        // Make a phone call
        Call call = new CallCreator(
            ACCOUNT_SID,
            PHONE_NUMBER,
            number.getPhoneNumber(),
            URI.create("https://twilio.com")
        ).create();
        System.out.println(call.getSid());

        // Print all the messages
        Iterable<Message> messages = new MessageReader().read();
        for (Message m : messages) {
            System.out.println(m.getSid());
            System.out.println(m.getBody());
        }

        // Get some calls
        Iterable<Call> calls = new CallReader().pageSize(2).read();
        for (Call c : calls) {
            System.out.println(c.getSid());
        }

        Trunk trunk = new TrunkCreator()
            .setFriendlyName("shiny trunk")
            .setSecure(false)
            .create();

        System.out.println(trunk);

        // Delete a resource
        Service service = Service.creator().create();
        boolean result = Service.deleter(service.getSid()).delete();
        System.out.println(result);

        Iterable<Service> services = Service.reader().pageSize(2).read();
        int j = 0;
        for (Service s : services) {
            System.out.println("Service " + j + ": " + s.getSid());
            j++;
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
        Iterable<Local> numbers = new LocalReader(ACCOUNT_SID, "US").read();

        // Buy the first phone number
        Iterator<Local> iter = numbers.iterator();
        if (iter.hasNext()) {
            Local local = iter.next();
            return new IncomingPhoneNumberCreator(
                ACCOUNT_SID,
                local.getPhoneNumber()
            ).create();
        }

        return null;
    }

}