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
 * The Class Dial.
 */
public class Dial extends Verb {

    /**
     * Instantiates a new dial.
     *
     * @param number the number
     */
    public Dial(final String number) {
        super(V_DIAL, number);
        allowedVerbs = new ArrayList<>();
        allowedVerbs.add(Verb.V_NUMBER);
        allowedVerbs.add(Verb.V_CONFERENCE);
        allowedVerbs.add(Verb.V_CLIENT);
        allowedVerbs.add(Verb.V_QUEUE);
        allowedVerbs.add(Verb.V_SIP);
    }

    /**
     * Instantiates a new dial.
     */
    public Dial() {
        this(null);
    }

    /**
     * Sets the action.
     *
     * @param url the new action
     */
    public void setAction(final String url) {
        set("action", url);
    }

    /**
     * Sets the method.
     *
     * @param method the new method
     */
    public void setMethod(final String method) {
        set("method", method);
    }

    /**
     * Sets the timeout.
     *
     * @param i the new timeout
     */
    public void setTimeout(final int i) {
        set("timeout", Integer.toString(i));
    }

    /**
     * Sets the hangup on star.
     *
     * @param f the new hangup on star
     */
    public void setHangupOnStar(final boolean f) {
        if (f) {
            set("hangupOnStar", "true");
        } else {
            set("hangupOnStar", "false");
        }
    }

    /**
     * Sets the time limit.
     *
     * @param i the new time limit
     */
    public void setTimeLimit(final int i) {
        set("timeLimit", Integer.toString(i));
    }

    /**
     * Sets the caller id.
     *
     * @param callerId the new caller id
     */
    public void setCallerId(final String callerId) {
        set(" callerId ", callerId);
    }

}

