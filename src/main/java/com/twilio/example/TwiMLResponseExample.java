package com.twilio.example;

import com.twilio.twiml.Conference;
import com.twilio.twiml.Dial;
import com.twilio.twiml.Gather;
import com.twilio.twiml.Redirect;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

/**
 * The Class TwiMLResponseExample.
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class TwiMLResponseExample {

    /**
     * TwiML example usage.
     *
     * @param args command line args
     * @throws TwiMLException if cannot generate TwiML
     */
    public static void main(final String[] args) throws TwiMLException {
        // Say
        Say say = new Say.Builder("Hello World!")
            .voice(Say.Voice.MAN)
            .loop(5)
            .build();

        VoiceResponse response = new VoiceResponse.Builder()
            .say(say)
            .build();

        System.out.println(response.toXml());

        // Gather, Redirect
        Gather gather = new Gather.Builder()
            .numDigits(10)
            .say(new Say.Builder("Press 1").build())
            .build();

        Redirect redirect = new Redirect.Builder().build();

        response = new VoiceResponse.Builder()
            .gather(gather)
            .redirect(redirect)
            .build();

        System.out.println(response.toXml());

        // Conference
        Conference conference = new Conference.Builder("my room")
            .beep(Conference.Beep.TRUE)
            .build();

        Dial dial = new Dial.Builder()
            .callerId("+1 (555) 555-5555")
            .action("foo")
            .hangupOnStar(true)
            .conference(conference)
            .build();

        response = new VoiceResponse.Builder()
            .dial(dial)
            .build();

        System.out.println(response.toXml());
    }
}