package com.twilio.sdk.resource.factory.sip;

import java.util.Map;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.Credential;
import org.apache.http.NameValuePair;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Credential objects.
 *
 * For more information see <a href=" https://www.twilio.com/docs/api/rest/credential"> https://www.twilio.com/docs/api/rest/credential</a>
 *
 */
public interface CredentialFactory {

	/**
	 * Creates the credential
	 *
	 * @param params the params map
	 * @return the credential
	 * @throws TwilioRestException
	 */
	public Credential create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the credential
	 *
	 * @param params the params list
	 * @return the credential
	 * @throws TwilioRestException
	 */
	public Credential create(List<NameValuePair> params) throws TwilioRestException;
}
