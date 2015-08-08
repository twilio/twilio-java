package com.twilio.sdk;

/**
 * See: <a href="https://www.twilio.com/docs/errors/">https://www.twilio.com/docs/errors/</a> for more info
 * @author FrankStratton
 *
 */
public class TwilioRestException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -181355409302925081L;
	
	/** The error code. */
	private int errorCode;
	
	/** The message. */
	private String message;
	
	/** The more info. */
	private String moreInfo;

	/**
	 * Instantiates a new twilio rest exception.
	 *
	 * @param message the message
	 * @param errorCode the error code
	 */
	public TwilioRestException(String message, int errorCode) {
		this(message, errorCode, "");
	}

	/**
	 * Instantiates a new twilio rest exception.
	 *
	 * @param message the message
	 * @param errorCode the error code
	 * @param moreInfo the more info
	 */
	public TwilioRestException(String message, int errorCode, String moreInfo) {
		super(message);

		this.message = message;
		this.errorCode = errorCode;
		this.moreInfo = moreInfo;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return this.message;
	}

	/**
	 * Gets the more info.
	 *
	 * @return the more info
	 */
	public String getMoreInfo() {
		return moreInfo;
	}

}
