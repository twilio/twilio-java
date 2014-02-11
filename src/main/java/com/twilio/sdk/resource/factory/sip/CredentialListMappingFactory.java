package com.twilio.sdk.resource.factory.sip;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.CredentialListMapping;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" https://www.twilio.com/docs/api/rest/credential-list-mappings"> https://www.twilio.com/docs/api/rest/credential-list-mappings</a>
 *
 */
public interface CredentialListMappingFactory {


	/**
	 * Creates the ip access control list mapping
	 *
	 * @param params the params map
	 * @return the ip access control list mapping
	 * @throws TwilioRestException
	 */
	public CredentialListMapping create(Map<String, String> params) throws TwilioRestException;

	/**
	 * Creates the ip access control list mapping
	 *
	 * @param params the params list
	 * @return the ip access control list mapping
	 * @throws TwilioRestException
	 */
	public CredentialListMapping create(List<NameValuePair> params) throws TwilioRestException;

}
