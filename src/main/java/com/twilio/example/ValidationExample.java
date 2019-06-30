package com.twilio.example;


import com.twilio.http.TwilioRestClient;
import com.twilio.http.ValidationClient;
import com.twilio.rest.accounts.v1.credential.PublicKey;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.NewSigningKey;
import com.twilio.twiml.TwiMLException;
import com.twilio.type.PhoneNumber;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class ValidationExample {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    /**
     * Example Twilio usage.
     *
     * @param args command line args
     * @throws TwiMLException if unable to generate TwiML
     */
    public static void main(String[] args) throws Exception {

        // Generate public/private key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        java.security.PublicKey pk = pair.getPublic();

        // Use the default rest client
        TwilioRestClient client =
            new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN)
                .build();

        // Create a public key and signing key using the default client
        PublicKey key = PublicKey.creator(
            DatatypeConverter.printBase64Binary(pk.getEncoded())
        ).setFriendlyName("Public Key").create(client);

        NewSigningKey signingKey = NewSigningKey.creator().create(client);

        // Switch to validation client as the default client
        TwilioRestClient validationClient = new TwilioRestClient.Builder(signingKey.getSid(), signingKey.getSecret())
            .accountSid(ACCOUNT_SID)
            .httpClient(new ValidationClient(ACCOUNT_SID, key.getSid(), signingKey.getSid(), pair.getPrivate()))
            .build();

        // Make REST API requests
        Iterable<Message> messages = Message.reader().read(validationClient);
        for (Message message : messages) {
            System.out.println(message.getBody());
        }

        Message message = Message.creator(
            new PhoneNumber("+1XXXXXXXXXX"),
            new PhoneNumber("+1XXXXXXXXXX"),
            "Public Key Client Validation Test"
        ).create(validationClient);
        System.out.println(message.getSid());
    }
}
