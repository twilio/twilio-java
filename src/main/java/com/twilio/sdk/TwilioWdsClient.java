package com.twilio.sdk;

/**
 * @author apayment
 * @since 2014-09-09
 */
public class TwilioWdsClient extends TwilioClient {

	public TwilioWdsClient(final String accountSid, final String authToken) {
		super(accountSid, authToken, "http://wds.twilio.com");
	}

}
