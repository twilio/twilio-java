package com.twilio.sdk.resource.factory;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.CredentiaList;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating CredentialList objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/credential-list"> http://www.twilio.com/docs/api/rest/credential-list</a>
 *
 */
public interface CrednetialListFactory {

	/**
	 * Creates the credential list.
	 *
	 * @param params the params
	 * @return the credential list
	 * @throws TwilioRestException
	 */
	public CredentialList create(Map<String, String> params) throws TwilioRestException;
}
