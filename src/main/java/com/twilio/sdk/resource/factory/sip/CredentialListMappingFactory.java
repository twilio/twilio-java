package com.twilio.sdk.resource.factory.sip;

import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.sip.CredentialListMapping;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SipDomain objects.
 *
 * For more information see <a href=" http://www.twilio.com/docs/api/rest/credential-list-mappings"> http://www.twilio.com/docs/api/rest/credential-list-mappings</a>
 *
 */
public interface CredentialListMappingFactory {


	/**
	 * Creates the ip access control list mapping
	 *
	 * @param params the params
	 * @return the ip access control list mapping
	 * @throws TwilioRestException
	 */
	public CredentialListMapping create(Map<String, String> params) throws TwilioRestException;

}
