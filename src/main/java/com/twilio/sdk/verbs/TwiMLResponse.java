package com.twilio.sdk.verbs;

import java.util.ArrayList;

/*
Copyright (c) 2008-2015 Twilio, Inc.

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
 * The Class TwiMLResponse.
 */
public class TwiMLResponse extends Verb {

    /**
     * Instantiates a new twiml response.
     */
    public TwiMLResponse() {
        super(Verb.V_RESPONSE, null);
        allowedVerbs = new ArrayList<>();
        allowedVerbs.add(Verb.V_CLIENT);
        allowedVerbs.add(Verb.V_CONFERENCE);
        allowedVerbs.add(Verb.V_DIAL);
        allowedVerbs.add(Verb.V_ENQUEUE);
        allowedVerbs.add(Verb.V_GATHER);
        allowedVerbs.add(Verb.V_HANGUP);
        allowedVerbs.add(Verb.V_LEAVE);
        allowedVerbs.add(Verb.V_NUMBER);
        allowedVerbs.add(Verb.V_PAUSE);
        allowedVerbs.add(Verb.V_PLAY);
        allowedVerbs.add(Verb.V_QUEUE);
        allowedVerbs.add(Verb.V_RECORD);
        allowedVerbs.add(Verb.V_REDIRECT);
        allowedVerbs.add(Verb.V_SAY);
        allowedVerbs.add(Verb.V_SMS);
        allowedVerbs.add(Verb.V_MESSAGE);
        allowedVerbs.add(Verb.V_REJECT);
    }

}

