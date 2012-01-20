package com.twilio.sdk;

import java.util.Map;



/**
 * See: {@see <a href="http://www.twilio.com/docs/errors/">http://www.twilio.com/docs/errors/} for more info
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
	 * Parses the response.
	 *
	 * @param response the response
	 * @return the twilio rest exception
	 */
	public static TwilioRestException parseResponse(TwilioRestResponse response) {
		Map<String, Object> data = response.toMap();
		String message = "";
		String moreInfo = "";
		int errorCode = 0;
		if (response.isJson()) {
			message = (String) data.get("message");
			
			if (data.get("code") != null) {
				errorCode = (Integer) data.get("code");
			}
			if (data.get("more_info") != null) {
				moreInfo = (String) data.get("more_info");
			}
		}
		// TODO xml

		return new TwilioRestException(message, errorCode, moreInfo);
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
