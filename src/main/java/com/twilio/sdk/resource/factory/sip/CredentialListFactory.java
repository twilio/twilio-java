package com.twilio.sdk.resource.factory.sip;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.CredentialListInstance;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating CredentialListInstance objects.
 *
 * For more information see <a href=" https://www.twilio.com/docs/api/rest/credential-list"> https://www.twilio.com/docs/api/rest/credential-list</a>
 *
 */
public interface CredentialListFactory {

	/**
	 * Creates the credential list.
	 *
	 * @param params the params map
	 * @return the credential list
	 * @throws TwilioRestException
	 */
	public CredentialListInstance create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the credential list.
	 *
	 * @param params the params list
	 * @return the credential list
	 * @throws TwilioRestException
	 */
	public CredentialListInstance create(List<NameValuePair> params) throws TwilioRestException;

}
