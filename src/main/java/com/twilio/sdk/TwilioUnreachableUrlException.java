package com.twilio.sdk;

/**
 * See: <a href="https://www.twilio.com/docs/errors/">https://www.twilio.com/docs/errors/</a> for more info
 * @author FrankStratton
 *
 */
public class TwilioUnreachableUrlException extends TwilioRestException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -181355409302925781L;

	/**
	 * Instantiates a new twilio rest exception.
	 *
	 * @param message the message
	 * @param errorCode the error code
	 */
	public TwilioUnreachableUrlException(String message, int errorCode) {
		super(message, errorCode, "");
	}


}
