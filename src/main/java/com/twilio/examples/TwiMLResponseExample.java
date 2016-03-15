package com.twilio.examples;

import com.twilio.sdk.twiml.Conference;
import com.twilio.sdk.twiml.Dial;
import com.twilio.sdk.twiml.Gather;
import com.twilio.sdk.twiml.Redirect;
import com.twilio.sdk.twiml.Say;
import com.twilio.sdk.twiml.TwiMLException;
import com.twilio.sdk.twiml.VoiceResponse;

/*
Copyright (c) 2012-2016 Twilio, Inc.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

/**
 * The Class TwiMLResponseExample.
 */
public class TwiMLResponseExample {

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