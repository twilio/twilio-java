package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Credential;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Credential objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/credential"> http://www.twilio.com/docs/api/rest/credential</a>
 *
 */
public interface CredentialFactory {

	/**
	 * Creates the credential
	 *
	 * @param params the params
	 * @return the credential
	 * @throws TwilioRestException
	 */
	public Credential create(Map<String, String> params) throws TwilioRestException;
}
