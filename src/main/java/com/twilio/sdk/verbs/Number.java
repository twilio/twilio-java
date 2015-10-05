package com.twilio.sdk.verbs;



// TODO: Auto-generated Javadoc
/*
Copyright (c) 2008 Twilio, Inc.

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
 * The Class Number.
 */
public class Number extends Verb {
    
    /**
     * Instantiates a new number.
     *
     * @param number the number
     */
    public Number(String number) {
        super(V_NUMBER, number);
        this.allowedVerbs = null;
    }

    /**
     * Sets the send digits.
     *
     * @param digits the new send digits
     */
    public void setSendDigits(String digits){
       this.set("sendDigits", digits);   
    }
    
    /**
     * Sets the url.
     *
     * @param url the new url
     */
    public void setUrl(String url){
       this.set("url", url);
    }

    /**
     * Sets the method.
     *
     * @param method the HTTP method to use when requesting the url
     */
    public void setMethod(String method){
       this.set("method", method);
    }

	/**
	 * Set a URL to be requested when the status of this Call changes.
	 * @param url Status callback URL.
	 */
	public void setStatusCallback(String url) {
		this.set("statusCallback", url);
	}

	/**
	 * Set the method Twilio will use to request the StatusCallback URL.
	 * @param method Either "GET" or "POST"
	 */
	public void setStatusCallbackMethod(String method) {
		this.set("statusCallbackMethod", method);
	}

	/**
	 * Set the events Twilio will fire StatusCallbacks for.
	 * Available call lifecycle events:
	 * <ul>
	 *     <li>initiated</li>
	 *     <li>ringing</li>
	 *     <li>answered</li>
	 *     <li>completed</li>
	 * </ul>
	 *
	 * 'completed' events are provided gratis; for pricing on the other
	 * events see twilio.com.
	 *
	 * To get callbacks for more than one event, provide a space-separated
	 * list of the events, for example "initiated ringing completed".
	 *
	 * If unset, Twilio defaults to sending only the 'completed' event.
	 * @param events Events to provide callbacks for.
	 */
	public void setStatusCallbackEvents(String events) {
		this.set("statusCallbackEvent", events);
	}

}

