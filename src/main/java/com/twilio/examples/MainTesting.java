package com.twilio.examples;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creators.api.v2010.account.MessageCreator;
import com.twilio.sdk.creators.trunking.v1.TrunkCreator;
import com.twilio.sdk.deleters.trunking.v1.TrunkDeleter;
import com.twilio.sdk.fetchers.trunking.v1.TrunkFetcher;
import com.twilio.sdk.readers.api.v2010.account.CallReader;
import com.twilio.sdk.resources.api.v2010.account.Call;
import com.twilio.sdk.resources.api.v2010.account.Message;
import com.twilio.sdk.resources.trunking.v1.Trunk;
import com.twilio.sdk.updaters.trunking.v1.TrunkUpdater;
import com.twilio.types.PhoneNumber;

public class MainTesting {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // List calls
        Iterable<Call> calls = new CallReader(ACCOUNT_SID).pageSize(3).execute();
        for (Call call : calls) {
            System.out.println(call.getSid());
        }

        // Send a message
        Message message = new MessageCreator(
            ACCOUNT_SID,
            new PhoneNumber("+18584618959"),
            new PhoneNumber("+13067005464"),
            "Hello world!"
        ).execute();
        System.out.println("Message");
        System.out.println(message);

        // Create a SIP Trunk
        Trunk trunk = new TrunkCreator()
            .setFriendlyName("Java Trunk")
            .execute();
        System.out.println("Trunk");
        System.out.println(trunk);

        // Fetch the trunk
        Trunk ft = new TrunkFetcher(trunk.getSid()).execute();
        System.out.println("Fetched Trunk");
        System.out.println(ft);

        // Update a trunk
        Trunk ut = new TrunkUpdater(trunk.getSid())
            .setFriendlyName("Updated Trunk")
            .execute();
        System.out.println("Updated Trunk");
        System.out.println(ut);

        // Delete the trunk
        boolean dt = new TrunkDeleter(trunk.getSid()).execute();
        System.out.println("Deleted Trunk");
        System.out.println(dt);

        // TwiML
//        TwiML twiml = new VoiceResponse.Builder()
//            .say(new Say.Builder("Hello World!").build())
//            .play(new Play.Builder("https://api.twilio.com/cowbell.mp3").loop(5).build())
//            .build();
//        System.out.println(twiml);
    }

}