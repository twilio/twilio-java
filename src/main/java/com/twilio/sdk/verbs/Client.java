package com.twilio.sdk.verbs;

/*
Copyright (c) 2012 Twilio, Inc.

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
 * Class for the &lt;Client> TwiML verb. A full reference can be found online
 * at https://www.twilio.com/docs/api/twiml/client.
 */
public class Client extends Verb {
    
    /**
     * Instantiate a new client.
     *
     * @param name the name
     */
    public Client(String name) {
        super(V_CLIENT, name);
        this.allowedVerbs = null;
    }

    /**
     * Sets the method to use when requesting the URL.
     *
     * @param method the HTTP method to use when requesting the url
     */
    public void setMethod(String method){
       this.set("method", method);
    }

    /**
     * Sets a url to play to one party before both parties have been connected.
     * This can be used to reject an incoming call or play TwiML to one
     * party before connecting the two.
     *
     * @param url the new url
     */
    public void setUrl(String url){
       this.set("url", url);
    }
}

