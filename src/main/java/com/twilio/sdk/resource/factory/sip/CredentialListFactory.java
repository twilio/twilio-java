package com.twilio.sdk.resource.factory.sip;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.CredentialListInstance;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating CredentialListInstance objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/credential-list"> http://www.twilio.com/docs/api/rest/credential-list</a>
 *
 */
public interface CredentialListFactory {

	/**
	 * Creates the credential list.
	 *
	 * @param params the params
	 * @return the credential list
	 * @throws TwilioRestException
	 */
	public CredentialListInstance create(Map<String, String> params) throws TwilioRestException;
}
