package com.twilio.sdk.resource.factory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Token;
import org.apache.http.NameValuePair;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Token objects.
 */
public interface TokenFactory {

	/**
	 * Creates the token.
	 *
	 * @param params the params list
	 * @return the token
	 * @throws TwilioRestException
	 */
	public Token create(List<NameValuePair> params) throws TwilioRestException;
}
