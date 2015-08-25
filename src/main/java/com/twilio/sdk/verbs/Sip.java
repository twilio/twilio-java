package com.twilio.sdk.verbs;

import java.util.ArrayList;

public class Sip extends Verb {


    /**
     * Instantiates a new Sip.
     */
    public Sip(final String uri) {
        super(V_SIP, uri);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_URI);
    }

    /**
     * Instantiates a new Sip Noun with no URI.
     */
    public Sip() {
        this(null);
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    public void setPassword(String password) {
        this.set("password", password);
    }

    /**
     * Sets the url.
     *
     * @param url the waiting url
     */
    public void setUrl(String url){
       this.set("url", url);
    }

    /**
     * Sets the method.
     *
     * @param method the new method
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
